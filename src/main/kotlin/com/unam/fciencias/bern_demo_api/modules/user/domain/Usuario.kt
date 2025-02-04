package com.unam.fciencias.bern_demo_api.modules.user.domain

data class Usuario(
    var id: String? = null,
    var nombre: String,
    var correo: String,
    var token: String? = null,
    var password: String? = null
)
