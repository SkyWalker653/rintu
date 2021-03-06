// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import tools.automation.bean.ProjectMaster;

privileged aspect ProjectMaster_Roo_DbManaged {
    
    @Column(name = "sap_code", length = 10)
    private String ProjectMaster.sapCode;
    
    @Column(name = "name", length = 100)
    private String ProjectMaster.name;
    
    @Column(name = "pm_sap_code", length = 10)
    private String ProjectMaster.pmSapCode;
    
    @Column(name = "pm_name", length = 100)
    private String ProjectMaster.pmName;
    
    @Column(name = "pm_email", length = 100)
    private String ProjectMaster.pmEmail;
    
    @Column(name = "lob_name", length = 100)
    private String ProjectMaster.lobName;
    
    @Column(name = "du_name", length = 100)
    private String ProjectMaster.duName;
    
    @Column(name = "sdu_name", length = 100)
    private String ProjectMaster.sduName;
    
    @Column(name = "linked_sap_code", length = 10)
    private String ProjectMaster.linkedSapCode;
    
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date ProjectMaster.startDate;
    
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "M-")
    private Date ProjectMaster.endDate;
    
    @Column(name = "alt_appr_sap", length = 10)
    private String ProjectMaster.altApprSap;
    
    @Column(name = "alt_appr_name", length = 100)
    private String ProjectMaster.altApprName;
    
    @Column(name = "alt_appr_email", length = 100)
    private String ProjectMaster.altApprEmail;
    
    @Column(name = "status", length = 10)
    private String ProjectMaster.status;
    
    @Column(name = "team_size")
    private Short ProjectMaster.teamSize;
    
    public String ProjectMaster.getSapCode() {
        return sapCode;
    }
    
    public void ProjectMaster.setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }
    
    public String ProjectMaster.getName() {
        return name;
    }
    
    public void ProjectMaster.setName(String name) {
        this.name = name;
    }
    
    public String ProjectMaster.getPmSapCode() {
        return pmSapCode;
    }
    
    public void ProjectMaster.setPmSapCode(String pmSapCode) {
        this.pmSapCode = pmSapCode;
    }
    
    public String ProjectMaster.getPmName() {
        return pmName;
    }
    
    public void ProjectMaster.setPmName(String pmName) {
        this.pmName = pmName;
    }
    
    public String ProjectMaster.getPmEmail() {
        return pmEmail;
    }
    
    public void ProjectMaster.setPmEmail(String pmEmail) {
        this.pmEmail = pmEmail;
    }
    
    public String ProjectMaster.getLobName() {
        return lobName;
    }
    
    public void ProjectMaster.setLobName(String lobName) {
        this.lobName = lobName;
    }
    
    public String ProjectMaster.getDuName() {
        return duName;
    }
    
    public void ProjectMaster.setDuName(String duName) {
        this.duName = duName;
    }
    
    public String ProjectMaster.getSduName() {
        return sduName;
    }
    
    public void ProjectMaster.setSduName(String sduName) {
        this.sduName = sduName;
    }
    
    public String ProjectMaster.getLinkedSapCode() {
        return linkedSapCode;
    }
    
    public void ProjectMaster.setLinkedSapCode(String linkedSapCode) {
        this.linkedSapCode = linkedSapCode;
    }
    
    public Date ProjectMaster.getStartDate() {
        return startDate;
    }
    
    public void ProjectMaster.setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date ProjectMaster.getEndDate() {
        return endDate;
    }
    
    public void ProjectMaster.setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String ProjectMaster.getAltApprSap() {
        return altApprSap;
    }
    
    public void ProjectMaster.setAltApprSap(String altApprSap) {
        this.altApprSap = altApprSap;
    }
    
    public String ProjectMaster.getAltApprName() {
        return altApprName;
    }
    
    public void ProjectMaster.setAltApprName(String altApprName) {
        this.altApprName = altApprName;
    }
    
    public String ProjectMaster.getAltApprEmail() {
        return altApprEmail;
    }
    
    public void ProjectMaster.setAltApprEmail(String altApprEmail) {
        this.altApprEmail = altApprEmail;
    }
    
    public String ProjectMaster.getStatus() {
        return status;
    }
    
    public void ProjectMaster.setStatus(String status) {
        this.status = status;
    }
    
    public Short ProjectMaster.getTeamSize() {
        return teamSize;
    }
    
    public void ProjectMaster.setTeamSize(Short teamSize) {
        this.teamSize = teamSize;
    }
    
}
