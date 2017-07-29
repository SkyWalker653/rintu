package tools.automation.bean;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "pmsmart_lob_level_4")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "pmsmartProjects" })
public class PmsmartLobLevel4 {
}
