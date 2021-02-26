package com.swiftly.codingexersicejavi.framework.usecases

import com.swiftly.core.usecase.GetAllSpecials
import com.swiftly.core.usecase.SetAllSpecials

data class UseCases(
        val getAllSpecials: GetAllSpecials,
        val setAllSpecials: SetAllSpecials,
)