package com.swiftly.core.repo

import com.swiftly.core.data.ManagerSpecialList
import com.swiftly.core.data.Resource

interface ManagerSpecialRepository {
    suspend fun getAllSpecials(): Resource<ManagerSpecialList?>?
    suspend fun setAllSpecials(list: ManagerSpecialList?)
    fun isStale(): Boolean
}