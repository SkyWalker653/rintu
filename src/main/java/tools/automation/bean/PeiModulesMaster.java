package tools.automation.bean;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "pei_modules_master")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "templateCategory" })
public class PeiModulesMaster {
}
