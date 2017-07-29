package tools.automation.bean;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "svnedge_servers")
@RooDbManaged(automaticallyDelete = true)
public class SvnedgeServers {

    public static tools.automation.bean.SvnedgeServers findCurrentSvnedgeServer() {
        return entityManager().createQuery("SELECT o FROM SvnedgeServers o WHERE o.status =:status", SvnedgeServers.class).setParameter("status", "Active Current").getSingleResult();
    }
}
