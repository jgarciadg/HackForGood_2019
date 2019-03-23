package android.hackforgood.hackforgood.ui.ad_detail

import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.data.ad_detail.AdDetailModel
import android.hackforgood.hackforgood.data.model.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_ad_detail.*
import kotlinx.android.synthetic.main.card_person_in_travel.view.*

class AdDetailActivity : AppCompatActivity(), AdDetail_MVP.View {
    private lateinit var ad: Ad
    private lateinit var presenter: AdDetail_MVP.Presenter

    private var travelsSolicited: List<Travel>? = null
    private var users: List<User>? = null
    private var centers: List<Center>? = null
    private var cities: List<City>? = null

    lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_detail)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        window.statusBarColor = resources.getColor(R.color.colorSecondary)

        setupMVP()

        if (intent.extras != null)
            ad = intent.extras["ad"] as Ad

        presenter.viewLoaded(ad.id)
        dateTextView.text = ad.day
        maxPersonsTextView.text = "Máximo personas " + ad.maxPersons.toString()
        numSolicitedTextView2.text = "Número solicitudes " + ad.solicited_persons.toString()
        timeLastAppointment.text = "Última cita: " + ad.timeLastAppointment

        val urlImage = "http://qss.unex.es:2072/user/photo/"
        Picasso.get()
                .load(urlImage)
                .into(profile_image_creator)
    }

    override fun showAcceptedPerson(travelId: Int) {
        Snackbar.make(container, "Aceptado", Snackbar.LENGTH_SHORT).show()
    }

    override fun setDataCenters(centers: List<Center>) {
        this.centers = centers

        nameCenterTextView.text = centers.filter { center -> center.id == ad.idCenter }.single().name

        if (cities != null && travelsSolicited != null && users != null)
            setupRecView(travelsSolicited!!)
    }

    override fun setDataCities(cities: List<City>) {
        this.cities = cities

        nameLocalidadTextView.text = cities.filter { city -> city.id == ad.idLocalidad}.single().name

        if (centers != null && travelsSolicited != null && users != null)
            setupRecView(travelsSolicited!!)
    }

    override fun setDataTravels(data: List<Travel>) {
        travelsSolicited = data
        if (cities != null && cities != null && users != null)
            setupRecView(travelsSolicited!!)
    }

    override fun setDataUsers(data: List<User>) {
        users = data

        if (travelsSolicited != null && cities != null && centers != null)
            setupRecView(travelsSolicited!!)
    }

    private fun setupRecView(data: List<Travel>) {
        val llm = LinearLayoutManager(this)
        recView.setLayoutManager(llm)

        adapter = RVAdapter(data)
        recView.adapter = adapter
    }

    private fun setupMVP() {
        presenter = AdDetailPresenter(this, AdDetailModel(this))
    }

    inner class RVAdapter(private val data: List<Travel>) : RecyclerView.Adapter<RVAdapter.TravelViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TravelViewHolder {
            val view = LayoutInflater.from(p0.context).inflate(R.layout.card_person_in_travel, p0, false)
            val viewholder = TravelViewHolder(view)

            return viewholder
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(viewholder: TravelViewHolder, i: Int) {
            val travel = data[i]
            val user = users!!.filter { user -> user.id == travel.idUser }.single()
            val urlImage = "http://qss.unex.es:2072/user/photo/${user.photo}"

            Picasso.get()
                    .load(urlImage)
                    .into(viewholder.personalImage)

            viewholder.hourTextView.text = travel.hourCita
            viewholder.telephoneTextView.text = user.telephone

            viewholder.acceptButton.setOnClickListener {
                presenter.acceptTravel(ad.id, user.id)

                viewholder.acceptButton.isClickable = false
                viewholder.acceptButton.text = "Aceptado"
            }

        }

        inner class TravelViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val personalImage = itemView.personalImage
            val hourTextView = itemView.hourTextView
            val acceptButton = itemView.acceptButton
            val telephoneTextView = itemView.telephoneTextView
            val cityTextView = itemView.cityTextView
        }

    }
}
