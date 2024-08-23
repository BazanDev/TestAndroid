package com.factumex.prueba

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.rememberNavController
import com.factumex.prueba.presentation.ui.navigation.AppScreens
import com.factumex.prueba.presentation.ui.views.ActividadUnoView
import org.junit.Rule
import org.junit.Test

class ActividadUnoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDefaultValues() {
        composeTestRule.setContent {
            // Renderiza la vista que quieres probar
            ActividadUnoView(navHostController = rememberNavController())
        }

        // Verificar que la imagen circular con el nombre vacío se renderiza correctamente
        composeTestRule.onNodeWithText("Nombre")
            .assertExists()

        // Verificar que los sliders se encuentran en la pantalla
        composeTestRule.onNodeWithText("Rojo")
            .assertExists()
        composeTestRule.onNodeWithText("Verde")
            .assertExists()
        composeTestRule.onNodeWithText("Azul:")
            .assertExists()
    }


    @Test
    fun testUrlInput() {
        composeTestRule.setContent {
            ActividadUnoView(navHostController = rememberNavController())
        }

        // Simular ingreso de una URL en el campo de texto
        val testUrl = "https://example.com/image.png"
        composeTestRule.onNodeWithText("URL de la Imagen")
            .performTextInput(testUrl)

        // Verificar que el valor ingresado se refleja en el campo de texto
        composeTestRule.onNodeWithText(testUrl)
            .assertExists()
    }

}