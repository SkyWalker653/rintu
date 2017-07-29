// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import tools.automation.bean.RepositoryGroup;
import tools.automation.bean.RepositoryGroupUserMap;
import tools.automation.bean.User;

privileged aspect RepositoryGroupUserMap_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "group_name", referencedColumnName = "id", nullable = false)
    private RepositoryGroup RepositoryGroupUserMap.groupName;
    
    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    private User RepositoryGroupUserMap.user;
    
    public RepositoryGroup RepositoryGroupUserMap.getGroupName() {
        return groupName;
    }
    
    public void RepositoryGroupUserMap.setGroupName(RepositoryGroup groupName) {
        this.groupName = groupName;
    }
    
    public User RepositoryGroupUserMap.getUser() {
        return user;
    }
    
    public void RepositoryGroupUserMap.setUser(User user) {
        this.user = user;
    }
    
}