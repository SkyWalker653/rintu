// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import tools.automation.bean.PmsmartProject;

privileged aspect PmsmartProject_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PmsmartProject.entityManager;
    
    public static final EntityManager PmsmartProject.entityManager() {
        EntityManager em = new PmsmartProject().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PmsmartProject.countPmsmartProjects() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PmsmartProject o", Long.class).getSingleResult();
    }
    
    public static List<PmsmartProject> PmsmartProject.findAllPmsmartProjects() {
        return entityManager().createQuery("SELECT o FROM PmsmartProject o", PmsmartProject.class).getResultList();
    }
    
    public static PmsmartProject PmsmartProject.findPmsmartProject(Integer id) {
        if (id == null) return null;
        return entityManager().find(PmsmartProject.class, id);
    }
    
    public static List<PmsmartProject> PmsmartProject.findPmsmartProjectEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PmsmartProject o", PmsmartProject.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PmsmartProject.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PmsmartProject.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PmsmartProject attached = PmsmartProject.findPmsmartProject(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PmsmartProject.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PmsmartProject.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PmsmartProject PmsmartProject.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PmsmartProject merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}