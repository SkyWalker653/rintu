package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "user_role_map")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "role", "user" })
public class UserRoleMap {

    public static List<tools.automation.bean.Role> findAllRolesByUserId(int userId) {
        return entityManager().createQuery("SELECT o.role FROM UserRoleMap o WHERE o.user.id = :userId ", Role.class).setParameter("userId", userId).getResultList();
    }

    public static tools.automation.bean.UserRoleMap findUserRoleMapByUserAndRole(int userId, int roleId) {
        return entityManager().createQuery("SELECT o FROM UserRoleMap o WHERE o.user.id = :userId AND o.role.id = :roleId ", UserRoleMap.class).setParameter("userId", userId).setParameter("roleId", roleId).getSingleResult();
    }
}
