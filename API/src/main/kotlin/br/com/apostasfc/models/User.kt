package br.com.apostasfc.models

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import io.quarkus.security.jpa.Password
import io.quarkus.security.jpa.Username
import jakarta.persistence.*
import java.util.*


@Entity
@Table(name = "users")
class User : PanacheEntityBase {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID

    @Column(unique = true)
    @Username
    lateinit var username: String

    @Column
    @Password
    lateinit var password: String

    @Column
    @Enumerated
    lateinit var role: Roles

}

enum class Roles {
    ADMIN, USER
}