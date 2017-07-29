package tools.automation.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "user")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "pmsmartAccessRequests", "pmsmartAccessRequests1", "pmsmartProjects", "pmsmartProjects1", "pmsmartProjects2", "pmsmartProjects3", "pmsmartProjects4", "repositoryGroupUserMaps", "repositoryUserMaps", "svnRepositories", "svnRepositories1", "svnRepositories2", "svnRepositories3", "svnRepositories4", "svnRequestFileRestorations", "svnRequestFileRestorations1", "svnRequestFileRestorations2", "svnRequestRepositoryAccesses", "svnRequestRepositoryAccesses1", "svnRequestRepositoryAccesses2", "svnRequestRepositoryMigrations", "svnRequestRepositoryMigrations1", "svnRequestRepositoryMigrations2", "svnRequestRepositoryMigrations3", "svnRequestRepositoryMigrations4", "svnRequestRepositoryMigrations5", "svnRequestRepositoryRestorations", "svnRequestRepositoryRestorations1", "svnRequestRepositoryRestorations2", "svnRequestRepositoryRestorations3", "svnRequestRepositoryRestorations4", "svnRequestRepositoryRestorations5", "userRoleMaps", "svnRepositories5" })
public class User {

    @Column(name = "login_id", length = 50)
    @NotNull
    private String loginId;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "full_name", length = 100)
    @NotNull
    private String fullName;

    @Column(name = "sap_code", length = 10)
    @NotNull
    private String sapCode;

    @Column(name = "email_id", length = 50)
    @NotNull
    private String emailId;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "status", length = 10)
    @NotNull
    private String status;

    @Column(name = "user_authentication", length = 10)
    @NotNull
    private String userAuthentication;

    @Column(name = "created_by", length = 10)
    @NotNull
    private String createdBy;

    @Column(name = "created_on")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date createdOn;

    @OneToMany(mappedBy = "projectManager", cascade = CascadeType.ALL)
    private Set<SvnRepository> svnRepositories;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRoleMap> userRoleMaps;

    @OneToMany(mappedBy = "alternateApprover", cascade = CascadeType.ALL)
    private Set<SvnRepository> svnRepositories1;

    @OneToMany(mappedBy = "projectManager", cascade = CascadeType.ALL)
    private Set<SvnRepository> svnRepositories2;

    public User() {
    }

    public User(int id, String fullName, String loginId, String sapCode, String emailId, String phoneNumber, String status, String userAuthentication, String createdBy, Date createdOn) {
        this.setId(id);
        this.setFullName(fullName);
        this.setLoginId(loginId);
        this.setSapCode(sapCode);
        this.setEmailId(emailId);
        this.setPhoneNumber(phoneNumber);
        this.setStatus(status);
        this.setUserAuthentication(userAuthentication);
        this.setCreatedBy(createdBy);
        this.setCreatedOn(createdOn);
    }

    public Set<tools.automation.bean.SvnRepository> getSvnRepositories1() {
        return svnRepositories1;
    }

    public Set<tools.automation.bean.SvnRepository> getSvnRepositories2() {
        return svnRepositories2;
    }

    public static tools.automation.bean.User findUserData(Integer id) {
        TypedQuery<User> query = entityManager().createQuery("SELECT NEW tools.automation.bean.User(o.id, o.fullName, o.loginId, o.sapCode, o.emailId, o.phoneNumber, o.status, o.userAuthentication , o.createdBy, o.createdOn) FROM User o WHERE o.id = :id", User.class);
        return query.setParameter("id", id).getSingleResult();
    }

    public static tools.automation.bean.User findUserDataBySAPCode(String sapCode) {
        TypedQuery<User> query = entityManager().createQuery("SELECT NEW tools.automation.bean.User(o.id, o.fullName, o.loginId, o.sapCode, o.emailId, o.phoneNumber, o.status, o.userAuthentication , o.createdBy, o.createdOn) FROM User o WHERE o.sapCode = :sapCode", User.class);
        return query.setParameter("sapCode", sapCode).getSingleResult();
    }

    public static tools.automation.bean.User findUserDataByEmailID(String emailId) {
        TypedQuery<User> query = entityManager().createQuery("SELECT NEW tools.automation.bean.User(o.id, o.fullName, o.loginId, o.sapCode, o.emailId, o.phoneNumber, o.status, o.userAuthentication , o.createdBy, o.createdOn) FROM User o WHERE o.emailId = :emailId", User.class);
        return query.setParameter("emailId", emailId).getSingleResult();
    }

    public static tools.automation.bean.User findUserDataByLoginID(String loginId) {
        TypedQuery<User> query = entityManager().createQuery("SELECT NEW tools.automation.bean.User(o.id, o.fullName, o.loginId, o.sapCode, o.emailId, o.phoneNumber, o.status, o.userAuthentication , o.createdBy, o.createdOn) FROM User o WHERE o.loginId = :loginId", User.class);
        return query.setParameter("loginId", loginId).getSingleResult();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(String userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public static List<tools.automation.bean.User> findAllUsers() {
        return entityManager().createQuery("SELECT NEW tools.automation.bean.User(o.id, o.fullName, o.loginId, o.sapCode, o.emailId, o.phoneNumber, o.status, o.userAuthentication , o.createdBy, o.createdOn) FROM User o ", User.class).getResultList();
    }

    public static long countUsers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM User o", Long.class).getSingleResult();
    }

    public static tools.automation.bean.User getUserDataByID(int id) {
        return entityManager().createQuery("SELECT NEW tools.automation.bean.User(o.id, o.fullName, o.loginId, o.sapCode, o.emailId, o.phoneNumber, o.status, o.userAuthentication , o.createdBy, o.createdOn) FROM User o WHERE o.id = :id", User.class).setParameter("id", id).getSingleResult();
    }

    public void setSvnRepositories1(Set<tools.automation.bean.SvnRepository> svnRepositories1) {
        this.svnRepositories1 = svnRepositories1;
    }

    public void setSvnRepositories2(Set<tools.automation.bean.SvnRepository> svnRepositories2) {
        this.svnRepositories2 = svnRepositories2;
    }

    public static List<tools.automation.bean.MenuSub> findAllMenuByUserRole(tools.automation.bean.User user) {
        TypedQuery<MenuSub> query = entityManager().createQuery("SELECT DISTINCT(o.menuSub) FROM RoleSubmenuMap o WHERE o.role IN (SELECT r.role from UserRoleMap r WHERE r.user = :user) AND ( o.menuSub.menuDashboard.id = :dashboard OR o.menuSub.menuDashboard IS NULL ) AND o.menuSub.type = 'MENU' ORDER BY o.menuSub.menuPrimary.order ASC ", MenuSub.class);
        return query.setParameter("user", user).getResultList();
    }

    public static tools.automation.bean.User findUserForLogin(tools.automation.bean.User user) {
        TypedQuery<User> query = entityManager().createQuery("SELECT o FROM User o WHERE o.loginId = :login", User.class);
        return query.setParameter("login", user.getLoginId()).getSingleResult();
    }

    public static long countUsersForLogin(tools.automation.bean.User user) {
        TypedQuery<Long> query = entityManager().createQuery("SELECT COUNT(o) FROM User o WHERE o.loginId = :login", Long.class);
        return query.setParameter("login", user.getLoginId()).getSingleResult();
    }

    public static List<tools.automation.bean.MenuDashboard> findAllDashboardMenuByUserRole(tools.automation.bean.User user) {
        TypedQuery<MenuDashboard> query = entityManager().createQuery("SELECT DISTINCT(o.menuDashboard) FROM RoleDashboardMenuMap o WHERE o.role IN (SELECT r.role from UserRoleMap r WHERE r.user = :user)", MenuDashboard.class);
        return query.setParameter("user", user).getResultList();
    }
}
