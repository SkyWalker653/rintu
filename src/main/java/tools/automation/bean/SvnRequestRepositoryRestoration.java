package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "svn_request_repository_restoration")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "approvedBy", "alternateApproval", "assignee", "projectManager", "projectSpoc", "userId" })
public class SvnRequestRepositoryRestoration {

    public static List<tools.automation.bean.SvnRequestRepositoryRestoration> findAllSvnRequestRepositoryRestorationsByUser(String loginId) {
        return null;
    }
}
