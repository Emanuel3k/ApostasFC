package br.com.apostasfc.utils

import br.com.apostasfc.models.Roles
import io.smallrye.jwt.build.Jwt
import io.smallrye.jwt.util.KeyUtils.readPrivateKey
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class TokenUtils {

    // Jwt generation
    fun generateToken(username: String, role: Roles): String {

        return  try {
            val issuer = "http://127.0.0.1:8080/login" // Issuer of my jwt
            val privateKeyLocation = "/privatekey.pem" //Location of my private key
            val privateKey = readPrivateKey(privateKeyLocation)  //Private key reading

            Jwt.issuer(issuer).subject(username).groups(role.name).expiresAt(System.currentTimeMillis() + 3600)
                .sign(privateKey)
        } catch (err: Exception) {
            err.message.toString()
        }


    }

}