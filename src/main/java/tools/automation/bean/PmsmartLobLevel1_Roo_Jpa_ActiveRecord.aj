// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import tools.automation.bean.PmsmartLobLevel1;

privileged aspect PmsmartLobLevel1_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager PmsmartLobLevel1.entityManager;
    
    public static final EntityManager PmsmartLobLevel1.entityManager() {
        EntityManager em = new PmsmartLobLevel1().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long PmsmartLobLevel1.countPmsmartLobLevel1s() {
        return entityManager().createQuery("SELECT COUNT(o) FROM PmsmartLobLevel1 o", Long.class).getSingleResult();
    }
    
    public static List<PmsmartLobLevel1> PmsmartLobLevel1.findAllPmsmartLobLevel1s() {
        return entityManager().createQuery("SELECT o FROM PmsmartLobLevel1 o", PmsmartLobLevel1.class).getResultList();
    }
    
    public static PmsmartLobLevel1 PmsmartLobLevel1.findPmsmartLobLevel1(Integer id) {
        if (id == null) return null;
        return entityManager().find(PmsmartLobLevel1.class, id);
    }
    
    public static List<PmsmartLobLevel1> PmsmartLobLevel1.findPmsmartLobLevel1Entries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM PmsmartLobLevel1 o", PmsmartLobLevel1.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void PmsmartLobLevel1.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void PmsmartLobLevel1.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            PmsmartLobLevel1 attached = PmsmartLobLevel1.findPmsmartLobLevel1(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void PmsmartLobLevel1.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void PmsmartLobLevel1.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public PmsmartLobLevel1 PmsmartLobLevel1.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        PmsmartLobLevel1 merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}