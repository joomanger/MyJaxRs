package sys.beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sys.entities.SysRole;

/**
 *
 * @author savin
 */
@Named
@RequestScoped
public class RoleCBean {

    @Inject
    private RoleEJB ejb;

    @Inject
    private FindRoleView frv;

    @Inject
    private FindRoleSession frs;

    @Inject
    private OpenRoleView orv;

    @Inject
    private NewRoleView nrv;

    public List<SysRole> findAll() {
        return ejb.findAll();
    }

    public SysRole findRole() {
        return ejb.find(frs.getRole_id());
    }

    public void deleteRoles() {
        for (SysRole role : frv.getSelectedRoles()) {
            String status = ejb.remove(role);
            ejb.sendMessage(status, "Роль " + role.getRoleName() + " удалена успешно");
        }
    }

    public void deleteMenusORV() {
        SysRole role = orv.getRole();
        role.getMenus().removeAll(orv.getSelectedMenus());
        String status = ejb.edit(role);
        ejb.sendMessage(status, "Меню удалены успешно");
    }

    public void deleteMenusNRV() {
        SysRole role = nrv.getRole();
        role.getMenus().removeAll(nrv.getSelectedMenus());
    }

    public void addMenuORV() {
        SysRole role = orv.getRole();
        orv.getRole().getMenus().add(orv.getNewMenu());
        String status = ejb.edit(role);
        ejb.sendMessage(status, "Меню добавлено успешно");
    }

    public void addMenuNRV() {
        nrv.getRole().getMenus().add(nrv.getNewMenu());
    }

    public String saveRoleORV() {
        SysRole role = orv.getRole();
        ejb.edit(role);
        return "roles";
    }

    public String createRole() {
        SysRole role = nrv.getRole();
        ejb.create(role);
        return "roles";
    }

}
