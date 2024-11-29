package com.bootcamp.ejerciciocatorcekotlin.state

data class IMCState (
                     val edad: String = "",
                     val peso: String = "",
                     val altura: String = "",
                     val resultadoIMC: String = "",
                     val errorMessage: String = "",
                     val estadoSalud: String = ""
)