package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.forum.repository.UsuarioRepository;


@Configuration
public class SecurityConfiguration {
	
	 	//@Autowired
	    private AutenticacaoService autenticacaoService;

	    @Autowired
	    private TokenService tokenService;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }

	    @Bean
	    public PasswordEncoder encoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	        .antMatchers(HttpMethod.GET, "/topicos").permitAll()
	        .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
	        .antMatchers(HttpMethod.POST, "/auth").permitAll()
	        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
	        .antMatchers("/v3/api-docs/**").permitAll()
	        .anyRequest().authenticated()
	        .and().csrf().disable()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        //.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService), UsernamePasswordAuthenticationFilter.class);
	        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
	       

	        return http.build();
	    }

	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers("/**.html", "/v3/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**",
                    "/swagger-ui/**", "/bus/v3/api-docs/**");
	    }
	    
	    public static void main(String[] args) {
	    	System.out.println(new BCryptPasswordEncoder().encode("123456"));
	    }

}
