// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tools.automation.bean.SvnRepository;

privileged aspect SvnRepository_Roo_ToString {
    
    public String SvnRepository.toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("pmsmartAccessRequests", "repositoryFolders", "repositoryGroups", "repositoryUserMaps", "svnRepositoryAccessTexts", "svnRequestFileRestorations", "svnRequestRepositoryAccesses", "alternateApprover", "managerApproved", "projectManager", "primaryApprover", "spoc", "stage1Approved", "stage2Approved").toString();
    }
    
}
