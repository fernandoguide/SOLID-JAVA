package br.com.fernandoguide.solid.dip;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static br.com.fernandoguide.solid.dip.Constants.LOGIN;
import static br.com.fernandoguide.solid.dip.Constants.USUARIO;


@Slf4j
public class DependencyInversionProblem {
    static Console console = (s1, s2) -> (LOGIN + s1 + USUARIO+ s2);

    public static void main(String[] args) {
        User user = new User("email@mail.com", "123");
        var authenticateLogin = new AuthenticateLogin();
        log.info(authenticateLogin.loginLinkedin(user));
        log.info(authenticateLogin.loginFacebook(user));
    }

    private static class AuthenticateLogin {
        public String loginLinkedin(User user){
            AuthenticationLinkedin authenticationLinkedin = new AuthenticationLinkedin();
            return authenticationLinkedin.login(user);
        }
        public String loginFacebook(User user){
            AuthenticationFacebook authenticationFacebook = new AuthenticationFacebook();
            return authenticationFacebook.login(user);
        }
    }
    private static class AuthenticationLinkedin  {
        public String login(User user) {
            return console.show(AuthenticationLinkedin.class.getSimpleName() , user.getUsername());
        }
    }
    private static class AuthenticationFacebook  {
        public String login(User user) {
            return console.show(AuthenticationFacebook.class.getSimpleName() , user.getUsername());
        }
    }
}
