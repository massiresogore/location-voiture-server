package com.msr.agenceloc.security;

import com.msr.agenceloc.client.ClientUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;

public class MyUserPrincipal implements UserDetails {

    private final ClientUser clientUser;

    public MyUserPrincipal(ClientUser clientUser) {
        this.clientUser = clientUser;
    }

    /**
     * on crée une chaine de caractère pour
     * nous retourner "Role_admin" ou "Role_user", pour
     * un utilisateur
     *
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return Arrays.stream(StringUtils.tokenizeToStringArray(this.clientUser.getRoles(), " "))
                .map(role-> new SimpleGrantedAuthority("ROLE_"+role)).toList();
    }

    @Override
    public String getPassword() {
        return this.clientUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.clientUser.getNom();
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
        return this.clientUser.isEnable();
    }

    public ClientUser getClientUser() {
        return clientUser;
    }
}
