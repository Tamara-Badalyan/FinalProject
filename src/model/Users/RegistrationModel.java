package model.Users;

import exceptions.InputException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public  class RegistrationModel {
    private String fullName;
    private String userName;
    private String email;
    private String password;

    public RegistrationModel(String fullName, String userName, String email, String password) {
        this.fullName = getFullName();
        this.userName = getUserName();
        this.email = getEmail();
        this.password = getPassword();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (isFullName(fullName))
            this.fullName = fullName;
        else
            throw new InputException();
    }

    private boolean isFullName(String name) {
        String regex = "(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)";
        return name.matches(regex);
    }

    private boolean isEmail(String email) {
        String regex = "^(.+)@(\\S+)$";
        return email.matches(regex);
    }

    private boolean uniqueCharachters(String str) {
        for (int i = 0; i < str.length(); i++)
            for (int j = i + 1; j < str.length(); j++)
                if (str.charAt(i) == str.charAt(j))
                    return false;
        return true;

    }
    private boolean isCorrectPassword(String password){
        String regex="^(?=.{8,}$)(?=(?:.*?[A-Z]){2})(?=(?:.*?[0-9]){3}).*$";
        return password.matches(regex);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName.length()>10 && uniqueCharachters(userName))
            this.userName = userName;
        throw new InputException();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isEmail(email))
            this.email = email;
        else
            throw new InputException();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(isCorrectPassword(password))
            this.password = md5Custom(password);
        else
            throw new InputException();
    }

    public  static  String md5Custom(String message) {
        StringBuffer sb = new StringBuffer();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(message.getBytes());
            byte[] digest = md.digest();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationModel that = (RegistrationModel) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.length());
    }
}

