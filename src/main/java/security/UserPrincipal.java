/*

 */
package security;

import java.security.Principal;

/**
 *
 * @author savin
 */
public class UserPrincipal implements Principal {

    private String name ;

    public UserPrincipal(String name) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
