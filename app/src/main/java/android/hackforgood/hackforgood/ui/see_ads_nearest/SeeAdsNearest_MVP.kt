package android.hackforgood.hackforgood.ui.see_ads_nearest

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center

/**
 * Created by justo on 22/03/2019.
 */
interface SeeAdsNearest_MVP {
    interface View {
        fun setDataToShow(data: List<Ad>)
        fun setDataCenter(data: List<Center>)
        fun setDataToShowOthers(data: List<Ad>)
    }

    interface Presenter {
        fun viewLoaded(day: String, hour: String, idCenter: Int)
    }

    interface Model {
        fun getAdsRecommended(day: String, hour: String, idCenter: Int): LiveData<List<Ad>>
        fun getOthersAds(day: String, idCenter: Int): LiveData<List<Ad>>
        fun getCenters(): LiveData<List<Center>>
    }
}