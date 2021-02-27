package com.swiftly.codingexersicejavi.framework.db

import com.swiftly.core.data.ManagerSpecial
import com.swiftly.core.data.ManagerSpecialList
import com.swiftly.core.repo.ManagerSpecialDataSource

/**
 * Will hold live data which the repository & viewmodel will observe.
 * Updated during a successful network call
 */
class DbSpecialsMock: ManagerSpecialDataSource {

    private var managerSpecials: ManagerSpecialList? = null

    companion object {
        @Volatile
        private var INSTANCE: DbSpecialsMock? = null

        @Synchronized
        fun getInstance(): DbSpecialsMock
                = INSTANCE ?: DbSpecialsMock().also { INSTANCE = it }
    }

    override suspend fun getAll(): ManagerSpecialList? {
        return managerSpecials
    }

    override suspend fun setAll(list: ManagerSpecialList?) {
        managerSpecials = list
    }
}