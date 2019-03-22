package android.hackforgood.hackforgood.data.repository

import android.arch.lifecycle.MutableLiveData
import android.hackforgood.hackforgood.data.model.Travel
import android.hackforgood.hackforgood.data.repository.remote.APIClient

/**
 * Created by justo on 21/03/2019.
 */
class TravelRepository {
    private var travelLiveData = MutableLiveData<List<Travel>>()
    private var travelService = APIClient.Single.getTravelService()

}