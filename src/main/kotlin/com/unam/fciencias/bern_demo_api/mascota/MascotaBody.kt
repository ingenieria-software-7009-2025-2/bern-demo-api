package com.unam.fciencias.bern_demo_api.mascota

data class MascotaBody(
    val tipo: String = "",
    val name: String = "",
    val peso: String = ""
)