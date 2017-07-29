// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import tools.automation.bean.PmsmartLobLevel1;
import tools.automation.bean.PmsmartLobLevel2;
import tools.automation.bean.PmsmartProject;

privileged aspect PmsmartLobLevel1_Roo_DbManaged {
    
    @OneToMany(mappedBy = "level1", cascade = CascadeType.ALL)
    private Set<PmsmartLobLevel2> PmsmartLobLevel1.pmsmartLobLevel2s;
    
    @OneToMany(mappedBy = "level1", cascade = CascadeType.ALL)
    private Set<PmsmartProject> PmsmartLobLevel1.pmsmartProjects;
    
    @Column(name = "name", length = 50)
    @NotNull
    private String PmsmartLobLevel1.name;
    
    @Column(name = "description", length = 500)
    private String PmsmartLobLevel1.description;
    
    public Set<PmsmartLobLevel2> PmsmartLobLevel1.getPmsmartLobLevel2s() {
        return pmsmartLobLevel2s;
    }
    
    public void PmsmartLobLevel1.setPmsmartLobLevel2s(Set<PmsmartLobLevel2> pmsmartLobLevel2s) {
        this.pmsmartLobLevel2s = pmsmartLobLevel2s;
    }
    
    public Set<PmsmartProject> PmsmartLobLevel1.getPmsmartProjects() {
        return pmsmartProjects;
    }
    
    public void PmsmartLobLevel1.setPmsmartProjects(Set<PmsmartProject> pmsmartProjects) {
        this.pmsmartProjects = pmsmartProjects;
    }
    
    public String PmsmartLobLevel1.getName() {
        return name;
    }
    
    public void PmsmartLobLevel1.setName(String name) {
        this.name = name;
    }
    
    public String PmsmartLobLevel1.getDescription() {
        return description;
    }
    
    public void PmsmartLobLevel1.setDescription(String description) {
        this.description = description;
    }
    
}