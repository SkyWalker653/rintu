// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tools.automation.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import tools.automation.bean.SvnRepository;

privileged aspect SvnRepository_Roo_Jpa_Entity {
    
    declare @type: SvnRepository: @Entity;
    
    declare @type: SvnRepository: @Table(name = "svn_repository");
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer SvnRepository.id;
    
    public Integer SvnRepository.getId() {
        return this.id;
    }
    
    public void SvnRepository.setId(Integer id) {
        this.id = id;
    }
    
}