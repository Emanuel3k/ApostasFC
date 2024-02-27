package br.com.apostasfc.controller

import br.com.apostasfc.dto.UserForm
import br.com.apostasfc.service.UserService
import jakarta.annotation.security.PermitAll
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.Response

@ApplicationScoped
@Path("/")
class UserController {

    @Inject
    private lateinit var userService: UserService

    @GET
    @Path("login")
    @PermitAll
    fun login(@Valid userForm: UserForm): Response {
        return try {
            userService.login(userForm)
        } catch (err: Exception) {
            Response.serverError().entity(err.message).build()
        }
    }

    @POST
    @PermitAll
    @Path("register")
    @Transactional
    fun createUser(@Valid userForm: UserForm): Response {
        return try {
            userService.register(userForm)
        } catch (err: Exception) {
            Response.serverError().entity(err.message).build()
        }
    }
}