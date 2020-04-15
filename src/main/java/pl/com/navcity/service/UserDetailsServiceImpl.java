package pl.com.navcity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.com.navcity.repository.UserRepositoryImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepositoryImpl userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }
}
