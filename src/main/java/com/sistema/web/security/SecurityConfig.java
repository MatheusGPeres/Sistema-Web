package com.sistema.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Informa que essa é uma classe de configuração do Spring
@EnableWebSecurity // Ativa o Spring Security
public class SecurityConfig {

    @Bean // Define um bean do tipo SecurityFilterChain (a cadeia de filtros de segurança)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth //auth -> variável usada como parametro de lambda
                                //permite definir regras de segurança
                                .requestMatchers("/cadastro", "/login", "/css/**").permitAll() //patterns permitidos sem login
                                .requestMatchers("/produtos/**").hasRole("USER") //apenas usuários com a role USER podem acessar URLs que começam com /produtos/
                                .anyRequest().authenticated() //qualquer outra requisição precisa de autenticação
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/produtos/dashboard", true)
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


            .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll())
                .exceptionHandling(handling -> handling.accessDeniedPage("/403"));


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public UserDetailsService userDetailsService(UserRepository userRepository) {
    //     return username -> {
    //         User user = userRepository.findByUsername(username);
    //         if (user == null) {
    //             throw new UsernameNotFoundException("Usuário não encontrado");
    //         }
    //         return new org.springframework.security.core.userdetails.User(
    //             user.getUsername(),
    //             user.getSenha(),
    //             Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
    //         );
    //     };
    // }


    // @Bean 
    // public UserDetailsService userDetailsService() { 
    //     UserDetails user = org.springframework.security.core.userdetails.User
    //         .withDefaultPasswordEncoder()
    //         .nome("admin")
    //         .password("1234")
    //         .roles("USER")
    //         .build();

    //     return new InMemoryUserDetailsManager(user);

    // } 

}