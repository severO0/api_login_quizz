package testedeapi.com.service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import testedeapi.com.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;
  
    
    @Override
    public UserDetails loadUserByUsername(String registroAcademico) throws UsernameNotFoundException {
        return userRepository.findByRegistroAcademico(registroAcademico);
    }
    
}
