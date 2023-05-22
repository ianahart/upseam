package com.backend.fitters.auth.request;

import com.backend.fitters.user.Role;

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String confirmPassword;

    public RegisterRequest(String role, String firstName, String lastName, String email, String password,
            String confirmPassword) {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
