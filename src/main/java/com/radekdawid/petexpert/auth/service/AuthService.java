package com.radekdawid.petexpert.auth.service;

import com.radekdawid.petexpert.auth.payload.request.LoginRequest;
import com.radekdawid.petexpert.exceptions.TokenRefreshException;
import com.radekdawid.petexpert.security.jwt.payload.request.TokenRefreshRequest;
import com.radekdawid.petexpert.security.jwt.payload.response.JwtResponse;
import com.radekdawid.petexpert.security.jwt.refreshToken.RefreshToken;
import com.radekdawid.petexpert.security.jwt.refreshToken.RefreshTokenService;
import com.radekdawid.petexpert.security.jwt.utils.JwtUtils;
import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public ResponseEntity<?> loginUser(LoginRequest loginRequest) {
        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword(), userDetails.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        if (refreshTokenService.findByUser(user).isPresent()) {
            refreshTokenService.deleteRefreshTokenByUserId(user);
        }

        String jwt = jwtUtils.generateJwtToken(user);

        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken()));
    }


    public ResponseEntity<?> refreshToken(TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateJwtToken(user);
                    return ResponseEntity.ok(new JwtResponse(token, refreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(refreshToken, "Refresh token is not in database!"));
    }
}
