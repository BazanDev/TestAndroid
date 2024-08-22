package com.factumex.prueba

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Configuración de hilt para inyección en toda la app
 */
@HiltAndroidApp
class JetpackComposeApp: Application() {
}