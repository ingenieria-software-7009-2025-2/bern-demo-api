package com.unam.fciencias.bern_demo_api.user.repository

import com.unam.fciencias.bern_demo_api.user.repository.entity.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {

    @Query(value = "SELECT * FROM usuario WHERE correo=?1", nativeQuery = true)
    fun findByEmail(email: String): User
}