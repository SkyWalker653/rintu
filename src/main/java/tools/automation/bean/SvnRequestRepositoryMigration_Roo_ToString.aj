// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tools.automation.bean.SvnRequestRepositoryMigration;

privileged aspect SvnRequestRepositoryMigration_Roo_ToString {
    
    public String SvnRequestRepositoryMigration.toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("approvedBy", "alternateApproval", "migratedBy", "projectManager", "requestedBy", "projectSpoc").toString();
    }
    
}
