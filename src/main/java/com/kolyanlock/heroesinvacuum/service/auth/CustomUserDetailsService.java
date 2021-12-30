package com.kolyanlock.heroesinvacuum.service.auth;

import com.kolyanlock.heroesinvacuum.dao.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.kolyanlock.heroesinvacuum.entity.auth.User user = userRepository.findByName(username);

        if (user == null || !user.isEnabled())
            throw new UsernameNotFoundException ("Unknown user: " + username);

        return User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .build();
    }
}
