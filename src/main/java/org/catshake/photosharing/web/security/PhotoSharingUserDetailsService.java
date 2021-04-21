package org.catshake.photosharing.web.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PhotoSharingUserDetailsService implements UserDetailsService {
    // private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/*
        return userRepository.findByUsername(username)
                .map(PhotoSharingUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
*/
        throw new UnsupportedOperationException("loadUserByUsername");
    }
}
