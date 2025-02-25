package com.unam.fciencias.bern_demo_api.user.repository

import com.unam.fciencias.bern_demo_api.user.repository.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
}