package beans.sys;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.AbstractClientBean;
import service.AbstractEJB;
import service.AbstractView;
import service.SessionActions;
import entities.sys.SysUser;

@Named
@RequestScoped
public class UserCBean extends AbstractClientBean<SysUser>{

    @Inject
    private UserEJB ejb;
    @Inject
    private OpenUserView ouv;
    @Inject
    private NewUserView nuv;
    @Inject
    private FindUserView fuv;
    @Inject
    private SessionActions sc;

    @Override
    protected AbstractEJB<SysUser> getEJB() {
        return ejb;
    }

    @Override
    protected AbstractView<SysUser> getOpenView() {
        return ouv;
    }

    @Override
    protected AbstractView<SysUser> getFindView() {
        return fuv;
    }

    @Override
    protected AbstractView<SysUser> getNewView() {
        return nuv;
    }
    
    public SysUser findUserById(Long p_id) {
        return ejb.find(p_id);
    }

    public SysUser findUserByUserName(String p_username) {
        return ejb.findByUserName(p_username);
    }

    public List<SysUser> findAll() {
        return ejb.findAll();
    }

    public void deleteUsers() {
        String username = sc.getCurrentUser().getUsername();
        for (SysUser u : fuv.getSelectedEntities()) {
            if (!u.getUsername().equals("admin")&&!u.getUsername().equals(username)) {
                //if (!u.getUsername().equals(username)) {
                    String status = ejb.remove(u);
                    ejb.sendMessage(status, "Пользователь " + u.getUsername() + " удален успешно");
               // }
            }
        }
    }

    public void addRoleOUV() {
        SysUser user = ouv.getEntity();
        user.getRoles().add(ouv.getNewRole());
        String status = ejb.edit(user);
        ejb.sendMessage(status, "Роль добавлена успешно");
    }

    public void addRoleNUV() {
        try {
            SysUser user = nuv.getEntity();
            user.getRoles().add(nuv.getNewRole());
//            String status = ejb.edit(user);
//            ejb.sendMessage(status, "Роль добавлена успешно");
        } catch (NullPointerException ex) {
            System.out.println("sys.beans.UserCBean.addRoleNUV(): " + ex.getMessage());
        }
    }

    public void deleteRolesOUV() {
        SysUser user = ouv.getEntity();
        user.getRoles().removeAll(ouv.getSelectedEntityLines());
        String status = ejb.edit(user);
        ejb.sendMessage(status, "Роли удалены успешно");
    }

    public void deleteRolesNUV() {
        SysUser user = nuv.getEntity();
        user.getRoles().removeAll(nuv.getSelectedEntityLines());
//        String status = ejb.edit(user);
//        ejb.sendMessage(status, "Роли удалены успешно");
    }

    public String createNewUser() {
        SysUser user=nuv.getEntity();
        user.setPassword(digestPassword(nuv.getNewPassword()));
        SysUser u = ejb.registerNewUser(user);
        String result = ejb.create(user);
        if (u != null) {
            nuv.setFieldDisabled(true);
            ejb.sendMessage(result, "Пользователь " + user.getUsername() + " создан");
        } else {
            ejb.sendMessage(result, "Пользователь " + user.getUsername() + " не зарегистрирован в группе USERS. Обратитесь к сисадмину");
        }

        return "users";
    }

    private String digestPassword(String p_password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte bin[] = messageDigest.digest(p_password.getBytes());
            return Base64.getEncoder().encodeToString(bin);
        } catch (NoSuchAlgorithmException se) {
            System.out.println("exception sys.beans.UserCBean.digestPassword() " + se.getMessage());
            return null;
        }
    }

    public void changePassword() {
        if (ouv.isPasswordsEqual()) {
            SysUser user = ouv.getEntity();
            user.setPassword(digestPassword(ouv.getNewPassword()));
            String status = ejb.edit(user);
            ejb.sendMessage(status, "Пароль изменен успешно");
            ouv.setNewPassword("");
            ouv.setReNewPassword("");
        }
    }

}
