package com.android.appleoffortune.data.repository

import com.android.appleoffortune.domain.model.StopRate
import com.android.appleoffortune.domain.repository.LinkDataRepo
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GameLinkFirebase:LinkDataRepo {
    override suspend fun GetGameLink(): String {
        return suspendCoroutine { continuation ->

            db.collection("link").whereEqualTo("id", 1).get().addOnSuccessListener {
                if (it.isEmpty==false) {
                    continuation.resume(it.first().get("value").toString())
                }
                else
                    continuation.resume("")
            }

        }
    }

    companion object {
        val db = FirebaseFirestore.getInstance()
    }
}