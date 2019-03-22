package android.hackforgood.hackforgood.ui.see_own_ads

import android.arch.lifecycle.LiveData
import android.hackforgood.hackforgood.data.model.Ad
import android.hackforgood.hackforgood.data.model.Center

/**
 * Created by justo on 21/03/2019.
 */
interface SeeOwnAds_MVP {
    interface View {
        fun setDataToShow(data: List<Ad>)
        fun setDataCenter(data: List<Center>)
    }

    interface Presenter {
        fun viewLoaded()
    }

    interface Model {
        fun getAdds(): LiveData<List<Ad>>
        fun getCenters(): LiveData<List<Center>>
    }
}