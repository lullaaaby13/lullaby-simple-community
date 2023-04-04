package com.lullaby.project.simplecommunity.lullabysimplecommunity.application.authenticate

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {
    val JWT_SECRET = "itsmysecret"
    val parser = Jwts.parser().setSigningKey(JWT_SECRET)
    val ACCESS_TOKEN_EXPIRATION_MS = 1000 * 60 * 60 // 3600초
    val REFRESH_TOKEN_EXPIRATION_MS = 1000 * 60 * 60 * 24 * 7 // 7일

    fun accessToken(userId: Long, role: String): String {
        val issuedAt = Date()
        val expiration = Date(issuedAt.time + ACCESS_TOKEN_EXPIRATION_MS)

        return buildToken(userId, role, issuedAt, expiration)
    }

    fun refreshToken(userId: Long, role: String): String {
        val issuedAt = Date()
        val expiration = Date(issuedAt.time + REFRESH_TOKEN_EXPIRATION_MS)

        return buildToken(userId, role, issuedAt, expiration)
    }

    private fun buildToken(userId: Long, role: String, issuedAt: Date, expiration: Date): String {
        val claims = Jwts.claims().setSubject(userId.toString())
        claims["role"] = role

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
            .compact()!!
    }
    fun validate(token: String): Boolean {
        try {
            parser.parseClaimsJws(token)
            return true
        } catch (exception: Exception) {
            println("JWT Token 검증 실패 ")
        }

        return false
    }

    fun parse(token: String): TokenBody {
        val claims = parser.parseClaimsJws(token)
        return TokenBody(
            userId = claims.body["sub"]?.toString()?.toLong() ?: throw Error(),
            role = claims.body["role"]?.toString() ?: throw Error(),
        )
    }

}