package AirlineApp.security.model;

import AirlineApp.data.models.Passenger;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class SecureUser implements UserDetails {

    private final Passenger passenger;


    @Override
    public String getPassword() {
        return passenger.getUser().getPassword();
    }

    @Override
    public String getUsername() {
        return passenger.getUser().getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
