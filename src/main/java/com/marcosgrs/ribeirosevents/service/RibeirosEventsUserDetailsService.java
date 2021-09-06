package com.marcosgrs.ribeirosevents.service;

import com.marcosgrs.ribeirosevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RibeirosEventsUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public RibeirosEventsUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User '%s' not found.", email)));
    }
}
