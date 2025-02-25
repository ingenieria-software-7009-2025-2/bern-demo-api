package com.unam.fciencias.bern_demo_api.user.repository.entity

import jakarta.persistence.*

@Entity
@Table(name = "usuario")
class User constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int =0,
    @Column(name = "nombre")
    var name: String ="",
    @Column(name = "password")
    var password: String="",
    @Column(name = "correo")
    var mail: String="",
    @Column(name = "token")
    var token: String? = null
) {


}