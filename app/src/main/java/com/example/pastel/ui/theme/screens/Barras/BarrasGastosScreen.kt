package com.example.pastel.ui.theme.screens.Barras

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
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.columnSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.Fill
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent

@Composable
fun BarrasGastosScreen(modifier: Modifier = Modifier) {
    val meses = listOf("Enero", "Febrero", "Marzo")
    var mesSeleccionado by remember { mutableStateOf("Enero") }
    val gastosMes = remember(mesSeleccionado) {
        getGastosWalletView.gastosDelMes(mesSeleccionado)
    }
    val modelProducer = remember { CartesianChartModelProducer() }

    LaunchedEffect(gastosMes) {
        modelProducer.runTransaction {
            columnSeries {
                series(gastosMes.map { it.monto })
            }
        }
    }

    val columnas = gastosMes.map { gasto ->
        rememberLineComponent(fill = Fill(gasto.color))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "WalletView · Comparativa mensual (Vico)",
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

        CartesianChartHost(
            chart = rememberCartesianChart(
                rememberColumnCartesianLayer(
                    columnProvider = ColumnCartesianLayer.ColumnProvider.series(columnas)
                ),
                startAxis = VerticalAxis.rememberStart(),
                bottomAxis = HorizontalAxis.rememberBottom()
            ),
            modelProducer = modelProducer,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Categorías en $mesSeleccionado: ${gastosMes.joinToString { it.categoria }}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}