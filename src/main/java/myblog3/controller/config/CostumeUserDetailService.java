package myblog3.controller.config;

import myblog3.entity.Role;
import myblog3.entity.User;
import myblog3.repository.AuthRepsotiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class CostumeUserDetailService implements UserDetailsService {

    @Autowired
    private AuthRepsotiry authRepsotiry;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepsotiry.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found With:" + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
