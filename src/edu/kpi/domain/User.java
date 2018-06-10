package edu.kpi.domain;

public class User {
    private String login;
    private String password;
    private String name;
    private String email;
    private boolean advertising;

    public User() {
    }

    public User(String login, String password, String name, String email, boolean advertising) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.advertising = advertising;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
