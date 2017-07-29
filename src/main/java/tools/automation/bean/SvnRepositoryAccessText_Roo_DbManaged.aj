// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import tools.automation.bean.SvnRepository;
import tools.automation.bean.SvnRepositoryAccessText;

privileged aspect SvnRepositoryAccessText_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "repository", referencedColumnName = "id", nullable = false)
    private SvnRepository SvnRepositoryAccessText.repository;
    
    @Column(name = "status", length = 50)
    @NotNull
    private String SvnRepositoryAccessText.status;
    
    public SvnRepository SvnRepositoryAccessText.getRepository() {
        return repository;
    }
    
    public void SvnRepositoryAccessText.setRepository(SvnRepository repository) {
        this.repository = repository;
    }
    
    public String SvnRepositoryAccessText.getStatus() {
        return status;
    }
    
    public void SvnRepositoryAccessText.setStatus(String status) {
        this.status = status;
    }
    
}