package br.com.rotacilio.meusgastos.session.impl

import android.content.Context
import br.com.rotacilio.meusgastos.session.SessionCore
import java.util.*

object SessionManagerUtil : SessionCore {

    private const val SESSION_EXPIRY_TIME = "br.com.rotacilio.meusgastos.SESSION_EXPIRY_TIME"
    private const val SESSION_PREFERENCES = "br.com.rotacilio.meusgastos.SESSION_PREFERENCES"

    override fun startUserSession(context: Context, expiresIn: Int) {
        val calendar = Calendar.getInstance()
        val userLoggedInTime = calendar.time
        calendar.time = userLoggedInTime
        calendar.add(Calendar.SECOND, expiresIn)
        val expiryTime = calendar.time
        val editor = context.getSharedPreferences(SESSION_PREFERENCES, 0).edit()
        editor.putLong(SESSION_EXPIRY_TIME, expiryTime.time)
        editor.apply()
    }

    override fun isSessionActive(currentTime: Date, context: Context): Boolean {
        val sessionExpiresAt = Date(getExpiryDateFromPreferences(context)!!)
        return !currentTime.after(sessionExpiresAt)
    }

    override fun endUserSession(context: Context) {
        clearStoredData(context)
    }

    private fun getExpiryDateFromPreferences(context: Context): Long? {
        return context.getSharedPreferences(SESSION_PREFERENCES, 0).
        getLong(SESSION_EXPIRY_TIME, 0)
    }

    private fun clearStoredData(context: Context) {
        val editor = context.getSharedPreferences(SESSION_PREFERENCES, 0).edit()
        editor.clear()
        editor.apply()
    }
}