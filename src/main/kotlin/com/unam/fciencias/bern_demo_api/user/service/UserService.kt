package com.unam.fciencias.bern_demo_api.user.service

import com.unam.fciencias.bern_demo_api.user.domain.Usuario
import com.unam.fciencias.bern_demo_api.user.repository.UserRepository
import com.unam.fciencias.bern_demo_api.user.repository.entity.User
import org.springframework.stereotype.Service

@Service
class UserService(var userRepository: UserRepository) {


    fun addUser(usuario: Usuario): Usuario {

        //Convertimos el objeto del dominio al objeto que necesita nuestra BD
        val usuarioDB =
            User(name = usuario.nombre, password = usuario.password!!, mail = usuario.correo, token = usuario.token)

        val result = userRepository.save(usuarioDB)

        // Convertimos el objeto de nuestra BD a un objeto de nuestro dominio.
        val usuarioCreado = Usuario(
            id = result.id.toString(),
            nombre = result.name,
            correo = result.mail,
            token = result.token,
            password = result.password,
            isActive = false
        )
        return usuarioCreado
    }


    fun retrieveAllUser(): List<Usuario> {

        val myUsers = mutableListOf<Usuario>()

        val result = userRepository.findAll()

        result.forEach { user: User ->
            // Convertimos el objeto de nuestra BD a un objeto de nuestro dominio.
            val userFound = Usuario(
                id = user.id.toString(),
                nombre = user.name,
                correo = user.mail,
                token = user.token,
                password = user.password,
                isActive = false
            )

            myUsers.add(userFound)
        }

        return myUsers
    }
}