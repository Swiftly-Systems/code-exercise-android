package com.swiftly.core.repo

class ManagerSpecialRepository(private val dataSource: ManagerSpecialDataSource) {
    suspend fun getAllSpecials() = dataSource.getAll()
}