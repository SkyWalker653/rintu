// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import tools.automation.bean.PeiConsolidation;
import tools.automation.bean.PeiConsolidationModules;

privileged aspect PeiConsolidationModules_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "pei_consolidation", referencedColumnName = "id", nullable = false)
    private PeiConsolidation PeiConsolidationModules.peiConsolidation;
    
    @Column(name = "name", length = 50)
    @NotNull
    private String PeiConsolidationModules.name;
    
    @Column(name = "status", length = 15)
    @NotNull
    private String PeiConsolidationModules.status;
    
    public PeiConsolidation PeiConsolidationModules.getPeiConsolidation() {
        return peiConsolidation;
    }
    
    public void PeiConsolidationModules.setPeiConsolidation(PeiConsolidation peiConsolidation) {
        this.peiConsolidation = peiConsolidation;
    }
    
    public String PeiConsolidationModules.getName() {
        return name;
    }
    
    public void PeiConsolidationModules.setName(String name) {
        this.name = name;
    }
    
    public String PeiConsolidationModules.getStatus() {
        return status;
    }
    
    public void PeiConsolidationModules.setStatus(String status) {
        this.status = status;
    }
    
}
