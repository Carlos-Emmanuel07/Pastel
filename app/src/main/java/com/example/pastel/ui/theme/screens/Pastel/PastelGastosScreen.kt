package com.example.pastel.ui.theme.screens.Pastel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pastel.gastosDelMes
import com.example.pastel.getGastosWalletView
import com.patrykandpatrick.vico.compose.common.Fill
import com.patrykandpatrick.vico.compose.pie.PieChartHost
//import com.patrykandpatrick.vico.compose.pie.PieChartModelProducer //no borrarlo por si acaso
import com.patrykandpatrick.vico.compose.pie.PieChart
import com.patrykandpatrick.vico.compose.pie.data.PieChartModelProducer
import com.patrykandpatrick.vico.compose.pie.data.pieModel
import com.patrykandpatrick.vico.compose.pie.rememberPieChart

@Composable
fun PastelGastosScreen(modifier: Modifier = Modifier) {
    val meses = listOf("Enero", "Febrero", "Marzo")
    var mesSeleccionado by remember { mutableStateOf("Enero") }
    val gastosMes = remember(mesSeleccionado) {
        getGastosWalletView.gastosDelMes(mesSeleccionado)
    }
    val modelProducer = remember { PieChartModelProducer() }

    LaunchedEffect(gastosMes) {
        modelProducer.runTransaction {
            pieModel {
                series(gastosMes.map { it.monto.toDouble() })
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "WalletView · Gastos por categoría (Vico)",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            meses.forEach { mes ->
                FilterChip(
                    selected = mes == mesSeleccionado,
                    onClick = { mesSeleccionado = mes },
                    label = { Text(mes) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val slices = gastosMes.map { gasto ->
            PieChart.Slice(fill = Fill(gasto.color))
        }

        PieChartHost(
            chart = rememberPieChart(
                sliceProvider = PieChart.SliceProvider.series(slices)
            ),
            modelProducer = modelProducer,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        val totalMes = gastosMes.sumOf { it.monto.toDouble() }
        Text(
            text = "Total $mesSeleccionado: $${"%.2f".format(totalMes)}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}