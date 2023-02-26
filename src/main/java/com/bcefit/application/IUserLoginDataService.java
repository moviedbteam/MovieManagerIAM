package com.bcefit.application;

import com.bcefit.domaine.UserLoginData;

public interface IUserLoginDataService {

    UserLoginData findByEmail(String email);
}
