package com.unam.fciencias.bern_demo_api.user.controller

import com.unam.fciencias.bern_demo_api.user.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/users")
class UserController(var userRepository: UserRepository) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<Any> {
        val result = userRepository.findAll()
        return ResponseEntity.ok(result)
    }
}