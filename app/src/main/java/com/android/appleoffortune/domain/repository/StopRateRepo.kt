package com.android.appleoffortune.domain.repository

import com.android.appleoffortune.domain.model.StopRate

interface StopRateRepo {

    public suspend fun GetStopRates():List<StopRate>

}