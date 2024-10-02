package com.clinica.Odontologica.Service;

import java.util.ArrayList;
import java.util.List;

import com.clinica.Odontologica.Entity.UserEntity;
import com.clinica.Odontologica.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // Asignar roles
        user.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRol().name())));

        // Asignar permisos
        user.getRoles().stream()
                .flatMap(role -> role.getPermissionsList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(), // Aquí usa getIsEnabled
                user.isAccountNonExpired(), // Aquí usa getAccountNoExpired
                user.isAccountNonLocked(), // Aquí usa getAccountNoLocked
                user.isCredentialsNonExpired(), // Aquí usa getCredentialNoExpired
                authorityList);
    }
}
