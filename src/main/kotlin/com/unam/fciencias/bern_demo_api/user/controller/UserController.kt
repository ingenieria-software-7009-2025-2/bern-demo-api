package com.unam.fciencias.bern_demo_api.user.controller

import com.unam.fciencias.bern_demo_api.user.controller.body.LoginUserBody
import com.unam.fciencias.bern_demo_api.user.controller.body.UserBody
import com.unam.fciencias.bern_demo_api.user.repository.UserRepository
import com.unam.fciencias.bern_demo_api.user.domain.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/users")
class UserController(var userRepository: UserRepository) {

    @PostMapping()
    fun addUser(@RequestBody userBody: UserBody): ResponseEntity<Usuario> {
        val usuario = Usuario(nombre = userBody.nombre, correo = userBody.mail, password = userBody.password)
        return ResponseEntity.ok(usuario)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginUserBody: LoginUserBody): ResponseEntity<Usuario> {
        val usuario = Usuario(correo = loginUserBody.mail, password = loginUserBody.password, nombre = "")
        return ResponseEntity.ok(usuario)
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        val sessionClosed = "Sesión finalizada"
        return ResponseEntity.ok(sessionClosed)
    }

    @GetMapping("/me")
    fun me(): ResponseEntity<Usuario> {
        val userFake = Usuario(nombre = "Luis Bernabe", correo = "luis_berna@ciencias.unam.mx")
        return ResponseEntity.ok(userFake)
    }

    /*@GetMapping
    fun getAllUsers(): ResponseEntity<Any> {
        val result = userRepository.findAll()
        return ResponseEntity.ok(result)
    }*/
}