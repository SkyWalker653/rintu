// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tools.automation.bean.PmsmartLobLevel2;

privileged aspect PmsmartLobLevel2_Roo_ToString {
    
    public String PmsmartLobLevel2.toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("pmsmartLobLevel3s", "pmsmartProjects", "level1").toString();
    }
    
}