package AirlineApp.security.model;

import AirlineApp.data.models.Passenger;
import AirlineApp.data.models.Role;
import AirlineApp.data.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class SecureUser implements UserDetails {

    private final User user;


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = user.getRole();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority userAuthorities = new SimpleGrantedAuthority(role.name());
        authorities.add(userAuthorities);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {return user.isActive();}
}
