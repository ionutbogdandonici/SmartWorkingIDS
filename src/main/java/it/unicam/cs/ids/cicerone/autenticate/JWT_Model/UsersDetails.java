package it.unicam.cs.ids.cicerone.autenticate.JWT_Model;

import it.unicam.cs.ids.cicerone.model.users.Associazione;
import it.unicam.cs.ids.cicerone.model.users.Cicerino;
import it.unicam.cs.ids.cicerone.model.users.Turista;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsersDetails implements UserDetails {
    private Turista turista;
    private Cicerino cicerino;
    private Associazione associazione;

    public UsersDetails(Turista turista){
        this.turista = turista;
    }

    public UsersDetails(Cicerino cicerino){this.cicerino = cicerino;}

    public UsersDetails(Associazione associazione) {this.associazione = associazione;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("user");
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
       if(this.turista !=null){
            return turista.getPassword();
        }else if(this.cicerino != null){
            return cicerino.getPassword();
        }else if(this.associazione != null){
            return associazione.getPassword();
        }
        return null;
    }

    @Override
    public String getUsername() {
        if(this.turista !=null){
            return turista.getUsername();
        }else if(this.cicerino != null){
            return cicerino.getUsername();
        }else if(this.associazione != null){
            return associazione.getUsername();
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
