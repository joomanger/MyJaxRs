package sys.beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.SessionActions;
import sys.entities.SysUser;

@Named
@RequestScoped
public class UserCBean {

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
        for (SysUser u : fuv.getSelectedUsers()) {
            if (!u.getUsername().equals("admin")) {
                if ((!u.getUsername().equals(username))) {
                    String status = ejb.remove(u);
                    ejb.sendMessage(status, "Пользователь " + u.getUsername() + " удален успешно");
                }
            }
        }
    }

    public void addRoleOUV() {
        SysUser user = ouv.getUser();
        user.getRoles().add(ouv.getNewRole());
        String status = ejb.edit(user);
        ejb.sendMessage(status, "Роль добавлена успешно");
    }

    public void addRoleNUV() {
        try {
            SysUser user = nuv.getUser();
            user.getRoles().add(nuv.getNewRole());
            String status = ejb.edit(user);
            ejb.sendMessage(status, "Роль добавлена успешно");
        } catch (NullPointerException ex) {
            System.out.println("sys.beans.UserCBean.addRoleNUV(): " + ex.getMessage());
        }
    }

    public void deleteRoles() {
        SysUser user = nuv.getUser();
        user.getRoles().removeAll(nuv.getSelectedRoles());
        String status = ejb.edit(user);
        ejb.sendMessage(status, "Роли удалены успешно");
    }

    public void deleteRolesNUV() {
        SysUser user = nuv.getUser();
        user.getRoles().removeAll(ouv.getSelectedRoles());
        String status = ejb.edit(user);
        ejb.sendMessage(status, "Роли удалены успешно");
    }

    public String saveUser(SysUser user) {
        String result = ejb.edit(user);
        ejb.sendMessage(result, "Пользователь " + user.getUsername() + " сохранен");
        return "users";
    }

    public void changePassword() {
        if (ouv.isPasswordsEqual()) {
            try {
                MessageDigest messageDigest = java.security.MessageDigest.getInstance("SHA-256");
                byte bin[] = messageDigest.digest((ouv.getNewPassword()).getBytes());
                SysUser user = ouv.getUser();
                user.setPassword(Base64.getEncoder().encodeToString(bin));
                String status = ejb.edit(user);
                ejb.sendMessage(status, "Пароль изменен успешно");
                ouv.setNewPassword("");
                ouv.setReNewPassword("");
            } catch (NoSuchAlgorithmException se) {
                System.out.println("changePassword: " + se.getMessage());
            }
        }
    }

}
