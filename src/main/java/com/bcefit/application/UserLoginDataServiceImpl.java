package com.bcefit.application;

import com.bcefit.domaine.UserLoginData;
import com.bcefit.infrastructure.IUserLoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginDataServiceImpl implements IUserLoginDataService {

    @Autowired
    private IUserLoginDataRepository userLoginDataRepository;

    @Override
    public UserLoginData findByEmail(String email) {
        return userLoginDataRepository.findByEmail(email);
    }
}
