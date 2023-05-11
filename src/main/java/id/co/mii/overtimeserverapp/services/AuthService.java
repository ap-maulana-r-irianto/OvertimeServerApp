package id.co.mii.overtimeserverapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.co.mii.overtimeserverapp.models.Employee;
import id.co.mii.overtimeserverapp.models.Role;
import id.co.mii.overtimeserverapp.models.User;
import id.co.mii.overtimeserverapp.models.dto.requests.LoginRequest;
import id.co.mii.overtimeserverapp.models.dto.requests.UserRequest;
import id.co.mii.overtimeserverapp.models.dto.responses.LoginResponse;
import id.co.mii.overtimeserverapp.repositories.EmailSender;
import id.co.mii.overtimeserverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

        private AuthenticationManager authenticationManager;
        private UserRepository userRepository;
        private AppUserDetailService appUserDetailService;
        private ModelMapper modelMapper;
        private PasswordEncoder passwordEncoder;
        private RoleService roleService;
        private EmailSender emailSender;

        public LoginResponse login(LoginRequest loginRequest) {
                // login request
                UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword());

                // set principle
                Authentication auth = authenticationManager.authenticate(authReq);
                SecurityContextHolder.getContext().setAuthentication(auth);

                User user = userRepository
                                .findByUsernameOrEmployee_Email(
                                                loginRequest.getUsername(),
                                                loginRequest.getUsername())
                                .get();

                UserDetails userDetails = appUserDetailService.loadUserByUsername(
                                loginRequest.getUsername());

                List<String> authorities = userDetails
                                .getAuthorities()
                                .stream()
                                .map(authority -> authority.getAuthority())
                                .collect(Collectors.toList());

                return new LoginResponse(
                                user.getUsername(),
                                user.getEmployee().getEmail(),
                                authorities);
        }

        public User register(UserRequest userRequest) {
                Employee employee = modelMapper.map(userRequest, Employee.class);
                User user = modelMapper.map(userRequest, User.class);

                // set password
                user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

                // set Role
                List<Role> roles = new ArrayList<>();
                roles.add(roleService.getById(1));
                user.setRole(roles);

                employee.setUser(user);
                user.setEmployee(employee);
                return userRepository.save(user);
        }

        public User create(UserRequest userRequest) {
                Employee employee = modelMapper.map(userRequest, Employee.class);
                User user = modelMapper.map(userRequest, User.class);

                // set password
                user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

                // set Role
                // List<Role> roles = new ArrayList<>();
                // roles.add(roleService.getById(1));
                // user.setRoles(roles);

                employee.setUser(user);
                user.setEmployee(employee);

                // String link = "http://localhost:8089/login";
                // emailSender.send(userRequest.getEmail(), buildEmail(userRequest.getName(), userRequest.getUsername(),
                //                 userRequest.getPassword(), link));
                return userRepository.save(user);
        }

        private String buildEmail(String name, String username, String password, String link) {
                return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n"
                                +
                                "\n" +
                                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                                "\n" +
                                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n"
                                +
                                "    <tbody><tr>\n" +
                                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                                "        \n" +
                                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n"
                                +
                                "          <tbody><tr>\n" +
                                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                                +
                                "                  <tbody><tr>\n" +
                                "                    <td style=\"padding-left:10px\">\n" +
                                "                  \n" +
                                "                    </td>\n" +
                                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n"
                                +
                                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n"
                                +
                                "                    </td>\n" +
                                "                  </tr>\n" +
                                "                </tbody></table>\n" +
                                "              </a>\n" +
                                "            </td>\n" +
                                "          </tr>\n" +
                                "        </tbody></table>\n" +
                                "        \n" +
                                "      </td>\n" +
                                "    </tr>\n" +
                                "  </tbody></table>\n" +
                                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                                +
                                "    <tbody><tr>\n" +
                                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                                "      <td>\n" +
                                "        \n" +
                                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n"
                                +
                                "                  <tbody><tr>\n" +
                                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                                "                  </tr>\n" +
                                "                </tbody></table>\n" +
                                "        \n" +
                                "      </td>\n" +
                                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                                "    </tr>\n" +
                                "  </tbody></table>\n" +
                                "\n" +
                                "\n" +
                                "\n" +
                                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n"
                                +
                                "    <tbody><tr>\n" +
                                "      <td height=\"30\"><br></td>\n" +
                                "    </tr>\n" +
                                "    <tr>\n" +
                                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n"
                                +
                                "        \n" +
                                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi "
                                + name
                                + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">This is your account. Username : "
                                + username
                                + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">This is your account. Password : "
                                + password
                                + "</p><br><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Please click on the below link to login: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">"
                                + link
                                + "<p>See you soon</p>"
                                +
                                "        \n" +
                                "      </td>\n" +
                                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                                "    </tr>\n" +
                                "    <tr>\n" +
                                "      <td height=\"30\"><br></td>\n" +
                                "    </tr>\n" +
                                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                                "\n" +
                                "</div></div>";
        }
}