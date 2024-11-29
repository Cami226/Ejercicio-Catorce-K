package com.bootcamp.ejerciciocatorcekotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


import com.bootcamp.ejerciciocatorcekotlin.ui.theme.EjercicioCatorceKotlinTheme

import com.bootcamp.ejerciciocatorcekotlin.navigation.Navigation


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioCatorceKotlinTheme {

                Navigation()
                }
            }
        }
    }


