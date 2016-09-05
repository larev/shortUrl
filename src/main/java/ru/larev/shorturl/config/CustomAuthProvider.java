package ru.larev.shorturl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.larev.shorturl.entity.Account;
import ru.larev.shorturl.repository.AccountRepository;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
@Component
public class CustomAuthProvider implements AuthenticationProvider {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String accountId = authentication.getName();
        String password = authentication.getCredentials().toString();
        Account one = accountRepository.findOne(accountId);
        if (one != null) {
            if (passwordEncoder.matches(password, one.getPassword())) {
                return new UsernamePasswordAuthenticationToken(one, password, AuthorityUtils.NO_AUTHORITIES);
            }
            throw new BadCredentialsException("Wrong password");
        }
        throw new BadCredentialsException("The account not found");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
