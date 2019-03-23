package android.hackforgood.hackforgood.ui.see_ads_nearest

import android.app.TimePickerDialog
import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.see_ads_nearest.SeeAdsNearestModel
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_see_ads_nearest.*
import kotlinx.android.synthetic.main.card_ads.view.*
import java.util.*

class SeeAdsNearestActivity : AppCompatActivity(), SeeAdsNearest_MVP.View, TimePickerDialog.OnTimeSetListener {
    private lateinit var presenter: SeeAdsNearest_MVP.Presenter
    private var centers: List<Center>? = null
    private var cities: List<City>? = null
    private var adsRecommended: List<Ad>? = null
    private var adsOthers: List<Ad>? = null
    private lateinit var timePickerDialog: TimePickerDialog

    private var indexAdSelected = 0
    private var typeAd = "recommended"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_ads_nearest)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        window.statusBarColor = resources.getColor(R.color.colorSecondary)

        setupMVP()

        setupTimePickerDialog()

        val day = intent.extras["day"].toString()
        val hour = intent.extras["hour"].toString()
        val idCenter = intent.extras["idCenter"].toString().toInt()
        val idLocalidad = intent.extras["idLocalidad"].toString().toInt()
        presenter.viewLoaded(day, hour, idCenter, idLocalidad)
    }

    private fun setupRecViewRecommended(data: List<Ad>) {
        val llm = LinearLayoutManager(this)
        recViewRecommended.setLayoutManager(llm)

        val adapter = RVAdapter(data)
        recViewRecommended.adapter = adapter
    }

    private fun setupRecViewOthers(data: List<Ad>) {
        val llm = LinearLayoutManager(this)
        recViewOthers.setLayoutManager(llm)

        val adapter = RVAdapter(data)
        recViewOthers.adapter = adapter
    }

    override fun setDataToShow(data: List<Ad>) {
        adsRecommended = data

        if (centers != null && cities != null && adsOthers != null)
            setupRecViewRecommended(adsRecommended!!)
    }

    override fun setDataToShowOthers(data: List<Ad>) {
        adsOthers = data

        if (centers != null && cities != null && adsRecommended != null)
            setupRecViewOthers(adsOthers!!)
    }

    override fun setDataCenter(data: List<Center>) {
        centers = data
        if (adsOthers != null && cities != null && adsRecommended != null)
            setupRecViewOthers(adsOthers!!)
        if (adsOthers != null && cities != null && adsRecommended != null)
            setupRecViewRecommended(adsRecommended!!)
    }

    override fun setDataCities(it: List<City>) {
        cities = it

        if (adsOthers != null && centers != null && adsRecommended != null)
            setupRecViewOthers(adsOthers!!)
        if (adsOthers != null && centers != null && adsRecommended != null)
            setupRecViewRecommended(adsRecommended!!)
    }

    private fun setupMVP() {
        presenter = SeeAdsNearestPresenter(this, SeeAdsNearestModel(this))
    }

    override fun showErrorInRequestAd() {
        Snackbar.make(activity, "No se ha podido solicitar el viaje", Snackbar.LENGTH_SHORT).show()
    }

    override fun showAdRequested() {
        Snackbar.make(activity, "Viaje Solicitado", Snackbar.LENGTH_SHORT).show()
    }

    private fun setupTimePickerDialog() {
        val date = Date()
        val calendar = GregorianCalendar()
        calendar.time = date
        val hour = calendar.get(Calendar.MONTH)
        val minute = calendar.get(Calendar.MINUTE)
        timePickerDialog = TimePickerDialog(this, 0, this, hour, minute, true)
    }

    private fun formatHour(hourOfDay: Int, minute: Int): String {
        var string_hour = hourOfDay.toString()
        if (hourOfDay < 10)
            string_hour = "0$hourOfDay"

        var string_minute = minute.toString()
        if (minute < 10)
            string_minute = "0$minute"

        return "$string_hour:$string_minute:00"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val string_hour: String = formatHour(hourOfDay, minute)

        if (typeAd == "recommended")
            presenter
                    .requestTravelButtonClicked(adsRecommended!![indexAdSelected].id, string_hour)
        else
            presenter
                    .requestTravelButtonClicked(adsOthers!![indexAdSelected].id, string_hour)

    }

    inner class RVAdapter(private val data: List<Ad>) : RecyclerView.Adapter<RVAdapter.AdViewHolder>() {

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AdViewHolder {
            val view = LayoutInflater.from(p0.context).inflate(R.layout.card_ads, p0, false)
            val viewholder = AdViewHolder(view)

            return viewholder
        }

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(viewholder: AdViewHolder, i: Int) {
            val ad = data[i]
            viewholder.dateTextView.text = ad.day
            viewholder.hourProvTextView.text = ad.timeArriveProvisional

            val center = centers!!.filter { center -> center.id == ad.idCenter }.single()
            viewholder.nameCenterTextView.text = center.name
            viewholder.salidaTextView.text = cities!!.filter { city -> city.id == ad.idLocalidad}.single().name

            viewholder.card.setOnClickListener {
                //Launch item with info about center and ad
            }

            viewholder.buttonRequest.setOnClickListener {
                if (adsRecommended!!.contains(ad)) {
                    indexAdSelected = adsRecommended!!.indexOf(ad)
                    typeAd = "recommended"

                } else if (adsOthers!!.contains(ad)) {
                    indexAdSelected = adsOthers!!.indexOf(ad)
                    typeAd = "others"
                }

                timePickerDialog.show()
            }
        }

        inner class AdViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var dateTextView = itemView.dateTextView
            var hourProvTextView = itemView.hourProvTextView
            var nameCenterTextView = itemView.nameCenterTextView
            var card = itemView.card
            var buttonRequest = itemView.buttonRequest
            var salidaTextView = itemView.salidaTextView
        }

    }
}
