package android.hackforgood.hackforgood.ui.see_own_ads

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.hackforgood.hackforgood.R
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.model.City
import android.hackforgood.hackforgood.data.see_own_ads.SeeOwnAdsModel
import android.hackforgood.hackforgood.ui.ad_detail.AdDetailActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_see_own_ads.*
import kotlinx.android.synthetic.main.card_ads.view.*


class SeeOwnAdsFragment : Fragment(), SeeOwnAds_MVP.View {
    private lateinit var presenter: SeeOwnAds_MVP.Presenter
    private var centers: List<Center>? = null
    private var ads: List<Ad>? = null
    private var cities: List<City>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_see_own_ads, container, false)

        setupMVP()

        presenter.viewLoaded()

        return view
    }

    private fun setupRecView(data: List<Ad>) {
        progressBar.visibility = View.GONE
        val llm = LinearLayoutManager(activity)
        recView.setLayoutManager(llm)

        val adapter = RVAdapter(data)
        recView.adapter = adapter
    }

    override fun setDataCenter(data: List<Center>) {
        centers = data
        if (ads != null && cities != null)
            setupRecView(ads!!)
    }

    override fun setDataCity(data: List<City>) {
        cities = data
        if (ads != null && centers != null)
            setupRecView(ads!!)
    }

    override fun setDataToShow(data: List<Ad>?) {
        ads = data
        if (centers != null && ads != null && cities != null)
            setupRecView(ads!!)
    }

    private fun setupMVP() {
        presenter = SeeOwnAdsPresenter(this, SeeOwnAdsModel(activity!!))
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
            val localidad = cities!!.filter { city -> city.id == ad.idLocalidad}.single()

            viewholder.nameCenterTextView.text = center.name
            viewholder.salidaTextView.text = localidad.name
            viewholder.button.visibility = View.GONE

            viewholder.card.setOnClickListener {
                val intent = Intent(activity, AdDetailActivity::class.java)
                intent.putExtra("ad", ad)
                startActivity(intent)
            }
        }

        inner class AdViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var dateTextView = itemView.dateTextView
            var hourProvTextView = itemView.hourProvTextView
            var nameCenterTextView = itemView.nameCenterTextView
            var card = itemView.card
            var button = itemView.buttonRequest
            var salidaTextView = itemView.salidaTextView
        }

    }
}
