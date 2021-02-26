package com.swiftly.codingexersicejavi.framework

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swiftly.codingexersicejavi.R
import com.swiftly.codingexersicejavi.framework.di.ApplicationModule
import com.swiftly.codingexersicejavi.framework.di.DaggerViewModelComponent
import com.swiftly.codingexersicejavi.framework.usecases.UseCases
import com.swiftly.core.data.ManagerSpecial
import com.swiftly.core.data.ManagerSpecialList
import com.swiftly.core.data.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManagerSpecialsViewModel(application: Application): AndroidViewModel(application) {

    private val TAG: String = "ManagerSpecialsViewModel"
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val fetched = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val specialsList = MutableLiveData<List<ManagerSpecial>?>()

    @Inject
    lateinit var useCases: UseCases

    init{
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    fun getSpecials() {
        loading.value = true
        coroutineScope.launch {

            var resource = useCases.getAllSpecials()
            if(resource is Resource.Error<ManagerSpecialList?>?){
                error.postValue(resource?.message)
            }else if (resource is Resource.Success<ManagerSpecialList?>?){
                specialsList.postValue(resource?.data?.managerSpecials)
                Log.i(TAG, "canvas unit: " + resource?.data?.canvasUnit)
            }
            loading.postValue( false)
        }
    }
}