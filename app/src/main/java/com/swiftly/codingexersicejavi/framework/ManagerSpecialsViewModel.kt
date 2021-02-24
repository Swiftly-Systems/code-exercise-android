package com.swiftly.codingexersicejavi.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.swiftly.codingexersicejavi.framework.di.ApplicationModule
import com.swiftly.codingexersicejavi.framework.di.DaggerViewModelComponent
import com.swiftly.codingexersicejavi.framework.usecases.UseCases
import com.swiftly.core.data.ManagerSpecial
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ManagerSpecialsViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val fetched = MutableLiveData<Boolean>()
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
        coroutineScope.launch {
            specialsList.postValue(useCases.getAllSpecials())
        }
    }
}