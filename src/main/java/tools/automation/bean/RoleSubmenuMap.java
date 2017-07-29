package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "role_submenu_map")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "role", "menuSub" })
public class RoleSubmenuMap {

    public static tools.automation.bean.RoleSubmenuMap findMapByRoleAndMenu(int roleId, int menuId) {
        return entityManager().createQuery("SELECT o FROM RoleSubmenuMap o WHERE o.role.id = :roleId AND o.menuSub.id = :menuId ", RoleSubmenuMap.class).setParameter("roleId", roleId).setParameter("menuId", menuId).getSingleResult();
    }

    public static List<tools.automation.bean.RoleSubmenuMap> findMapByRole(int roleId) {
        return entityManager().createQuery("SELECT o FROM RoleSubmenuMap o WHERE o.role.id = :roleId ", RoleSubmenuMap.class).setParameter("roleId", roleId).getResultList();
    }
}
