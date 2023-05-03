package id.co.mii.overtimeserverapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import id.co.mii.overtimeserverapp.models.AppUserDetail;
import id.co.mii.overtimeserverapp.models.User;
import id.co.mii.overtimeserverapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService {

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException {
    User user = userRepository
        .findByUsernameOrEmployee_Email(username, username)
        .orElseThrow(() -> new UsernameNotFoundException("Username or Email incorrect!!!"));
    return new AppUserDetail(user);
  }
}