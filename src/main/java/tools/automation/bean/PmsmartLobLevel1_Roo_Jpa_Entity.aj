// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import tools.automation.bean.PmsmartLobLevel1;

privileged aspect PmsmartLobLevel1_Roo_Jpa_Entity {
    
    declare @type: PmsmartLobLevel1: @Entity;
    
    declare @type: PmsmartLobLevel1: @Table(name = "pmsmart_lob_level_1");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer PmsmartLobLevel1.id;
    
    public Integer PmsmartLobLevel1.getId() {
        return this.id;
    }
    
    public void PmsmartLobLevel1.setId(Integer id) {
        this.id = id;
    }
    
}