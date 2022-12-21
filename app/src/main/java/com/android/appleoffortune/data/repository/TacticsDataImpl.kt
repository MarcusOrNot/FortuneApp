package com.android.appleoffortune.data.repository

import com.android.appleoffortune.domain.model.Tactic
import com.android.appleoffortune.domain.repository.TacticsDataRepo
import com.google.firebase.firestore.FirebaseFirestore

class TacticsDataImpl:TacticsDataRepo {

    companion object {
        val db = FirebaseFirestore.getInstance()
    }

    /*override fun GetTactics(round: Int, callback: (resTactics: List<String>) -> Unit) {
        val res:List<String> = listOf()

        db.collection("tactics2").whereEqualTo("round", round).get().addOnCompleteListener {
            var res:MutableList<String> = mutableListOf()
            if (it.result.isEmpty() == false) {
                it.result.forEach {
                    res.add(it.get("value").toString())
                }
            }
            callback.invoke(res)
        }
    } */

    override fun GetTactics(callback: (resTactics: List<Tactic>) -> Unit) {
        db.collection("tactics2").get().addOnCompleteListener {
            var res:MutableList<Tactic> = mutableListOf()
            it.result.forEach {
                res.add(Tactic((it.get("round") as Long).toInt(), it.get("value").toString()))
            }
            callback.invoke(res)
        }
    }
}