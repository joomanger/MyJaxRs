package jaas;

import beans.sys.UserEJB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author savin
 */
public class MyLoginModule implements LoginModule {

    @Inject
    private UserEJB ejb;

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    private List<String> userGroups;

    @Override
    public void initialize(Subject subject,
            CallbackHandler callbackHandler,
            Map<String, ?> sharedState,
            Map<String, ?> options) {

        handler = callbackHandler;
        this.subject = subject;
    }

    @Override
    public boolean login() throws LoginException {

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);

        try {
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1])
                    .getPassword());

            // Here we validate the credentials against some
            // authentication/authorization provider.
            // It can be a Database, an external LDAP, 
            // a Web Service, etc.
            // For this tutorial we are just checking if 
            // user is "user123" and password is "pass123"
            if (name != null
                    && name.equals("admin")
                    && password != null
                    && password.equals("root")) {

                // We store the username and roles
                // fetched from the credentials provider
                // to be used later in commit() method.
                // For this tutorial we hard coded the
                // "admin" role
                try {
                    if (ejb == null) {
                        System.out.println("EJB IS NULL");

                    } else {
                        System.out.println(ejb);
                        //SysUser user = ejb.findByUserName(name);
                    }
                    
                    
                    Properties p = new Properties();
                    p.put("java.naming.factory.initial", "org.apache.openejb.client.LocalInitialContextFactory");
                    InitialContext ctx = new InitialContext(p);
                    UserEJB ejb2 = (UserEJB) ctx.lookup("UserEJB");
                    if (ejb2 == null) {
                        System.out.println("EJB2 IS NULL");

                    }

                    //System.out.println(user.getPassword());
                    System.out.println("after getPass");
                    login = name;
                    userGroups = new ArrayList<String>();
                    userGroups.add("user");
                } catch (Exception ex) {
                    System.out.println(ex);
                    throw new LoginException("Authentication failed");
                }
                return true;
            }

            // If credentials are NOT OK we throw a LoginException
            throw new LoginException("Authentication failed");

        } catch (IOException e) {
            throw new LoginException(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        }

    }

    @Override
    public boolean commit() throws LoginException {
        userPrincipal = new UserPrincipal(login);
        subject.getPrincipals().add(userPrincipal);
        if (userGroups != null && userGroups.size() > 0) {
            for (String groupName : userGroups) {
                rolePrincipal = new RolePrincipal(groupName);
                subject.getPrincipals().add(rolePrincipal);
            }
        }
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }

}
