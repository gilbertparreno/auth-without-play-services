package com.google.firebase.nongmsauth.internal

import androidx.annotation.Keep
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.internal.InternalAuthProvider
import com.google.firebase.components.Component
import com.google.firebase.components.ComponentRegistrar
import com.google.firebase.components.Dependency
import com.google.firebase.nongmsauth.FirebaseRestAuth

@Keep
class RestAuthRegistrar : ComponentRegistrar {

    override fun getComponents(): MutableList<Component<*>> {
        val restAuthCompontent =
            Component.builder(InternalAuthProvider::class.java)
                .add(Dependency.required(FirebaseApp::class.java))
                .factory { container ->
                    val firebaseApp = container.get(FirebaseApp::class.java)
                    return@factory FirebaseRestAuth.getInstance(firebaseApp)
                }
                .build()

        return mutableListOf(restAuthCompontent)
    }

}