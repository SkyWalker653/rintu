package tools.automation.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "svn_repository")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "pmsmartAccessRequests", "repositoryFolders", "repositoryGroups", "repositoryUserMaps", "svnRepositoryAccessTexts", "svnRequestFileRestorations", "svnRequestRepositoryAccesses", "alternateApprover", "managerApproved", "projectManager", "primaryApprover", "spoc", "stage1Approved", "stage2Approved" })
public class SvnRepository {

    @Column(name = "repo_access", columnDefinition = "blob")
    private byte[] repoAccess;

    public SvnRepository() {
    }

    public SvnRepository(int id, User alternateApprover, User projectManager, User primaryApprover, User spoc, String repoId, String incidentNumber, String repositoryName, String projectSapCode, String projectName, Integer cmtoolUserCount, Date serviceStartDate, Date serviceEndDate, String purpose, String aboutCmtass, Integer server, String repoCreatedBy, Calendar repoCreatedOn, User stage1Approved, User stage2Approved, String repositoryUuid, String status) {
        this.setId(id);
        this.setAlternateApprover(alternateApprover);
        this.setProjectManager(projectManager);
        this.setPrimaryApprover(primaryApprover);
        this.setSpoc(spoc);
        this.setRepoId(repoId);
        this.setIncidentNumber(incidentNumber);
        this.setRepositoryName(repositoryName);
        this.setProjectSapCode(projectSapCode);
        this.setProjectName(projectName);
        this.setCmtoolUserCount(cmtoolUserCount);
        this.setServiceStartDate(serviceStartDate);
        this.setServiceEndDate(serviceEndDate);
        this.setPurpose(purpose);
        this.setAboutCmtass(aboutCmtass);
        this.setServer(server);
        this.setRepoCreatedBy(repoCreatedBy);
        this.setRepoCreatedOn(repoCreatedOn);
        this.setStage1Approved(stage1Approved);
        this.setStage2Approved(stage2Approved);
        this.setRepositoryUuid(repositoryUuid);
        this.setStatus(status);
    }

    public static List<tools.automation.bean.SvnRepository> findAllSvnRepositorys() {
        return entityManager().createQuery("SELECT o FROM SvnRepository o", SvnRepository.class).getResultList();
    }

    public static List<tools.automation.bean.SvnRepository> findAllSvnRepositorysByUser(String loginID) {
        return entityManager().createQuery("SELECT o FROM SvnRepository o WHERE o.projectManager.loginId = :loginID OR o.primaryApprover.loginId = :loginID OR o.alternateApprover.loginId = :loginID OR o.spoc.loginId = :loginID", SvnRepository.class).setParameter("loginID", loginID).getResultList();
    }

    public static tools.automation.bean.SvnRepository findRepositoryByName(String repoName) {
        return entityManager().createQuery("SELECT NEW tools.automation.bean.SvnRepository( o.id, o.alternateApprover, o.projectManager, o.primaryApprover, o.spoc, o.repoId, o.incidentNumber, o.repositoryName, o.projectSapCode, o.projectName, o.cmtoolUserCount, o.serviceStartDate, o.serviceEndDate, o.purpose, o.aboutCmtass, o.server, o.repoCreatedBy, o.repoCreatedOn, o.stage1Approved, o.stage2Approved, o.repositoryUuid, o.status ) FROM SvnRepository o WHERE o.repositoryName = :repoName", SvnRepository.class).setParameter("repoName", repoName).getSingleResult();
    }

    public static String findRepositoryAccessByRepositoryId(int id) {
        return new String(entityManager().createQuery("SELECT o.repoAccess FROM SvnRepository o WHERE o.id = :id", byte[].class).setParameter("id", id).getSingleResult());
    }

    public static Long findRepositoryCountByName(String repoName) {
        return entityManager().createQuery("SELECT COUNT(o) FROM SvnRepository o WHERE o.repositoryName = :repoName", Long.class).setParameter("repoName", repoName).getSingleResult();
    }

    public static List<tools.automation.bean.SvnRepository> findAllNewRepositories() {
        return entityManager().createQuery("SELECT NEW tools.automation.bean.SvnRepository( o.id, o.alternateApprover, o.projectManager, o.primaryApprover, o.spoc, o.repoId, o.incidentNumber, o.repositoryName, o.projectSapCode, o.projectName, o.cmtoolUserCount, o.serviceStartDate, o.serviceEndDate, o.purpose, o.aboutCmtass, o.server, o.repoCreatedBy, o.repoCreatedOn, o.stage1Approved, o.stage2Approved , o.repositoryUuid, o.status ) FROM SvnRepository o WHERE o.status = :status AND o.server IS NULL", SvnRepository.class).setParameter("status", "Approved").getResultList();
    }
}
