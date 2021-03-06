// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import tools.automation.bean.SvnRequestFileRestoration;

privileged aspect SvnRequestFileRestoration_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SvnRequestFileRestoration.entityManager;
    
    public static final EntityManager SvnRequestFileRestoration.entityManager() {
        EntityManager em = new SvnRequestFileRestoration().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SvnRequestFileRestoration.countSvnRequestFileRestorations() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SvnRequestFileRestoration o", Long.class).getSingleResult();
    }
    
    public static List<SvnRequestFileRestoration> SvnRequestFileRestoration.findAllSvnRequestFileRestorations() {
        return entityManager().createQuery("SELECT o FROM SvnRequestFileRestoration o", SvnRequestFileRestoration.class).getResultList();
    }
    
    public static SvnRequestFileRestoration SvnRequestFileRestoration.findSvnRequestFileRestoration(Integer id) {
        if (id == null) return null;
        return entityManager().find(SvnRequestFileRestoration.class, id);
    }
    
    public static List<SvnRequestFileRestoration> SvnRequestFileRestoration.findSvnRequestFileRestorationEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SvnRequestFileRestoration o", SvnRequestFileRestoration.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SvnRequestFileRestoration.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SvnRequestFileRestoration.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SvnRequestFileRestoration attached = SvnRequestFileRestoration.findSvnRequestFileRestoration(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SvnRequestFileRestoration.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SvnRequestFileRestoration.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SvnRequestFileRestoration SvnRequestFileRestoration.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SvnRequestFileRestoration merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
