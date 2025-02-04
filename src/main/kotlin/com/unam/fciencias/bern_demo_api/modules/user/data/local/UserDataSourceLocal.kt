package com.unam.fciencias.bern_demo_api.modules.user.data.local

import com.unam.fciencias.bern_demo_api.modules.user.data.local.entity.User
import org.springframework.data.repository.CrudRepository

interface UserDataSourceLocal : CrudRepository<User, Int> {
}