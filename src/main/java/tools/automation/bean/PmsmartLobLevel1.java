package tools.automation.bean;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "pmsmart_lob_level_1")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "pmsmartLobLevel2s", "pmsmartProjects" })
public class PmsmartLobLevel1 {
}
