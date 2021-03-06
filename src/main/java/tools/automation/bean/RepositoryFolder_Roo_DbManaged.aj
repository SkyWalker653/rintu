// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import tools.automation.bean.RepositoryFolder;
import tools.automation.bean.RepositoryGroupFolderAccess;
import tools.automation.bean.SvnRepository;

privileged aspect RepositoryFolder_Roo_DbManaged {
    
    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private Set<RepositoryGroupFolderAccess> RepositoryFolder.repositoryGroupFolderAccesses;
    
    @ManyToOne
    @JoinColumn(name = "repository", referencedColumnName = "id", nullable = false)
    private SvnRepository RepositoryFolder.repository;
    
    @Column(name = "folder_path", length = 100)
    @NotNull
    private String RepositoryFolder.folderPath;
    
    @Column(name = "status", length = 10)
    @NotNull
    private String RepositoryFolder.status;
    
    @Column(name = "created_on")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date RepositoryFolder.createdOn;
    
    public Set<RepositoryGroupFolderAccess> RepositoryFolder.getRepositoryGroupFolderAccesses() {
        return repositoryGroupFolderAccesses;
    }
    
    public void RepositoryFolder.setRepositoryGroupFolderAccesses(Set<RepositoryGroupFolderAccess> repositoryGroupFolderAccesses) {
        this.repositoryGroupFolderAccesses = repositoryGroupFolderAccesses;
    }
    
    public SvnRepository RepositoryFolder.getRepository() {
        return repository;
    }
    
    public void RepositoryFolder.setRepository(SvnRepository repository) {
        this.repository = repository;
    }
    
    public String RepositoryFolder.getFolderPath() {
        return folderPath;
    }
    
    public void RepositoryFolder.setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }
    
    public String RepositoryFolder.getStatus() {
        return status;
    }
    
    public void RepositoryFolder.setStatus(String status) {
        this.status = status;
    }
    
    public Date RepositoryFolder.getCreatedOn() {
        return createdOn;
    }
    
    public void RepositoryFolder.setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
}
