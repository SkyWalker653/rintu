// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import tools.automation.bean.RolePrimaryMenuMap;

privileged aspect RolePrimaryMenuMap_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager RolePrimaryMenuMap.entityManager;
    
    public static final EntityManager RolePrimaryMenuMap.entityManager() {
        EntityManager em = new RolePrimaryMenuMap().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long RolePrimaryMenuMap.countRolePrimaryMenuMaps() {
        return entityManager().createQuery("SELECT COUNT(o) FROM RolePrimaryMenuMap o", Long.class).getSingleResult();
    }
    
    public static List<RolePrimaryMenuMap> RolePrimaryMenuMap.findAllRolePrimaryMenuMaps() {
        return entityManager().createQuery("SELECT o FROM RolePrimaryMenuMap o", RolePrimaryMenuMap.class).getResultList();
    }
    
    public static RolePrimaryMenuMap RolePrimaryMenuMap.findRolePrimaryMenuMap(Integer id) {
        if (id == null) return null;
        return entityManager().find(RolePrimaryMenuMap.class, id);
    }
    
    public static List<RolePrimaryMenuMap> RolePrimaryMenuMap.findRolePrimaryMenuMapEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM RolePrimaryMenuMap o", RolePrimaryMenuMap.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void RolePrimaryMenuMap.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void RolePrimaryMenuMap.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            RolePrimaryMenuMap attached = RolePrimaryMenuMap.findRolePrimaryMenuMap(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void RolePrimaryMenuMap.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void RolePrimaryMenuMap.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public RolePrimaryMenuMap RolePrimaryMenuMap.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RolePrimaryMenuMap merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}