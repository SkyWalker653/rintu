// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import tools.automation.bean.RepositoryGroupFolderAccess;

privileged aspect RepositoryGroupFolderAccess_Roo_Jpa_Entity {
    
    declare @type: RepositoryGroupFolderAccess: @Entity;
    
    declare @type: RepositoryGroupFolderAccess: @Table(name = "repository_group_folder_access");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer RepositoryGroupFolderAccess.id;
    
    public Integer RepositoryGroupFolderAccess.getId() {
        return this.id;
    }
    
    public void RepositoryGroupFolderAccess.setId(Integer id) {
        this.id = id;
    }
    
}
