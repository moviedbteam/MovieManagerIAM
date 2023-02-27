package com.bcefit.application;

import com.bcefit.domaine.UserLoginData;
import com.bcefit.dto.UserLoginDataDto;

public interface IUserLoginDataService {

    UserLoginData findByEmail(String email);

    void save(UserLoginDataDto userLoginDataDto);
}
