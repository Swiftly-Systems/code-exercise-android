package com.swiftly.core.usecase

import com.swiftly.core.data.ManagerSpecial
import com.swiftly.core.data.ManagerSpecialList
import com.swiftly.core.repo.ManagerSpecialRepository

class SetAllSpecials(private val specialsRepo: ManagerSpecialRepository) {
    suspend operator fun invoke(list: ManagerSpecialList) = specialsRepo.setAllSpecials(list)
}