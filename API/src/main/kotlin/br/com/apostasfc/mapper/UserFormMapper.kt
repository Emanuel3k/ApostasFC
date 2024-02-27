package br.com.apostasfc.mapper

import br.com.apostasfc.dto.UserForm
import br.com.apostasfc.models.User
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserFormMapper : Mapper<UserForm, User> {

    override fun map(t: UserForm): User {
        val user = User()
        user.username = t.username
        user.password = t.password
        return user
    }

}