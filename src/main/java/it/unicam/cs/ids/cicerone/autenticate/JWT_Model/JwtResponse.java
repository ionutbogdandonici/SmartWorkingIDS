package it.unicam.cs.ids.cicerone.autenticate.JWT_Model;

public class JwtResponse {
    String token;
    String username;
    public JwtResponse(){}

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
