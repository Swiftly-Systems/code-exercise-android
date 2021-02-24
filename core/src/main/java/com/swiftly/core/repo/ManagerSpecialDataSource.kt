package com.swiftly.core.repo

import com.swiftly.core.data.ManagerSpecial

interface ManagerSpecialDataSource {
    suspend fun getAll(): List<ManagerSpecial>?
}