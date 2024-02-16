package com.example.e_ticketscanner;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "LoginModel", strict = false)
public class LoginModel {
    @Element(name = "AppVersion")
    private String appVersion;

    @Element(name = "MONUMENTNAME_ID")
    private int monumentNameId;

    @Element(name = "PASSWORD")
    private String password;

    @Element(name = "Rememberme")
    private boolean rememberMe;

    @Element(name = "USERNAME")
    private String username;

    @Element(name = "id")
    private int id;

    @Element(name = "loginStatus")
    private boolean loginStatus;

    // Getters and setters

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getMonumentNameId() {
        return monumentNameId;
    }

    public void setMonumentNameId(int monumentNameId) {
        this.monumentNameId = monumentNameId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
}

