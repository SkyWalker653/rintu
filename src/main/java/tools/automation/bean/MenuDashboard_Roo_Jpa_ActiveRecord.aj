// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import tools.automation.bean.MenuDashboard;

privileged aspect MenuDashboard_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager MenuDashboard.entityManager;
    
    public static final EntityManager MenuDashboard.entityManager() {
        EntityManager em = new MenuDashboard().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long MenuDashboard.countMenuDashboards() {
        return entityManager().createQuery("SELECT COUNT(o) FROM MenuDashboard o", Long.class).getSingleResult();
    }
    
    public static List<MenuDashboard> MenuDashboard.findAllMenuDashboards() {
        return entityManager().createQuery("SELECT o FROM MenuDashboard o", MenuDashboard.class).getResultList();
    }
    
    public static MenuDashboard MenuDashboard.findMenuDashboard(Integer id) {
        if (id == null) return null;
        return entityManager().find(MenuDashboard.class, id);
    }
    
    public static List<MenuDashboard> MenuDashboard.findMenuDashboardEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM MenuDashboard o", MenuDashboard.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void MenuDashboard.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void MenuDashboard.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            MenuDashboard attached = MenuDashboard.findMenuDashboard(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void MenuDashboard.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void MenuDashboard.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public MenuDashboard MenuDashboard.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        MenuDashboard merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}