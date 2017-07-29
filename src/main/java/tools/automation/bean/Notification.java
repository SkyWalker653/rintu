package tools.automation.bean;

import java.util.Calendar;
import java.util.List;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "notification")
@RooDbManaged(automaticallyDelete = true)
public class Notification {

    public static void addNotification(int user, String notificationType, String note, String details) {
        Notification notification = new Notification();
        notification.setDateTime(Calendar.getInstance());
        notification.setUser(user);
        notification.setNotificationType(notificationType);
        notification.setNotification(note);
        notification.setDetails(details);
        notification.setHasRead("NO");
        notification.merge();
        notification.flush();
    }

    public static List<tools.automation.bean.Notification> findFirst30NotificationByUser(Integer userID) {
        return entityManager().createQuery("SELECT o FROM Notification o WHERE o.user = :userID ORDER BY o.dateTime DESC", Notification.class).setParameter("userID", userID).setMaxResults(30).getResultList();
    }
}
