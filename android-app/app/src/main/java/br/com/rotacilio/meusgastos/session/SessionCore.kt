package br.com.rotacilio.meusgastos.session

import android.content.Context
import java.util.*

interface SessionCore {
    fun startUserSession(context: Context, expiresIn: Int)
    fun isSessionActive(currentTime: Date, context: Context): Boolean
    fun endUserSession(context: Context)
}