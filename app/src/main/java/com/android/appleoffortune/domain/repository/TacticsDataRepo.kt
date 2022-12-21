package com.android.appleoffortune.domain.repository

import com.android.appleoffortune.domain.model.Tactic

interface TacticsDataRepo {

    public fun GetTactics(callback:(resTactics:List<Tactic>)->Unit)

}