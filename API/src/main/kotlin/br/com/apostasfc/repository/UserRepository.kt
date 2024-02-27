package br.com.apostasfc.repository

import br.com.apostasfc.models.User
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository

interface UserRepository : PanacheRepository<User>