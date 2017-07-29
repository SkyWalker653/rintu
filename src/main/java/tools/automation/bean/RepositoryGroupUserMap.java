package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "repository_group_user_map")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "groupName", "user" })
public class RepositoryGroupUserMap {

    public static List<tools.automation.bean.RepositoryGroupUserMap> findAllRepositoryGroupUserMapByGroup(int groupId) {
        return entityManager().createQuery("SELECT o FROM RepositoryGroupUserMap o WHERE o.groupName.id = :groupId ", RepositoryGroupUserMap.class).setParameter("groupId", groupId).getResultList();
    }

    public static int findRepositoryGroupUserMapByepositoryAndUser(int repoId, int userId) {
        List<RepositoryGroupUserMap> repoGroupUserMap = entityManager().createQuery("SELECT o FROM RepositoryGroupUserMap o WHERE o.groupName.repository.id = :repoId AND o.user.id = :userId ", RepositoryGroupUserMap.class).setParameter("repoId", repoId).setParameter("userId", userId).getResultList();
        for (RepositoryGroupUserMap repositoryGroupUserMap : repoGroupUserMap) {
            repositoryGroupUserMap.remove();
        }
        return repoGroupUserMap.size();
    }
}
