package com.swiftly.codingexersicejavi.framework

import com.swiftly.codingexersicejavi.framework.network.ManagerSpecialService
import com.swiftly.core.data.Resource
import com.swiftly.core.data.ManagerSpecial
import com.swiftly.core.data.ManagerSpecialList
import com.swiftly.core.repo.ManagerSpecialDataSource
import com.swiftly.core.repo.ManagerSpecialRepository

class ManagerSpecialRepository(private val dataSource: ManagerSpecialDataSource, private val service: ManagerSpecialService): ManagerSpecialRepository {

    override suspend fun getAllSpecials(): Resource<ManagerSpecialList?>? {
        var list: ManagerSpecialList? = dataSource.getAll()
        if (list == null || isStale())
            return service.fetchAllSpecials()
        else
            return Resource.Success<ManagerSpecialList?>(data = list)
    }

    override suspend fun setAllSpecials(list: ManagerSpecialList?) {
        dataSource.setAll(list)
    }

    override fun isStale(): Boolean{
        return true    //policy for this project is to always fetch fresh data
    }
}