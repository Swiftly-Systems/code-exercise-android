package com.swiftly.codingexersicejavi.framework

import android.content.Context
import com.swiftly.codingexersicejavi.framework.db.DbSpecialsMock
import com.swiftly.core.data.ManagerSpecialList
import com.swiftly.core.repo.ManagerSpecialDataSource

class ManagerSpecialDbSource(context: Context): ManagerSpecialDataSource {

    private val specialsService = DbSpecialsMock.getInstance()

    override suspend fun getAll(): ManagerSpecialList? {
        return specialsService.getAll()
    }

    override suspend fun setAll(list: ManagerSpecialList?) {
        specialsService.setAll(list)
    }
}