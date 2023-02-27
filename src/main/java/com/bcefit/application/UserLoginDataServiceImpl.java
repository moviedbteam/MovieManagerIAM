package com.bcefit.application;

import com.bcefit.domaine.UserLoginData;
import com.bcefit.dto.UserLoginDataDto;
import com.bcefit.infrastructure.IUserLoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLoginDataServiceImpl implements IUserLoginDataService {

    @Autowired
    private IUserLoginDataRepository userLoginDataRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserLoginData findByEmail(String email) {
        return userLoginDataRepository.findByEmail(email);
    }

    @Override
    public void save(UserLoginDataDto userLoginDataDto) {

        // Hash du password avec BCrypt
        passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode(userLoginDataDto.getPassword());
        userLoginDataDto.setPassword(passwordHash);

        // Mappe le DTO vers ENTITE
        UserLoginData userLoginData = MapperUserLoginData.mapDtoToEntity(userLoginDataDto);

        // Appel au DAO
        userLoginDataRepository.save(userLoginData);
    }
}
