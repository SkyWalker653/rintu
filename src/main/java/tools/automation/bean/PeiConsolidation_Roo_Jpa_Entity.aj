// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import tools.automation.bean.PeiConsolidation;

privileged aspect PeiConsolidation_Roo_Jpa_Entity {
    
    declare @type: PeiConsolidation: @Entity;
    
    declare @type: PeiConsolidation: @Table(name = "pei_consolidation");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer PeiConsolidation.id;
    
    public Integer PeiConsolidation.getId() {
        return this.id;
    }
    
    public void PeiConsolidation.setId(Integer id) {
        this.id = id;
    }
    
}
