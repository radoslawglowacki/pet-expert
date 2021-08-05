package com.radekdawid.petexpert.authentication.token;


import com.radekdawid.petexpert.users.user.model.User;
import com.radekdawid.petexpert.users.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {


    private final  ConfirmationTokenRepository confirmationTokenRepository;
    private final UserService userService;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public String createToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), user);
        saveConfirmationToken(confirmationToken);

        return token;
    }

    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        setConfirmedAt(token);
        userService.enableAppUser(confirmationToken.getUser().getEmail());
    }
}
