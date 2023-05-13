package id.co.mii.overtimeserverapp.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import id.co.mii.overtimeserverapp.models.dto.requests.UserRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public String sendSimpleMail(UserRequest userRequest) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom("maulanarafael@gmail.com");
            mailMessage.setTo(userRequest.getEmail());
            mailMessage
                    .setText("Hello " + userRequest.getName() + ",\n\nThis is your account on Overtime App\nUsername : "
                            + userRequest.getUsername() + "\nPassword : " + userRequest.getPassword() + "\n\nLink App for login : http://localhost:8089/login\n\nPlease contact administrator if any problem!");
            mailMessage.setSubject("Account for Overtime App");

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
