package com.unam.fciencias.bern_demo_api.user.service

import com.unam.fciencias.bern_demo_api.user.domain.Usuario
import com.unam.fciencias.bern_demo_api.user.repository.UserRepository
import com.unam.fciencias.bern_demo_api.user.repository.entity.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private var userRepository: UserRepository) {

    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

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
        logger.info("Desde el service el usuario creado es: $usuarioCreado")
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

    fun login(mail: String, password: String): Usuario? {
        val userFound = userRepository.findByEmailAndPassword(mail, password)

        return if (userFound != null) {
            val token = UUID.randomUUID().toString()
            updateTokenUser(userFound, token)
            Usuario(
                id = userFound.id.toString(),
                nombre = userFound.name,
                correo = userFound.mail,
                token = token,
                password = userFound.password,
                isActive = false
            )
        } else {
            userFound
        }

    }

    fun updateTokenUser(user: User, token: String) {
        user.token = token
        userRepository.save(user)
    }


    fun logout(token: String): Boolean {
        val userFound = userRepository.findByToken(token)

        if (userFound != null) {
            userFound.token = null
            userRepository.save(userFound)
            return true
        } else return false
    }

    fun getInfoAboutMe(token: String): Usuario?{
        val userFound = userRepository.findByToken(token)

        if (userFound != null) {
            return Usuario(
                id = userFound.id.toString(),
                nombre = userFound.name,
                correo = userFound.mail,
                token = "*******",
                password = userFound.password,
                isActive = false
            )
        } else return null
    }
}