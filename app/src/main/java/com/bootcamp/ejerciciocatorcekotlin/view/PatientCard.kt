package com.bootcamp.ejerciciocatorcekotlin.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import com.bootcamp.ejerciciocatorcekotlin.state.Patient



@Composable
fun PatientCard(patient: Patient, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "ID : ${patient.id}")
                Text(text = "Nombre: ${patient.name}")

                // Mostrar los demás datos solo si no están vacíos
                if (patient.edad.isNotEmpty()) {
                    Text(text = "Edad: ${patient.edad}")
                    Spacer(modifier = Modifier.height(4.dp))
                }

                if (patient.sexo == 0) {
                    Text(
                        text = "Hombre", fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else if (patient.sexo == 1) {
                    Text(
                        text = "Mujer", fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (patient.imc.isNotEmpty()) {
                    Text (text = "IMC: ${patient.imc}")
                    Spacer(modifier = Modifier.height(4.dp))
                }
                if (patient.estadoSalud.isNotEmpty()) {
                    Text(text = "Estado: ${patient.estadoSalud}") }
            }

            // Mostrar el botón de calcular solo si el IMC no ha sido calculado
            if (patient.imc.isEmpty()) {
                Button(onClick = {
                    navController.navigate("home/${patient.id}")
                }) {
                    Text("VER IMC")
            }
        }
    }
        }
    }


