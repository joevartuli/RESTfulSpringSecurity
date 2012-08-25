package rest.demo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Joseph Vartuli
 * Date: 25/08/12
 *
 * Simple Authentication Provider ensuring that the username and password is jack and jill.
 * This is the place to put in a more comprehensive data layer and security model.
 */
public class RestAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RestToken restToken = (RestToken) authentication;

        String key = restToken.getKey();
        String credentials = restToken.getCredentials();

        if (!key.equals("jack") || !credentials.equals("jill")) {
            throw new BadCredentialsException("Enter a username and password");
        }

        return getAuthenticatedUser(key, credentials);
    }

    private Authentication getAuthenticatedUser(String key, String credentials) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new RestToken(key, credentials, authorities);
    }

    @Override
    /*
        Determines if this class can support the token provided by the filter.
     */
    public boolean supports(Class<?> authentication) {
        return RestToken.class.equals(authentication);
    }
}
