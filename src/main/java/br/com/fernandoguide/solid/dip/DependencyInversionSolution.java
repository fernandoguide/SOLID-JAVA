package br.com.fernandoguide.solid.dip;

import lombok.extern.slf4j.Slf4j;

import static br.com.fernandoguide.solid.dip.Constants.LOGIN;
import static br.com.fernandoguide.solid.dip.Constants.USUARIO;

@Slf4j
public class DependencyInversionSolution {
    static Console console = (s1,s2) -> (LOGIN + s1 + USUARIO + s2);

    public static void main(String[] args) {
        User user = new User("email@mail.com", "123");
        var authenticateLoginFacebook = new AuthenticateLogin(new AuthenticationFacebook());
        var authenticateLoginLinkedin = new AuthenticateLogin(new AuthenticationLinkedin());
        log.info(authenticateLoginLinkedin.login(user));
        log.info(authenticateLoginFacebook.login(user));
    }
    public static class AuthenticateLogin {
        private final Authentication authentication;
        private AuthenticateLogin(Authentication authentication) {
            this.authentication = authentication;
        }
        public String login(User user){
            return authentication.login(user);
        }
    }
    public interface Authentication {
        String login(User user);
    }
    public static class AuthenticationLinkedin implements Authentication {
        @Override
        public String login(User user) {
            return console.show(AuthenticationLinkedin.class.getSimpleName(), user.getUsername());
        }
    }
    public static class AuthenticationFacebook implements Authentication {
        @Override
        public String login(User user) {
            return console.show(AuthenticationFacebook.class.getSimpleName(), user.getUsername());
        }
    }
}
