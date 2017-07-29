// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import tools.automation.bean.PmsmartProject;
import tools.automation.bean.PmsmartProjectType;

privileged aspect PmsmartProjectType_Roo_DbManaged {
    
    @OneToMany(mappedBy = "projectType", cascade = CascadeType.ALL)
    private Set<PmsmartProject> PmsmartProjectType.pmsmartProjects;
    
    @Column(name = "project_type", length = 100)
    @NotNull
    private String PmsmartProjectType.projectType;
    
    @Column(name = "description", length = 500)
    private String PmsmartProjectType.description;
    
    @Column(name = "created_by")
    private Integer PmsmartProjectType.createdBy;
    
    @Column(name = "created_on")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date PmsmartProjectType.createdOn;
    
    public Set<PmsmartProject> PmsmartProjectType.getPmsmartProjects() {
        return pmsmartProjects;
    }
    
    public void PmsmartProjectType.setPmsmartProjects(Set<PmsmartProject> pmsmartProjects) {
        this.pmsmartProjects = pmsmartProjects;
    }
    
    public String PmsmartProjectType.getProjectType() {
        return projectType;
    }
    
    public void PmsmartProjectType.setProjectType(String projectType) {
        this.projectType = projectType;
    }
    
    public String PmsmartProjectType.getDescription() {
        return description;
    }
    
    public void PmsmartProjectType.setDescription(String description) {
        this.description = description;
    }
    
    public Integer PmsmartProjectType.getCreatedBy() {
        return createdBy;
    }
    
    public void PmsmartProjectType.setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
    
    public Date PmsmartProjectType.getCreatedOn() {
        return createdOn;
    }
    
    public void PmsmartProjectType.setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
}