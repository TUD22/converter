package com.example.gr1converter

import android.graphics.Outline
import android.os.Bundle
import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gr1converter.ui.theme.Gr1ConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gr1ConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    val CENT="Cntimerets"
    val INCHES="Inches"
    val FEETS="Feets"

    val conversionFactors= mapOf(
        Pair(CENT, INCHES) to 0.393701,
        Pair(CENT, FEETS) to 0.0328084,
        Pair(FEETS, CENT) to 30.48,
        Pair(FEETS, INCHES) to 12.00,
        Pair(INCHES, FEETS) to 0.0833333,
        Pair(INCHES, CENT) to 2.54
    )

    var inputUnit by remember {mutableStateOf(CENT)}
    var outputUnit by remember {mutableStateOf(CENT)}
    var inExpended by remember { mutableStateOf(false)}
    var outExpended by remember { mutableStateOf(false)}
    var inputNumber by remember { mutableStateOf("")}
    var result by remember { mutableStateOf("0") }

    fun convertUnits(){
        val inputNumberDouble = inputNumber.toDoubleOrNull() ?: 0.0
        val key = Pair(inputUnit, outputUnit)
        val factor = conversionFactors[key] ?: 1.0
        val resultDouble = inputNumberDouble*factor
        result = resultDouble.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Unit Converter")
        OutlinedTextField(value = inputNumber, onValueChange = {inputNumber= it
            convertUnits()})
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box {
                Button(onClick = { inExpended= true }) {
                    Text(text = inputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "arrow")
                }
                DropdownMenu(expanded = inExpended, onDismissRequest = { inExpended=false }) {
                    DropdownMenuItem(text = { Text(text = CENT)}, onClick = { inExpended = false
                    inputUnit= CENT
                        convertUnits()})
                    DropdownMenuItem(text = { Text(text = INCHES)}, onClick = { inExpended = false
                        inputUnit= INCHES
                        convertUnits()})
                    DropdownMenuItem(text = { Text(text = FEETS)}, onClick = { inExpended = false
                        inputUnit= FEETS
                        convertUnits()})
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box {
                Button(onClick = { outExpended= true }) {
                    Text(text = outputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "arrow")
                }
                DropdownMenu(expanded = outExpended, onDismissRequest = { outExpended=false }) {
                    DropdownMenuItem(text = { Text(text = CENT)}, onClick = { outExpended = false
                        outputUnit= CENT
                        convertUnits()})
                    DropdownMenuItem(text = { Text(text = INCHES)}, onClick = { outExpended = false
                        outputUnit= INCHES
                        convertUnits()})
                    DropdownMenuItem(text = { Text(text = FEETS)}, onClick = { outExpended = false
                        outputUnit= FEETS
                        convertUnits()})
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Result: $result")
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Gr1ConverterTheme {
        UnitConverter()
    }
}