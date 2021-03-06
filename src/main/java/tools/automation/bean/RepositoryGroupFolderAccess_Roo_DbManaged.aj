// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import tools.automation.bean.RepositoryFolder;
import tools.automation.bean.RepositoryGroup;
import tools.automation.bean.RepositoryGroupFolderAccess;

privileged aspect RepositoryGroupFolderAccess_Roo_DbManaged {
    
    @ManyToOne
    @JoinColumn(name = "folder", referencedColumnName = "id", nullable = false)
    private RepositoryFolder RepositoryGroupFolderAccess.folder;
    
    @ManyToOne
    @JoinColumn(name = "repo_group", referencedColumnName = "id", nullable = false)
    private RepositoryGroup RepositoryGroupFolderAccess.repoGroup;
    
    @Column(name = "access", length = 5)
    @NotNull
    private String RepositoryGroupFolderAccess.access;
    
    @Column(name = "created_on")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date RepositoryGroupFolderAccess.createdOn;
    
    @Column(name = "status", length = 10)
    @NotNull
    private String RepositoryGroupFolderAccess.status;
    
    public RepositoryFolder RepositoryGroupFolderAccess.getFolder() {
        return folder;
    }
    
    public void RepositoryGroupFolderAccess.setFolder(RepositoryFolder folder) {
        this.folder = folder;
    }
    
    public RepositoryGroup RepositoryGroupFolderAccess.getRepoGroup() {
        return repoGroup;
    }
    
    public void RepositoryGroupFolderAccess.setRepoGroup(RepositoryGroup repoGroup) {
        this.repoGroup = repoGroup;
    }
    
    public String RepositoryGroupFolderAccess.getAccess() {
        return access;
    }
    
    public void RepositoryGroupFolderAccess.setAccess(String access) {
        this.access = access;
    }
    
    public Date RepositoryGroupFolderAccess.getCreatedOn() {
        return createdOn;
    }
    
    public void RepositoryGroupFolderAccess.setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    
    public String RepositoryGroupFolderAccess.getStatus() {
        return status;
    }
    
    public void RepositoryGroupFolderAccess.setStatus(String status) {
        this.status = status;
    }
    
}
