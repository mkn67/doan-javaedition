package com.app.javafx.core.security;

public class SessionManager {

    private static SessionManager instance;

    private String token;
    private String username;
    private String maNhom;
    private String loaiTk;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // ===== TOKEN =====
    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    // ===== USER INFO =====
    public void setUserInfo(String username, String maNhom, String loaiTk) {
        this.username = username;
        this.maNhom = maNhom;
        this.loaiTk = loaiTk;
    }

    public String getUsername() {
        return username;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public String getLoaiTk() {
        return loaiTk;
    }

    public boolean isLoggedIn() {
        return token != null && !token.isEmpty();
    }

    public void clearSession() {
        token = null;
        username = null;
        maNhom = null;
        loaiTk = null;
    }
}