package com.bcefit.infrastructure;

import com.bcefit.domaine.UserLoginData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserLoginDataRepository extends CrudRepository<UserLoginData, Integer> {

    UserLoginData findByEmail(String email);
}
