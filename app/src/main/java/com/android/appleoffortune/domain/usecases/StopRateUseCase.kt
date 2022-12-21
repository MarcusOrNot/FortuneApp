package com.android.appleoffortune.domain.usecases

import com.android.appleoffortune.domain.model.StopRate
import com.android.appleoffortune.domain.repository.StopRateRepo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StopRateUseCase(private val _stopRateRepo:StopRateRepo) {
    private lateinit var _stopRates:List<StopRate>
    init {
        GlobalScope.launch {
            _stopRates = _stopRateRepo.GetStopRates()
        }
    }

    public fun GetStopRate(round:Int):Int {
        _stopRates.forEach{
            if (it.round==round) return it.percent
        }
        return 0
    }

}