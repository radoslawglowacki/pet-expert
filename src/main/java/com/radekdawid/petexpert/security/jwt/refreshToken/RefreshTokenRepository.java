package com.radekdawid.petexpert.security.jwt.refreshToken;

import com.radekdawid.petexpert.users.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findRefreshTokenByUser(User user);

    void deleteByUser(User user);
}
