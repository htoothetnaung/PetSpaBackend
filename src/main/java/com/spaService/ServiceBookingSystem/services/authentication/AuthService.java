package com.spaService.ServiceBookingSystem.services.authentication;

import com.spaService.ServiceBookingSystem.dto.SignupRequestDTO;
import com.spaService.ServiceBookingSystem.dto.UserDto;

public interface AuthService {

    UserDto signupClient(SignupRequestDTO signupRequestDTO);

    Boolean presentByEmail(String email);

    UserDto signupCompany(SignupRequestDTO signupRequestDTO);

}
