package com.marcosgrs.ribeirosevents.service;

import com.marcosgrs.ribeirosevents.controllers.dto.SignUpDto;
import com.marcosgrs.ribeirosevents.domain.entity.Profile;
import com.marcosgrs.ribeirosevents.domain.entity.User;
import com.marcosgrs.ribeirosevents.domain.model.Role;
import com.marcosgrs.ribeirosevents.domain.repository.RoleRepository;
import com.marcosgrs.ribeirosevents.exceptions.InvalidSignUpDataException;
import com.marcosgrs.ribeirosevents.exceptions.NotFoundException;
import com.marcosgrs.ribeirosevents.exceptions.UserAlreadyExistsException;
import com.marcosgrs.ribeirosevents.mapper.SignUpMapper;
import com.marcosgrs.ribeirosevents.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class RibeirosEventsUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RibeirosEventsUserDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User '%s' not found.", email)));
    }

    public String signup(SignUpDto signUpDto) {
        validateForm(signUpDto);
        User userToSave = SignUpMapper.toEntity(signUpDto);
        userToSave.setAuthorities(Collections.singletonList(setUserRole()));
        var savedUser = userRepository.save(userToSave);
        return savedUser.getId();
    }

    private boolean userAlreadyExists(SignUpDto signUpDto) {
        Optional<User> existingUser = userRepository.findByEmail(signUpDto.getEmail());
        return existingUser.isPresent();
    }

    private boolean passwordAndItConfirmationMatches(SignUpDto signUpDto) {
        return signUpDto.getPassword().equals(signUpDto.getPasswordConfirmation());
    }

    private void validateForm(SignUpDto signUpDto) {
        if(userAlreadyExists(signUpDto)) {
            throw new UserAlreadyExistsException(String.format("User %s already exists", signUpDto.getEmail()));
        }

        if(!passwordAndItConfirmationMatches(signUpDto)) {
            throw new InvalidSignUpDataException("Password and its confirmation must match.");
        }
    }

    private Profile setUserRole() {
        return roleRepository.findByAuthority(Role.ROLE_USER.name())
                .orElseThrow(() -> new
                        InternalAuthenticationServiceException("ROLE_USER doesn't exists in database."));
    }

    public User findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String
                        .format("User %s not found", userId)));
    }
}
