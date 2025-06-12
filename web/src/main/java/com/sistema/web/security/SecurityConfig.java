package com.sistema.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // Informa que essa é uma classe de configuração do Spring
@EnableWebSecurity // Ativa o Spring Security
public class SecurityConfig {

    @Bean // Define um bean do tipo SecurityFilterChain (a cadeia de filtros de segurança)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth //auth -> variável usada como parametro de lambda
                                                //permite definir regras de segurança 
                .requestMatchers("/login", "/css/**").permitAll() //patterns permitidos sem login
                .anyRequest().authenticated() //qualquer outra requisição precisa de autenticação
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
            ) 
                //             ✅ .loginPage("/login")
                // Define qual é a URL da página de login personalizada.

                // Você deve ter uma view chamada login.html no seu projeto, geralmente em src/main/resources/templates.

                // 🚀 .defaultSuccessUrl("/dashboard", true)
                // Diz qual será a página para onde o usuário será redirecionado após o login com sucesso.

                // true significa que vai sempre redirecionar para /dashboard, mesmo que o usuário tenha tentado acessar outra página antes do login.

                // 🌐 .permitAll()
                // Permite que qualquer pessoa (mesmo sem estar logada) acesse a URL de login (/login) e envie o formulário.


            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean 
    public UserDetailsService userDetailsService() { 
        UserDetails user = org.springframework.security.core.userdetails.User
            .withDefaultPasswordEncoder()
            .username("admin")
            .password("1234")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);


    } 

}