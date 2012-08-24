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
 * @since:
 */
public class RestAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RestToken restToken = (RestToken) authentication;

        String key = restToken.getKey();
        String credentials = restToken.getCredentials();

        if (key.equals("Basic Og==")) {
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
    public boolean supports(Class<?> authentication) {
        return RestToken.class.equals(authentication);
    }
}
