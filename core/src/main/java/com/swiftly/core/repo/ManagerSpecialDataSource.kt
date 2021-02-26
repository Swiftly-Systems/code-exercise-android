package com.swiftly.core.repo

import com.swiftly.core.data.ManagerSpecialList

interface ManagerSpecialDataSource {
    suspend fun getAll(): ManagerSpecialList?
    suspend fun setAll(list: ManagerSpecialList?)
}