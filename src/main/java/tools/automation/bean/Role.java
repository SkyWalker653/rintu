package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "role")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "roleDashboardMenuMaps", "rolePrimaryMenuMaps", "roleSubmenuMaps", "userRoleMaps" })
public class Role {

    public static List<tools.automation.bean.Role> findOtherRolesByUserId(int userId) {
        return entityManager().createQuery("SELECT o FROM Role o WHERE o NOT IN (  SELECT p.role FROM UserRoleMap p WHERE p.user.id = :userId ) ", Role.class).setParameter("userId", userId).getResultList();
    }
}
