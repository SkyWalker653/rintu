// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tools.automation.bean.RoleDashboardMenuMap;

privileged aspect RoleDashboardMenuMap_Roo_ToString {
    
    public String RoleDashboardMenuMap.toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("menuDashboard", "role").toString();
    }
    
}
