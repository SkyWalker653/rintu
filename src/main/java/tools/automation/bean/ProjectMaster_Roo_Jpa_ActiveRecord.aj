// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import tools.automation.bean.ProjectMaster;

privileged aspect ProjectMaster_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager ProjectMaster.entityManager;
    
    public static final EntityManager ProjectMaster.entityManager() {
        EntityManager em = new ProjectMaster().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long ProjectMaster.countProjectMasters() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ProjectMaster o", Long.class).getSingleResult();
    }
    
    public static List<ProjectMaster> ProjectMaster.findAllProjectMasters() {
        return entityManager().createQuery("SELECT o FROM ProjectMaster o", ProjectMaster.class).getResultList();
    }
    
    public static ProjectMaster ProjectMaster.findProjectMaster(Integer id) {
        if (id == null) return null;
        return entityManager().find(ProjectMaster.class, id);
    }
    
    public static List<ProjectMaster> ProjectMaster.findProjectMasterEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ProjectMaster o", ProjectMaster.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void ProjectMaster.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void ProjectMaster.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ProjectMaster attached = ProjectMaster.findProjectMaster(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void ProjectMaster.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void ProjectMaster.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public ProjectMaster ProjectMaster.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ProjectMaster merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
