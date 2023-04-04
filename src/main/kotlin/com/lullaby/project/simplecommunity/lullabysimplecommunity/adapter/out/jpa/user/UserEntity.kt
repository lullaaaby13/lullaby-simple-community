package com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.out.jpa.user

import com.lullaby.project.simplecommunity.lullabysimplecommunity.domain.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Table(name = "lullaby_community_user")
@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Long?,
    var account: String?,
    var password: String?,
    var createdAt: LocalDateTime,
    var lastLoggedInAt: LocalDateTime?,
) {
    fun toDomain(): User {
        return User(
            id = this.id,
            account = this.account!!,
            password = this.password!!,
            createdAt = this.createdAt,
            lastLoggedInAt = this.lastLoggedInAt,
        )
    }
}