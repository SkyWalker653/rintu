package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "role_dashboard_menu_map")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "menuDashboard", "role" })
public class RoleDashboardMenuMap {

    public static List<tools.automation.bean.RoleDashboardMenuMap> findMapByRole(int roleId) {
        return entityManager().createQuery("SELECT o FROM RoleDashboardMenuMap o WHERE o.role.id = :roleId ", RoleDashboardMenuMap.class).setParameter("roleId", roleId).getResultList();
    }
}
