package tools.automation.bean;

import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "repository_group")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "pmsmartAccessRequests", "repositoryGroupFolderAccesses", "repositoryGroupUserMaps", "svnRequestRepositoryAccesses", "repository" })
public class RepositoryGroup {

    public static List<tools.automation.bean.RepositoryGroup> findGroupsByRepository(int repoId) {
        TypedQuery<RepositoryGroup> query = entityManager().createQuery("SELECT o FROM RepositoryGroup o WHERE o.repository.id = :repoId", RepositoryGroup.class);
        return query.setParameter("repoId", repoId).getResultList();
    }

    public static int removeRepositoryGroupByRepositoryAndGroup(int repoId, int groupId) {
        List<RepositoryGroup> repositoryGroups = entityManager().createQuery("SELECT o FROM RepositoryGroup o WHERE o.repository.id = :repoId AND o.id = :groupId", RepositoryGroup.class).setParameter("repoId", repoId).setParameter("groupId", groupId).getResultList();
        for (RepositoryGroup repositoryGroup : repositoryGroups) {
            repositoryGroup.remove();
        }
        return repositoryGroups.size();
    }

    public static long findMapCountByRepositoryGroup(int repoId, String groupName) {
        return entityManager().createQuery("SELECT COUNT(o) FROM RepositoryGroup o WHERE o.repository.id = :repoId AND LOWER(o.groupName) = :groupName", Long.class).setParameter("repoId", repoId).setParameter("groupName", groupName.toLowerCase()).getSingleResult();
    }
}
