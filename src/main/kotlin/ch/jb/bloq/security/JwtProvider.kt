package ch.jb.bloq.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.io.File
import java.security.Key
import java.time.LocalDateTime
import java.util.*

object JwtProvider {

    private val logger = LoggerFactory.getLogger(JwtProvider::class.java)

    private const val JWT_EXPIRATION_MS: Int = 864000000

    private val key: Key = getKey()

    private fun getKey(): Key {
        val jwtSecret = File("jwtsecret.key")

        return if (jwtSecret.exists()) {
            Keys.hmacShaKeyFor(jwtSecret.readBytes())
        } else {
            val newKey = Keys.secretKeyFor(SignatureAlgorithm.HS512)
            jwtSecret.also { it.createNewFile() }.writeBytes(newKey.encoded)
            newKey
        }
    }

    fun generateToken(auth: Authentication): String = Jwts.builder()
            .setSubject((auth.principal as UserPrincipal).id.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(Date().toInstant().epochSecond * 1000 + JWT_EXPIRATION_MS))
            .signWith(key)
            .compact()

    fun getUserIdFromJwt(jwt: String) = Jwts.parser()
            .setSigningKey(key)
            .parseClaimsJws(jwt)
            .body
            .subject
            .toLong()

    fun validateToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            logger.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            logger.error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            logger.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            logger.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            logger.error("JWT claims string is empty.")
        }

        return false
    }
}