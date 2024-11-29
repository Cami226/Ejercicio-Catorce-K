package com.bootcamp.ejerciciocatorcekotlin.viewmodel


import androidx.lifecycle.ViewModel
import com.bootcamp.ejerciciocatorcekotlin.state.Patient

import androidx.compose.runtime.mutableStateListOf


class PatientViewModel : ViewModel() {


    private val _pacientes = mutableStateListOf<Patient>()
    val pacientes: List<Patient> get() = _pacientes

    fun agregarPaciente(name: String) {
        val newId = _pacientes.size + 1 // Genera un ID único
        _pacientes.add(
            Patient(
                id = newId,
                name = name, edad = "",
                sexo = null, imc = "", estadoSalud = ""
            )
        )
    }

    fun actualizarPaciente(
        patientId: String?,
        edad: String,
        sexo: Int,
        imc: String,
        estadoSalud: String
    ) {
        val id = patientId?.toIntOrNull()
        if (id != null && id > 0 && id <= _pacientes.size) {


            val patient = _pacientes[id - 1]
            val actualizarPaciente = patient.copy(
                edad = edad, imc = imc, sexo = sexo,
                estadoSalud = estadoSalud
            )
            _pacientes[id - 1] = actualizarPaciente
        }
    }
}



        //val paciente = _pacientes[id-1]
        //val actualizarPaciente =paciente.copy(edad = edad, imc= imc, genero= genero,
          //  estadoSalud = estadoSalud)
            //_pacientes[id-1] = actualizarPaciente




// ULTIMO CORREGIDO Y FUNCIONAL
// val paciente = _pacientes.find { it.id == id }
//paciente?.let {
//  _pacientes[_pacientes.indexOf(it)] = it.copy(
//    edad = edad,
//  genero = genero,
//  imc = imc,
// estadoSalud = estadoSalud





//PREVIOOOOO val newPatient = Patient(id = _pacientes.size + 1, name = name)
//_pacientes.add(newPatient, edad = null,
//  genero =null, imc = "", imcEstado = "")