// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import tools.automation.bean.RepositoryGroupFolderAccess;

privileged aspect RepositoryGroupFolderAccess_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager RepositoryGroupFolderAccess.entityManager;
    
    public static final EntityManager RepositoryGroupFolderAccess.entityManager() {
        EntityManager em = new RepositoryGroupFolderAccess().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RepositoryGroupFolderAccess.countRepositoryGroupFolderAccesses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM RepositoryGroupFolderAccess o", Long.class).getSingleResult();
    }
    
    public static List<RepositoryGroupFolderAccess> RepositoryGroupFolderAccess.findAllRepositoryGroupFolderAccesses() {
        return entityManager().createQuery("SELECT o FROM RepositoryGroupFolderAccess o", RepositoryGroupFolderAccess.class).getResultList();
    }
    
    public static RepositoryGroupFolderAccess RepositoryGroupFolderAccess.findRepositoryGroupFolderAccess(Integer id) {
        if (id == null) return null;
        return entityManager().find(RepositoryGroupFolderAccess.class, id);
    }
    
    public static List<RepositoryGroupFolderAccess> RepositoryGroupFolderAccess.findRepositoryGroupFolderAccessEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM RepositoryGroupFolderAccess o", RepositoryGroupFolderAccess.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void RepositoryGroupFolderAccess.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RepositoryGroupFolderAccess.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RepositoryGroupFolderAccess attached = RepositoryGroupFolderAccess.findRepositoryGroupFolderAccess(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RepositoryGroupFolderAccess.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void RepositoryGroupFolderAccess.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public RepositoryGroupFolderAccess RepositoryGroupFolderAccess.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RepositoryGroupFolderAccess merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
