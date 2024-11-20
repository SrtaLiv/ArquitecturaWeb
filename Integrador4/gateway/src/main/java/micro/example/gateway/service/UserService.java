package micro.example.gateway.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import micro.example.gateway.entity.Authority;
import micro.example.gateway.entity.User;
import micro.example.gateway.repository.AuthorityRepository;
import micro.example.gateway.repository.UserRepository;
import micro.example.gateway.service.dto.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorityRepository authorityRepository;

    @Transactional
    public Long saveUser(UserDTO userDTO) {
        // Verificar y crear autoridades si no existen
        Set<Authority> authorities = new HashSet<>();
        for (String authorityName : userDTO.getAuthorities()) {
            Authority authority = authorityRepository.findById(authorityName)
                    .orElseGet(() -> authorityRepository.save(new Authority(authorityName)));
            authorities.add(authority);
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        //user.setPassword(userDTO.getPassword());
        user.setPassword( passwordEncoder.encode(userDTO.getPassword() ) );
        user.setAuthorities(authorities);
        User savedUser = userRepository.save(user);

        return savedUser.getId();
    }

}
