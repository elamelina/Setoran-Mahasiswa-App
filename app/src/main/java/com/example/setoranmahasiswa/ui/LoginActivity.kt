package com.example.setoranmahasiswa.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.setoranmahasiswa.R
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import com.example.setoranmahasiswa.data.SessionManager

class LoginActivity : AppCompatActivity() {

    private lateinit var authService: AuthorizationService
    private val clientId = "android-app" // sesuai Client ID Keycloak
    private val redirectUri = Uri.parse("com.example.setoranmahasiswa://redirect")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authService = AuthorizationService(this)

        findViewById<Button>(R.id.btnLoginSSO).setOnClickListener {
            startSSOLogin()
        }
    }

    private fun startSSOLogin() {
        val serviceConfig = AuthorizationServiceConfiguration(
            Uri.parse("https://id.tif.uin-suska.ac.id/auth/realms/tif/protocol/openid-connect/auth"),
            Uri.parse("https://id.tif.uin-suska.ac.id/auth/realms/tif/protocol/openid-connect/token")
        )

        val authRequest = AuthorizationRequest.Builder(
            serviceConfig,
            clientId,
            ResponseTypeValues.CODE,
            redirectUri
        )
            .setScope("openid profile email")
            .build()

        val intent = authService.getAuthorizationRequestIntent(authRequest)
        startActivityForResult(intent, 1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1001) {
            val resp = AuthorizationResponse.fromIntent(data!!)
            val ex = AuthorizationException.fromIntent(data)

            if (resp != null) {
                authService.performTokenRequest(resp.createTokenExchangeRequest()) { response, exception ->
                    if (response != null) {
                        val accessToken = response.accessToken
                        val idToken = response.idToken

                        SessionManager.token = accessToken ?: ""

                        // Optional: Extract NIM from ID Token
                        // SessionManager.nim = extractNimFromIdToken(idToken)

                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Log.e("Login", "Token Error: $exception")
                    }
                }
            } else {
                Log.e("Login", "Authorization failed: $ex")
            }
        }
    }
}