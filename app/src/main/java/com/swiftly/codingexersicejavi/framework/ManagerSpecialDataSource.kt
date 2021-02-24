package com.swiftly.codingexersicejavi.framework

import android.content.Context
import com.swiftly.codingexersicejavi.framework.network.ManagerSpecialService
import com.swiftly.core.data.ManagerSpecial
import com.swiftly.core.repo.ManagerSpecialDataSource

class ManagerSpecialDataSource(context: Context): ManagerSpecialDataSource {

    val specialsService = ManagerSpecialService.getInstance(context)

    override suspend fun getAll(): List<ManagerSpecial>? {
        return specialsService.getAll()
    }
}