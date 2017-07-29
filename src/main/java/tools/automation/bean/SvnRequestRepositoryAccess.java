package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "svn_request_repository_access")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "approvedBy", "repoGroup", "repository", "requestedBy", "user" })
public class SvnRequestRepositoryAccess {

    public static List<tools.automation.bean.SvnRequestRepositoryAccess> findAllSvnRequestRepositoryAccessesByUser(String loginId) {
        return entityManager().createQuery("SELECT o FROM SvnRequestRepositoryAccess o WHERE o.user.loginId = :loginId OR o.requestedBy.loginId = :loginId", SvnRequestRepositoryAccess.class).setParameter("loginId", loginId).getResultList();
    }
}
