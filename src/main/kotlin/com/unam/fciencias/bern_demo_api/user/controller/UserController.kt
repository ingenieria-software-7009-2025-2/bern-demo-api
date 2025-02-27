package com.unam.fciencias.bern_demo_api.user.controller

import com.unam.fciencias.bern_demo_api.user.controller.body.LoginUserBody
import com.unam.fciencias.bern_demo_api.user.controller.body.UserBody
import com.unam.fciencias.bern_demo_api.user.repository.UserRepository
import com.unam.fciencias.bern_demo_api.user.domain.Usuario
import com.unam.fciencias.bern_demo_api.user.repository.entity.User
import com.unam.fciencias.bern_demo_api.user.service.UserService
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/users")
class UserController(var userService: UserService) {

    @PostMapping
    fun addUser(@RequestBody userBody: UserBody): ResponseEntity<Any> {
        //Convertir los datos del request a un objeto del dominio
        val usuario = Usuario(nombre = userBody.nombre, password = userBody.password, correo = userBody.mail)

        val response = userService.addUser(usuario)
        return ResponseEntity.ok(response)

    }

    @PostMapping("/login")
    fun login(@RequestBody loginUserBody: LoginUserBody): ResponseEntity<Usuario> {
        val result = userService.login(loginUserBody.mail, loginUserBody.password)

        if (result == null) {
            return ResponseEntity.notFound().build()
        } else {
            return ResponseEntity.ok(result)
        }

    }

    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") token: String): ResponseEntity<String> {
        val successLogout = userService.logout(token)
        if (!successLogout) {
            return ResponseEntity.badRequest().build()
        } else {
            val sessionClosed = "Sesión finalizada"
            return ResponseEntity.ok(sessionClosed)
        }
    }

    @GetMapping("/me")
    fun me(@RequestHeader("Authorization") token: String): ResponseEntity<Usuario> {
        val response =userService.getInfoAboutMe(token)
        return if (response != null) {
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(401).build()
        }
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<Any> {
        val result = userService.retrieveAllUser()
        return ResponseEntity.ok(result)
    }
}