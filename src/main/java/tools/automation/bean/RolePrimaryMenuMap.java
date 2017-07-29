package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "role_primary_menu_map")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "role", "primaryManu" })
public class RolePrimaryMenuMap {

    public static List<tools.automation.bean.RolePrimaryMenuMap> findMapByRole(int roleId) {
        return entityManager().createQuery("SELECT o FROM RolePrimaryMenuMap o WHERE o.role.id = :roleId ", RolePrimaryMenuMap.class).setParameter("roleId", roleId).getResultList();
    }
}
