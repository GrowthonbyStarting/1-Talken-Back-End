package com.example.talken.common.security;

import com.example.talken.user.entity.User;
import com.example.talken.user.exception.UserError;
import com.example.talken.user.exception.UserException;
import com.example.talken.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new UserException(UserError.USER_NOT_FOUND)
        );
        return new UserDetailsImpl(user, user.getUsername());
    }
}
