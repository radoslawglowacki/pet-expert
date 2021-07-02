package com.radekdawid.petexpert.email;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;


    @Override
    @Async
    public void send(String to, String email, String subject) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
//            helper.setFrom("pet@expert.com");
            mailSender.send(mimeMessage);
        }catch(MessagingException e){
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }

    @SneakyThrows
    @Contract(pure = true)
    public void buildRegistrationEmail(String firstName, String email, String token) {
        ClassPathResource resource = new ClassPathResource("templates/registrationEmail.txt");
        InputStream inputStream = resource.getInputStream();
        String template = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining());


//        TODO: Change link to proper one in future
        String confirmationLink = "http://localhost:8081/api/v1/registration/confirm?token=" + token;
        String subject = "Pet Expert - confirm your email";
        String message = String.format(template, firstName, confirmationLink);
        this.send(email, message, subject);
    }
}
