// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import tools.automation.bean.PeiModulesMaster;

privileged aspect PeiModulesMaster_Roo_Jpa_Entity {
    
    declare @type: PeiModulesMaster: @Entity;
    
    declare @type: PeiModulesMaster: @Table(name = "pei_modules_master");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer PeiModulesMaster.id;
    
    public Integer PeiModulesMaster.getId() {
        return this.id;
    }
    
    public void PeiModulesMaster.setId(Integer id) {
        this.id = id;
    }
    
}