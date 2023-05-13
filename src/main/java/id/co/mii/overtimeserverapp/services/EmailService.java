package id.co.mii.overtimeserverapp.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import id.co.mii.overtimeserverapp.models.Employee;
import id.co.mii.overtimeserverapp.models.Overtime;
import id.co.mii.overtimeserverapp.models.Reimburse;
import id.co.mii.overtimeserverapp.models.dto.requests.UserRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public String sendMailAccount(UserRequest userRequest) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom("maulanarafael@gmail.com");
            mailMessage.setTo(userRequest.getEmail());
            mailMessage
                    .setText("Hello " + userRequest.getName() + ",\n\nThis is your account on Overtime App\nUsername : "
                            + userRequest.getUsername() + "\nPassword : " + userRequest.getPassword()
                            + "\n\nPlease kindly use the account for login to this link (Overtime App) : http://localhost:8089\n\nPlease contact administrator if any problem!");
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

    public String sendMailOvertimePaid(Overtime overtime) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom("maulanarafael@gmail.com");
            mailMessage.setTo(overtime.getEmployeeProject().getEmployee().getEmail());
            mailMessage
                    .setText("Hello " + overtime.getEmployeeProject().getEmployee().getName()
                            + ",\n\nWe want to confirm that your overtime has been paid to your account bank.\nNominal : "
                            + overtime.getNominal() + "\nDescription : " + overtime.getDescription()
                            + "\nDate Time : " + overtime.getStart_time() + " - " + overtime.getEnd_time()
                            + "\n\nPlease contact administrator if any problem!");
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

    public String sendMailReimbursePaid(Reimburse reimburse) {

        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom("maulanarafael@gmail.com");
            mailMessage.setTo(reimburse.getEmployee().getEmail());
            mailMessage
                    .setText("Hello " + reimburse.getEmployee().getName()
                            + ",\n\nWe want to confirm that your reimburse has been paid to your account bank.\nNominal : "
                            + reimburse.getNominal() + "\nDescription : " + reimburse.getDescription()
                            + "\nDate Time : " + reimburse.getDate_time()
                            + "\n\nPlease contact administrator if any problem!");
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
