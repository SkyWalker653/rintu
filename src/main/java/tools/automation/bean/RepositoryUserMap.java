package tools.automation.bean;

import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "repository_user_map")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "repository", "user" })
public class RepositoryUserMap {

    public static List<tools.automation.bean.User> findUsersByRepository(int repoId) {
        TypedQuery<User> query = entityManager().createQuery("SELECT NEW tools.automation.bean.User(o.user.id, o.user.fullName, o.user.loginId, o.user.sapCode, o.user.emailId, o.user.phoneNumber, o.user.status, o.user.userAuthentication , o.user.createdBy, o.user.createdOn) FROM RepositoryUserMap o WHERE o.repository.id = :repoId", User.class);
        return query.setParameter("repoId", repoId).getResultList();
    }

    public static List<tools.automation.bean.RepositoryUserMap> findAllRepositoryUserMaps() {
        return entityManager().createQuery("SELECT o FROM RepositoryUserMap o", RepositoryUserMap.class).getResultList();
    }

    public static int removeRepositoryUserMapByRepositoryAndUser(int repoId, int userId) {
        List<RepositoryUserMap> userMap = entityManager().createQuery("SELECT o FROM RepositoryUserMap o WHERE o.repository.id = :repoId AND o.user.id = :userId", RepositoryUserMap.class).setParameter("repoId", repoId).setParameter("userId", userId).getResultList();
        for (RepositoryUserMap repositoryUserMap : userMap) {
            repositoryUserMap.remove();
        }
        return userMap.size();
    }

    public static long findMapCountByRepositoryUser(int repoId, int userId) {
        return entityManager().createQuery("SELECT COUNT(o) FROM RepositoryUserMap o WHERE o.repository.id = :repoId AND o.user.id = :userId", Long.class).setParameter("repoId", repoId).setParameter("userId", userId).getSingleResult();
    }
}
