package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "repository_folder")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "repositoryGroupFolderAccesses", "repository" })
public class RepositoryFolder {

    public static List<tools.automation.bean.RepositoryFolder> findFolderByRepository(int repoId) {
        return entityManager().createQuery("SELECT o FROM RepositoryFolder o WHERE o.repository.id = :repoId", RepositoryFolder.class).setParameter("repoId", repoId).getResultList();
    }

    public static int removeRepositoryFolderByRepositoryAndGroup(int repoId, int folderId) {
        List<RepositoryFolder> folderList = entityManager().createQuery("SELECT o FROM RepositoryFolder o WHERE o.repository.id = :repoId AND o.id = :folderId", RepositoryFolder.class).setParameter("repoId", repoId).setParameter("folderId", folderId).getResultList();
        for (RepositoryFolder repositoryFolder : folderList) {
            repositoryFolder.remove();
        }
        return folderList.size();
    }
}
