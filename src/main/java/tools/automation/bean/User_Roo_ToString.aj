// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tools.automation.bean.User;

privileged aspect User_Roo_ToString {
    
    public String User.toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("pmsmartAccessRequests", "pmsmartAccessRequests1", "pmsmartProjects", "pmsmartProjects1", "pmsmartProjects2", "pmsmartProjects3", "pmsmartProjects4", "repositoryGroupUserMaps", "repositoryUserMaps", "svnRepositories", "svnRepositories1", "svnRepositories2", "svnRepositories3", "svnRepositories4", "svnRequestFileRestorations", "svnRequestFileRestorations1", "svnRequestFileRestorations2", "svnRequestRepositoryAccesses", "svnRequestRepositoryAccesses1", "svnRequestRepositoryAccesses2", "svnRequestRepositoryMigrations", "svnRequestRepositoryMigrations1", "svnRequestRepositoryMigrations2", "svnRequestRepositoryMigrations3", "svnRequestRepositoryMigrations4", "svnRequestRepositoryMigrations5", "svnRequestRepositoryRestorations", "svnRequestRepositoryRestorations1", "svnRequestRepositoryRestorations2", "svnRequestRepositoryRestorations3", "svnRequestRepositoryRestorations4", "svnRequestRepositoryRestorations5", "userRoleMaps", "svnRepositories5").toString();
    }
    
}