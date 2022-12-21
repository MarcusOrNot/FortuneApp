package com.android.appleoffortune.data.repository

import com.android.appleoffortune.domain.model.StopRate
import com.android.appleoffortune.domain.repository.StopRateRepo
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class StopRateImpl:StopRateRepo {
    override suspend fun GetStopRates(): List<StopRate> {

        return suspendCoroutine { continuation ->

            db.collection("stop_rate").get().addOnSuccessListener {
                val res:MutableList<StopRate> = mutableListOf()
                it.forEach{
                    res.add(StopRate((it.get("round") as Long).toInt(), (it.get("percent") as Long).toInt()))
                }
                continuation.resume(res)
            }

        }
    }

    companion object {
        val db = FirebaseFirestore.getInstance()
    }
}