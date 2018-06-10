package edu.kpi.servlets.beans;

public class RegistrationBean {
    private String login;
    private String password1;
    private String password2;
    private String name;
    private String email;
    private boolean advertising;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdvertising() {
        return advertising;
    }

    public void setAdvertising(boolean advertising) {
        this.advertising = advertising;
    }
}
