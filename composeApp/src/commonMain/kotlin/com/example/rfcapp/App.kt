package com.example.rfcapp

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
    fun App() {
    var nombre by remember { mutableStateOf("") }
    var apellidoPaterno by remember { mutableStateOf("") }
    var apellidoMaterno by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    val rfc = calcularRFC(nombre, apellidoPaterno, apellidoMaterno, fecha)
    val rfcConstruccion = rfcEnConstruccion(nombre, apellidoPaterno, apellidoMaterno, fecha)


    MaterialTheme {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Card(
                modifier = Modifier.width(450.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {

                Column(
                    modifier = Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "Calculadora RFC",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // RFC visible grande
                    Text("RFC generado", style = MaterialTheme.typography.labelLarge)

                    Text(
                        rfc,
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    if (rfc == "Datos incompletos") {

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Se está construyendo:")

                        Text(
                            rfcConstruccion,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre(s)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = apellidoPaterno,
                        onValueChange = { apellidoPaterno = it },
                        label = { Text("Apellido paterno") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = apellidoMaterno,
                        onValueChange = { apellidoMaterno = it },
                        label = { Text("Apellido materno") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = fecha,
                        onValueChange = { fecha = it },
                        label = { Text("Fecha nacimiento (YYYY-MM-DD)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}