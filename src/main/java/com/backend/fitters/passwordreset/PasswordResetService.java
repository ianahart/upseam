package com.backend.fitters.passwordreset;

import com.backend.fitters.advice.ForbiddenException;
import com.backend.fitters.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {
    @Autowired
    private final PasswordResetRepository passwordResetRepository;

    public PasswordResetService(PasswordResetRepository passwordResetRepository) {
        this.passwordResetRepository = passwordResetRepository;
    }

    public void deleteUserPasswordResetsById(Long id) {
        this.passwordResetRepository.deleteUserPasswordResetsById(id);
    }

    public void isResetTokenValid(String token) {
        PasswordReset passwordReset = this.passwordResetRepository.findByToken(token)
                .orElseThrow(() -> new ForbiddenException("Reset token is invalid."));
    }

    public void savePasswordReset(User user, String token) {
        if (token != null && user != null) {
            PasswordReset passwordReset = new PasswordReset(token, user);
            this.passwordResetRepository.save(passwordReset);

        }
    }
}
