package tz.rehema.rehemaassessment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import tz.rehema.rehemaassessment.model.NasaSearchApiModel
import tz.rehema.rehemaassessment.repository.NasaSearchApiRepository


class NasaSearchApiViewModel(application: Application) : AndroidViewModel(application) {
    private val mNasaSearchApiRepository: NasaSearchApiRepository

    val allNasaSearchApi: LiveData<List<NasaSearchApiModel>>
        get() = mNasaSearchApiRepository.getMutableLiveData()

    init {
        mNasaSearchApiRepository = NasaSearchApiRepository()
    }

}