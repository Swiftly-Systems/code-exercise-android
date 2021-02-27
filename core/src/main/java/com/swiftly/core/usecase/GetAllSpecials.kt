package com.swiftly.core.usecase

import com.swiftly.core.repo.ManagerSpecialRepository

class GetAllSpecials(private val specialsRepo: ManagerSpecialRepository) {
    suspend operator fun invoke() = specialsRepo.getAllSpecials()
}