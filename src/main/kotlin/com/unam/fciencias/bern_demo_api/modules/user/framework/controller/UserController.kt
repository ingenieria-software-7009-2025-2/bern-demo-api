package com.unam.fciencias.bern_demo_api.modules.user.framework.controller

import com.unam.fciencias.bern_demo_api.modules.user.data.local.UserDataSourceLocal
import com.unam.fciencias.bern_demo_api.modules.user.data.local.entity.User
import com.unam.fciencias.bern_demo_api.modules.user.domain.Usuario
import com.unam.fciencias.bern_demo_api.modules.user.framework.payload.UserPayload
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/user")
class UserController(var userDataSourceLocal: UserDataSourceLocal) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<Any> {
        val result = userDataSourceLocal.findAll()
        return ResponseEntity.ok(result)
    }

    @PostMapping
    fun createUser(@RequestBody userPayload: UserPayload): ResponseEntity<Any> {
        //Convertir los datos del request a un objeto del dominio
        val usuario = Usuario(nombre = userPayload.nombre, password = userPayload.password, correo = userPayload.mail)


        //Covertir el objeto del dominio hacia un objeto de nuestra BD
        val usuarioDB = User(name = usuario.nombre, password = usuario.password!!, mail = usuario.correo, token = usuario.token)

        val result = userDataSourceLocal.save(usuarioDB)
        return ResponseEntity.ok(result)
    }
}