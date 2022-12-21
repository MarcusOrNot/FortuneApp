package com.android.appleoffortune.data.repository

import com.android.appleoffortune.domain.repository.LoginResult
import com.android.appleoffortune.domain.repository.LoginServerRepo
import com.google.firebase.firestore.FirebaseFirestore

class LoginServerImp():LoginServerRepo {
    override fun tryLogin(pass:String, _onResult: LoginResult) {
        db.collection("passwords").whereEqualTo("value", pass).get().addOnCompleteListener {
            if (it.result.isEmpty()) _onResult.onFailed()
            else _onResult.onSuccess()
        }
    }

    companion object {
        val db = FirebaseFirestore.getInstance()
    }
}