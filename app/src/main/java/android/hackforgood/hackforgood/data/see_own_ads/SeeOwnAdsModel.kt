package android.hackforgood.hackforgood.data.see_own_ads

import android.arch.lifecycle.LiveData
import android.content.Context
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center
import android.hackforgood.hackforgood.data.repository.AdRepository
import android.hackforgood.hackforgood.data.repository.CenterRepository
import android.hackforgood.hackforgood.ui.see_own_ads.SeeOwnAds_MVP

/**
 * Created by justo on 21/03/2019.
 */
class SeeOwnAdsModel(context: Context) : SeeOwnAds_MVP.Model {
    private val adRepository = AdRepository(context)
    private val centerRepository = CenterRepository()

    override fun getAdds(): LiveData<List<Ad>> {
        return adRepository.getOwnAds()
    }

    override fun getCenters(): LiveData<List<Center>> {
        return centerRepository.getCenters()
    }
}