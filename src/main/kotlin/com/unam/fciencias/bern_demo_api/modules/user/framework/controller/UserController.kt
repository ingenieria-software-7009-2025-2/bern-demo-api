package com.unam.fciencias.bern_demo_api.modules.user.framework.controller

import com.unam.fciencias.bern_demo_api.modules.user.data.local.UserDataSourceLocal
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/v1/users")
class UserController(var userDataSourceLocal: UserDataSourceLocal) {

    @GetMapping
    fun getAllUsers(): ResponseEntity<Any> {
        val result = userDataSourceLocal.findAll()
        return ResponseEntity.ok(result)
    }
}