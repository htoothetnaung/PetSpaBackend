package com.spaService.ServiceBookingSystem.services.authentication;


import com.spaService.ServiceBookingSystem.dto.SignupRequestDTO;
import com.spaService.ServiceBookingSystem.dto.UserDto;
import com.spaService.ServiceBookingSystem.entity.User;
import com.spaService.ServiceBookingSystem.enums.userRole;
import com.spaService.ServiceBookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient(SignupRequestDTO signupRequestDTO){

        User user = new User();

        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));

        user.setRole(userRole.CLIENT);

        return userRepository.save(user).getDto();
    }

    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }


    public UserDto signupCompany(SignupRequestDTO signupRequestDTO){

        User user = new User();

        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));

        user.setRole(userRole.COMPANY);

        return userRepository.save(user).getDto();
    }


}
