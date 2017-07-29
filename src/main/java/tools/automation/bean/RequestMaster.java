package tools.automation.bean;

import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "request_master")
@RooDbManaged(automaticallyDelete = true)
public class RequestMaster {

    public static List<tools.automation.bean.RequestMaster> pendingApprovalList(Integer userId) {
        return entityManager().createQuery("SELECT o FROM RequestMaster o WHERE o.approverId = :approverId ORDER BY o.dateTime DESC", RequestMaster.class).setParameter("approverId", userId).getResultList();
    }

    public static List<tools.automation.bean.RequestMaster> findAllMyRequestMasters(Integer userId) {
        return entityManager().createQuery("SELECT o FROM RequestMaster o WHERE o.requestorId = :requestorId ORDER BY o.dateTime DESC", RequestMaster.class).setParameter("requestorId", userId).getResultList();
    }
}
