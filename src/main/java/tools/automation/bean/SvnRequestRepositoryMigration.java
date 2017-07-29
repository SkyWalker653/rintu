package tools.automation.bean;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "svn_request_repository_migration")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "approvedBy", "alternateApproval", "migratedBy", "projectManager", "requestedBy", "projectSpoc" })
public class SvnRequestRepositoryMigration {
}
