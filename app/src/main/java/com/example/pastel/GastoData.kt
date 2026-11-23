package com.example.pastel

import androidx.compose.ui.graphics.Color

data class GastoMensual(
    val categoria: String,
    val mes: String,
    val monto: Float,
    val color: Color
)


val getGastosWalletView = listOf(
    // Enero
    GastoMensual("Vivienda", "Enero", 6500f, Color(0xFF1565C0)),
    GastoMensual("Alimentación", "Enero", 3400f, Color(0xFF2E7D32)),
    GastoMensual("Transporte", "Enero", 1500f, Color(0xFFEF6C00)),
    GastoMensual("Servicios", "Enero", 1200f, Color(0xFF6A1B9A)),
    // Febrero
    GastoMensual("Vivienda", "Febrero", 6500f, Color(0xFF1565C0)),
    GastoMensual("Alimentación", "Febrero", 3850f, Color(0xFF2E7D32)),
    GastoMensual("Transporte", "Febrero", 1300f, Color(0xFFEF6C00)),
    GastoMensual("Servicios", "Febrero", 1450f, Color(0xFF6A1B9A)),
    // Marzo
    GastoMensual("Vivienda", "Marzo", 6500f, Color(0xFF1565C0)),
    GastoMensual("Alimentación", "Marzo", 3100f, Color(0xFF2E7D32)),
    GastoMensual("Transporte", "Marzo", 1750f, Color(0xFFEF6C00)),
    GastoMensual("Servicios", "Marzo", 1150f, Color(0xFF6A1B9A))
)

fun List<GastoMensual>.gastosDelMes(mes: String): List<GastoMensual> =
    filter { it.mes.equals(mes, ignoreCase = true) }