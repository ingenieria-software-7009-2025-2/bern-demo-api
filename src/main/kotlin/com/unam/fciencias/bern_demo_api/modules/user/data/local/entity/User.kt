package com.unam.fciencias.bern_demo_api.modules.user.data.local.entity

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
class User constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int =0,
    @Column(name = "nombre")
    val name: String ="",
    @Column(name = "password")
    val password: String="",
    @Column(name = "correo")
    val mail: String="",
    @Column(name = "token")
    val token: String=""
) {


}