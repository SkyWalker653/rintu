package tools.automation.bean;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "pmsmart_project")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "createdBy", "level1", "level2", "level3", "level4", "projectType", "projectManager", "supportQa", "referencedBy", "seniorManager" })
public class PmsmartProject {
}
