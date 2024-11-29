package com.bootcamp.ejerciciocatorcekotlin.view


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.bootcamp.ejerciciocatorcekotlin.components.CustomButton
import com.bootcamp.ejerciciocatorcekotlin.components.Space
import com.bootcamp.ejerciciocatorcekotlin.state.Patient
import com.bootcamp.ejerciciocatorcekotlin.components.SegmentedButtonSingleSelect
import com.bootcamp.ejerciciocatorcekotlin.viewmodel.PatientViewModel

import com.bootcamp.ejerciciocatorcekotlin.viewmodel.CalcularViewModel


@Composable
fun HomeView(navController: NavController, patientId: String?,
             patientviewModel: PatientViewModel = viewModel(),
             calcularViewModel: CalcularViewModel = viewModel()
) {


    val state = calcularViewModel.state.value
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(-1) }

    if (state.errorMessage.isNotEmpty()) {
    dialogMessage = state.errorMessage
      showDialog = true
    }

    // Determinar si mostrar el botón Guardar
    // val showSaveButton = state.resultadoIMC.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showDialog) {
            AlertDialog(onDismissRequest = { showDialog = false },
                title = { Text("¡CUIDADO!") },
                text = { Text(dialogMessage) },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Entendido")
                    }
                })
        }





        Text(
            text = "Calculadora de IMC",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        SegmentedButtonSingleSelect(
            selectedIndex = selectedIndex,
            onSelectedIndexChange = { selectedIndex = it })

        Space()

        OutlinedTextField(
            value = state.edad,
            onValueChange = { calcularViewModel.onEdadChanged(it) },
            label = { Text("Edad (años)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // Solo permite números
            )
        )




        Space()

        OutlinedTextField(
            value = state.peso,
            onValueChange = { calcularViewModel.onPesoChanged(it) },
            label = { Text("Peso (Kg)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // Solo permite números
            )

        )

        Space()


        OutlinedTextField(
            value = state.altura,
            onValueChange = { calcularViewModel.onAlturaChanged(it) },
            label = { Text("Altura (cm)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // Solo permite números
            )
        )


        Space()



        Button(
            onClick = {
                if (calcularViewModel.validarDatos()) {
                    calcularViewModel.calcularIMC()
                } else {
                    dialogMessage =
                        "No te olvides de llenar todos los campos con los datos solicitados."
                    showDialog = true
                    // Mostrar el AlertDialog si falta algún campo


                }
            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Calcular")
        }


        Space()

        Text(
            text = "IMC: ${state.resultadoIMC}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(36.dp),
            fontSize = 50.sp
        )
        Text(
            text = "Estado: ${state.estadoSalud}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(36.dp),
            fontSize = 20.sp
        )


        Space()

        if (state.resultadoIMC.isNotEmpty() && state.estadoSalud.isNotEmpty()) {
            CustomButton(
                text = "Guardar", modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                patientviewModel.actualizarPaciente(
                    patientId,
                    edad = state.edad,
                    sexo = selectedIndex,
                    imc = state.resultadoIMC,
                    estadoSalud = state.estadoSalud
                )
            }
                navController.navigate("patients")
            }
            }
        }










// patientId?.toIntOrNull()?.let { id ->
// val estadoSalud = calcularViewModel.imcEstado(state.resultadoIMC)////if (patientId != null) {
//                    val estadoSalud = calcularViewModel.imcEstado(state.resultadoIMC)
//                    val viewModel: PatientViewModel = viewModel()
//
//
//                    viewModel.actualizarPaciente(
//                        id = patientId,
//                        edad = state.edad,
//                        genero = state.genero,
//                        imc = state.resultadoIMC,
//                        estadoSalud = estadoSalud
//                    )