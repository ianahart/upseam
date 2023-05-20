package com.backend.fitters.config;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.backend.fitters.advice.NotFoundException;
import com.backend.fitters.advice.RefreshTokenException;
import com.backend.fitters.refreshtoken.RefreshToken;
import com.backend.fitters.refreshtoken.RefreshTokenRepository;
import com.backend.fitters.user.User;
import com.backend.fitters.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public RefreshTokenService(
            UserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken generateRefreshToken(Long userId) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(this.userRepository.findById(userId).get());
        // 12 days for refresh token
        refreshToken.setExpiryDate(Instant.now().plusMillis(86400000 * 12));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());

        this.refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public void revokeAllUserRefreshTokens(User user) {
        List<RefreshToken> refreshTokens = this.refreshTokenRepository.findAllUserRefreshTokens(user.getId());
        refreshTokens.forEach(rt -> {
            this.refreshTokenRepository.deleteById(rt.getId());
        });
    }

    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = this.refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> new NotFoundException("A refresh token is not present."));

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            this.refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException("Token has expired. Please sign in again.");
        }

        return refreshToken;
    }
}
