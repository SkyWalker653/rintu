package tools.automation.bean;



import javax.persistence.TypedQuery;

import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(versionField = "", table = "project_master")
@RooDbManaged(automaticallyDelete = true)
public class ProjectMaster {

    public static ProjectMaster findProjectCodeBySap(String projectCode) {
        TypedQuery<ProjectMaster> query = entityManager().createQuery("SELECT o FROM ProjectMaster o WHERE o.sapCode = :projectCode", ProjectMaster.class);
        return query.setParameter("projectCode", projectCode).getSingleResult();
    }
    
    public static ProjectMaster findTotalTeamSize(String linkedProjectSapCode, String sapCode) {
        //TypedQuery<ProjectMaster> query = entityManager().createQuery("SELECT o FROM ProjectMaster o WHERE o.sapCode = :projectCode AND o.pmSapCode = :pmSapCode", ProjectMaster.class);
        
        
        //TypedQuery<ProjectMaster> query = entityManager().createQuery("SELECT SUM(PM.noOfUsers) teamSize FROM ProjectMaster PM WHERE PM.sapCode IN(:sapCode, :sapCode) AND PM.pmSapCode = (SELECT PM1.pmSapCode FROM ProjectMaster PM1 WHERE PM1.sapCode = :sapCode)", ProjectMaster.class);
        //return query.setParameter("sapCode", linkedProjectSapCode).getSingleResult();
        //.setParameter("sapCode", sapCode)
    	
    	//TypedQuery<ProjectMaster> query = entityManager().createQuery("SELECT o.teamSize FROM ProjectMaster o WHERE o.sapCode = :linkedProjectSapCode", ProjectMaster.class);
    	
    	//ProjectMaster a = query.setParameter("sapCode", linkedProjectSapCode).getSingleResult();
    	//System.out.println("****************************************** " + a.toString() );
    	
    	return entityManager().createQuery("SELECT o.teamSize FROM ProjectMaster o WHERE o.sapCode = :sapCode", ProjectMaster.class)
    			.setParameter("sapCode", linkedProjectSapCode)
    			.getSingleResult();
    	
    }
}
