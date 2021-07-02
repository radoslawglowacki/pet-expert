package com.radekdawid.petexpert.users.user_details;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsService {
    private final UserDetailsRepository userDetailsRepository;
}
