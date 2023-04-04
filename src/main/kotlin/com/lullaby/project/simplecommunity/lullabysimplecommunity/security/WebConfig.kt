package com.lullaby.project.simplecommunity.lullabysimplecommunity.security

import com.lullaby.project.simplecommunity.lullabysimplecommunity.adapter.`in`.web.JwtAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class WebConfig(
    val JwtAuthFilter: JwtAuthFilter
) {
    @Bean
    fun security(http: HttpSecurity): SecurityFilterChain {
        return http.csrf().disable()
            .cors().disable()
            .authorizeHttpRequests {authorize ->
                authorize
                    .shouldFilterAllDispatcherTypes(false)
                    .requestMatchers("/", "/auth/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .addFilterAfter(this.JwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            }
            .build()
    }

}