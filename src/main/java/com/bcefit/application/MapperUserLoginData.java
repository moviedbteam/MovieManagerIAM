package com.bcefit.application;

import com.bcefit.domaine.UserLoginData;
import com.bcefit.dto.UserLoginDataDto;

public class MapperUserLoginData {


    public static UserLoginData mapDtoToEntity(UserLoginDataDto dto) {

        UserLoginData userLoginData = new UserLoginData();

        String passwordHash = dto.getPassword();

        userLoginData.setIdUser(dto.getIdUser());
        userLoginData.setLoginName(dto.getLoginName());
        userLoginData.setPasswordHash(passwordHash);
        userLoginData.setHashAlgoId(1);

        userLoginData.setEmail(dto.getEmail());

        return userLoginData;
    }
}
