package com.frankmoley.security.app.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LandonUserDetailService implements UserDetailsService {

    private final UserRepository<User> repository;

    public LandonUserDetailService(UserRepository<User> repository) {
        super();
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(s);
        return new LandonUserPrincipal(user.orElseThrow(
                () -> new UsernameNotFoundException("cannot find username:  " + s))
        );
    }
}
