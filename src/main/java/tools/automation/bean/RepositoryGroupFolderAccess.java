package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "repository_group_folder_access")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "folder", "repoGroup" })
public class RepositoryGroupFolderAccess {

    public static List<tools.automation.bean.RepositoryGroupFolderAccess> findAllRepositoryGroupAndFolder(int groupId, int folderId) {
        return entityManager().createQuery("SELECT o FROM RepositoryGroupFolderAccess o WHERE o.repoGroup.id = :groupId AND o.folder.id = :folderId ", RepositoryGroupFolderAccess.class).setParameter("groupId", groupId).setParameter("folderId", folderId).getResultList();
    }
}
