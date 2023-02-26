package com.bcefit.domaine;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserLoginData {

    @Id
    private int idUser;

    private String loginName;
    private String passwordHash;
    private String passwordSalt;
    private int hashAlgoId;
    private String email;

    public UserLoginData() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public int getHashAlgoId() {
        return hashAlgoId;
    }

    public void setHashAlgoId(int hashAlgoId) {
        this.hashAlgoId = hashAlgoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
