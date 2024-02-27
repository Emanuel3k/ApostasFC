package br.com.apostasfc.service

import br.com.apostasfc.dto.UserForm
import br.com.apostasfc.mapper.UserFormMapper
import br.com.apostasfc.models.Roles
import br.com.apostasfc.models.User
import br.com.apostasfc.repository.UserRepository
import br.com.apostasfc.utils.TokenUtils
import br.com.apostasfc.utils.UserUtils
import io.quarkus.elytron.security.common.BcryptUtil
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.core.Response
import java.net.URI

@ApplicationScoped
class UserService : UserRepository {

    // Class injection
    @Inject
    private lateinit var userFormMapper: UserFormMapper

    // Class injection
    @Inject
    private lateinit var userUtils: UserUtils

    // Class injection
    @Inject
    private lateinit var tokenUtils: TokenUtils

    // User login
    fun login(@Valid userForm: UserForm): Response {

        // Checks if the username is registered in the database
        val user = this.find("username", userForm.username).firstResult()
            ?: return Response.status(Response.Status.NOT_FOUND).entity("Incorrect username or password.")
                .build()

        // Checks if the password sent by the user is the same as the one in the database
        if (!BcryptUtil.matches(userForm.password, user.password)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Incorrect username or password.").build()
        }

        // Jwt generation
        val token = tokenUtils.generateToken(user.username, user.role)

        return Response.ok(token).build()
    }

    // User registration
    fun register(@Valid userForm: UserForm): Response {
        // Convert a userform to usermodel
        val user: User = userFormMapper.map(userForm)

        // Checks if the username is a letter or number
        if (!userUtils.usernameIsValid(user.username)) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Only letters and numbers allowed in username.").build()
        }

        // Checks if there is already a user on the system with that username
        if (this.find("username", user.username).firstResult() != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Username: ${user.username} is already in use ")
                .build()
        }

        // Defines the user's role
        user.role = Roles.USER
        // Encrypt password before going to database
        user.password = BcryptUtil.bcryptHash(user.password)
        // Persists data in the database
        this.persist(user)
        return Response.created(URI.create("/register/${user.id}")).build()
    }
}