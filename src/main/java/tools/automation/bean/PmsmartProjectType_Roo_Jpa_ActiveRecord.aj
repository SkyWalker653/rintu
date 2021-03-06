// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import tools.automation.bean.PmsmartProjectType;

privileged aspect PmsmartProjectType_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PmsmartProjectType.entityManager;
    
    public static final EntityManager PmsmartProjectType.entityManager() {
        EntityManager em = new PmsmartProjectType().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PmsmartProjectType.countPmsmartProjectTypes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PmsmartProjectType o", Long.class).getSingleResult();
    }
    
    public static List<PmsmartProjectType> PmsmartProjectType.findAllPmsmartProjectTypes() {
        return entityManager().createQuery("SELECT o FROM PmsmartProjectType o", PmsmartProjectType.class).getResultList();
    }
    
    public static PmsmartProjectType PmsmartProjectType.findPmsmartProjectType(Integer id) {
        if (id == null) return null;
        return entityManager().find(PmsmartProjectType.class, id);
    }
    
    public static List<PmsmartProjectType> PmsmartProjectType.findPmsmartProjectTypeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PmsmartProjectType o", PmsmartProjectType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PmsmartProjectType.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PmsmartProjectType.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PmsmartProjectType attached = PmsmartProjectType.findPmsmartProjectType(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PmsmartProjectType.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PmsmartProjectType.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PmsmartProjectType PmsmartProjectType.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PmsmartProjectType merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
