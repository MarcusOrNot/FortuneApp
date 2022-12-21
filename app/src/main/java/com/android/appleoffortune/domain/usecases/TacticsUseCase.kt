package com.android.appleoffortune.domain.usecases

import com.android.appleoffortune.domain.model.Tactic
import com.android.appleoffortune.domain.repository.TacticsDataRepo
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random
import kotlin.random.nextInt

class TacticsUseCase(private val _tacticsRepo:TacticsDataRepo) {
    private lateinit var _tactics:List<Tactic>
    init {
        _tacticsRepo.GetTactics({resTactics -> Unit
            _tactics = resTactics
        })
    }

    public fun GetRandomTactic(round:Int):String {
       val roundTactics:MutableList<Tactic> = mutableListOf()
        _tactics.forEach{
            if (it.round == round) roundTactics.add(it)
        }

        if (roundTactics.count()>0) {
            return roundTactics.get(Random.nextInt(roundTactics.count())).value
        }
        return "Error tactic"
    }

}