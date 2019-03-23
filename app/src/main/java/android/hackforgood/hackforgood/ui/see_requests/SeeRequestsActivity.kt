package android.hackforgood.hackforgood.ui.see_requests

import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.model.Travel
import android.hackforgood.hackforgood.data.see_requests.SeeRequestsModel
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_see_requests.*
import kotlinx.android.synthetic.main.card_travel.view.*

class SeeRequestsActivity : AppCompatActivity(), SeeRequests_MVP.View {
    private lateinit var presenter: SeeRequests_MVP.Presenter

    private var centers: List<Center>? = null
    private var ads: List<Ad>? = null
    private var travels: List<Travel>? = null
    private var cities: List<City>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_requests)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        window.statusBarColor = resources.getColor(R.color.colorSecondary)

        setupMVP()

        presenter.viewLoaded()
    }

    private fun setupRecView(data: List<Travel>) {
        val llm = LinearLayoutManager(this)
        recViewRequests.setLayoutManager(llm)

        val adapter = RVAdapter(data)
        recViewRequests.adapter = adapter
    }

    override fun setDataCenter(data: List<Center>) {
        centers = data
        if (travels != null && ads != null && cities != null)
            setupRecView(travels!!)
    }

    override fun setDataAds(data: List<Ad>) {
        ads = data
        if (centers != null && travels != null && cities != null)
            setupRecView(travels!!)
    }

    override fun setDataTravels(data: List<Travel>) {
        travels = data
        if (ads != null && centers != null && cities != null)
            setupRecView(travels!!)
    }

    override fun setDataCities(data: List<City>) {
        cities = data
        if (ads != null && centers != null && travels != null)
            setupRecView(travels!!)
    }

    private fun setupMVP() {
        presenter = SeeRequestsPresenter(this, SeeRequestsModel(this))
    }

    inner class RVAdapter(private val data: List<Travel>) : RecyclerView.Adapter<RVAdapter.TravelViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TravelViewHolder {
            val view = LayoutInflater.from(p0.context).inflate(R.layout.card_travel, p0, false)
            val viewholder = TravelViewHolder(view)

            return viewholder
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(viewholder: TravelViewHolder, i: Int) {
            val travel = data[i]
            val ad = ads?.filter { ad -> travel.idAd == ad.id }?.single()
            val center = centers?.filter { center -> center.id == ad!!.idCenter }?.single()
            val city = cities?.filter { city -> city.id == ad!!.idLocalidad }?.single()

            viewholder.dateTextView.text = ad!!.day
            viewholder.hourProvTextView.text = travel.hourCita
            if (!travel.revised)
                viewholder.status.text = "No revisada"
            else if (travel.acepted)
                viewholder.status.text = "Aceptada"
            else
                viewholder.status.text = "Denegada"

            viewholder.city.text = city!!.name
            viewholder.center.text = center!!.name
        }

        inner class TravelViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var dateTextView = itemView.dateTextView
            var hourProvTextView = itemView.hourProvTextView
            var status = itemView.status
            var city = itemView.city
            var center = itemView.center
        }

    }
}
