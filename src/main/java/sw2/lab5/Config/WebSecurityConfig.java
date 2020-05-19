package sw2.lab5.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception  {
        http.formLogin();
        http.authorizeRequests().antMatchers("/post").hasAnyAuthority("admin","user");
        http.authorizeRequests().antMatchers("/post/delete").hasAuthority("admin");
        http.authorizeRequests().antMatchers("/post/create").hasAnyAuthority("admin","user");
        http.authorizeRequests().antMatchers("/post/edit").hasAnyAuthority("admin","user");
        http.authorizeRequests().antMatchers("/user").hasAnyAuthority("admin","user");
        http.authorizeRequests().antMatchers("/user/list").hasAuthority("admin");
        http.authorizeRequests().antMatchers("/user/edit").hasAnyAuthority("admin","user");
        http.authorizeRequests().anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT email,password,active FROM lab5.user where email = ?")
                .authoritiesByUsernameQuery("SELECT u.email, r.role_name FROM lab5.user u INNER JOIN " +
                        "lab5.role r ON (u.id_role = r.id_role) WHERE u.email = ? and u.active = 1");
    }
}