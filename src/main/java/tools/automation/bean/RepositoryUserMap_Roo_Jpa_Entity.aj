// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import tools.automation.bean.RepositoryUserMap;

privileged aspect RepositoryUserMap_Roo_Jpa_Entity {
    
    declare @type: RepositoryUserMap: @Entity;
    
    declare @type: RepositoryUserMap: @Table(name = "repository_user_map");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer RepositoryUserMap.id;
    
    public Integer RepositoryUserMap.getId() {
        return this.id;
    }
    
    public void RepositoryUserMap.setId(Integer id) {
        this.id = id;
    }
    
}
