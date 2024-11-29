package com.bootcamp.ejerciciocatorcekotlin.viewmodel


import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.bootcamp.ejerciciocatorcekotlin.state.IMCState



class CalcularViewModel: ViewModel() {

    var state = mutableStateOf(IMCState())
        private set



    fun onEdadChanged(edad: String) {
        state.value = state.value.copy(edad = edad)
    }

    fun onPesoChanged(peso: String) {
        state.value = state.value.copy(peso = peso)
    }

    fun onAlturaChanged(altura: String) {
        state.value = state.value.copy(altura = altura)
    }






    fun validarDatos(): Boolean {
        val pesoNum = state.value.peso.toFloatOrNull()
        val alturaNum = state.value.altura.toFloatOrNull()
        val edadNum = state.value.edad.toIntOrNull()

        return pesoNum != null && pesoNum > 0 &&
                alturaNum != null && alturaNum > 0 &&
                edadNum != null && edadNum > 0
    }



    fun estadoSalud(imc: String): String {
        val imcValue = imc.toFloatOrNull() ?: return "Valor no válido"
        return when {
            imcValue < 18.5 -> "Bajo peso"
            imcValue in 18.5..24.9 -> "Peso normal"
            imcValue in 25.0..29.9 -> "Sobrepeso"
            imcValue in 30.0..34.9 -> "Obesidad I"
            imcValue in 35.0..39.9 -> "Obesidad II"
            imcValue >= 40 -> "Obesidad III"
            else -> "Valor no válido"
        }
    }

    fun calcularIMC() {
        val pesoNum = state.value.peso.toFloatOrNull() ?:0f
        val alturaNum = state.value.altura.toFloatOrNull()?.div(100)  ?:0f// Convertimos cm a m
       val resultadoIMC = if (alturaNum > 0) {
            val imc = pesoNum / (alturaNum * alturaNum)
           String.format("%.1f", imc)
        } else {
            "0.0"
        }

        state.value = state.value.copy(resultadoIMC = resultadoIMC)
        state.value = state.value.copy(estadoSalud = estadoSalud(resultadoIMC))
}

        }



