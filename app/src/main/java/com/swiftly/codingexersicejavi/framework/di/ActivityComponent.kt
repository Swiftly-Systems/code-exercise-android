package com.swiftly.codingexersicejavi.framework.di

import android.app.Activity
import com.swiftly.codingexersicejavi.framework.db.DbSpecialsMock
import com.swiftly.codingexersicejavi.framework.network.ManagerSpecialService
import com.swiftly.codingexersicejavi.presentation.MainActivity
import dagger.Component

@Component(modules = [ApplicationModule::class, ServiceModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}