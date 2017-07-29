package tools.automation.bean;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "svn_repository_access_text")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "repository" })
public class SvnRepositoryAccessText {

    @Column(name = "repo_access_text", columnDefinition = "blob")
    @NotNull
    private byte[] repoAccessText;

    public static String findRepositoryAccessByRepositoryId(int repoId) {
        return new String(entityManager().createQuery("SELECT o.repoAccessText FROM SvnRepositoryAccessText o WHERE o.repository.id = :id", byte[].class).setParameter("id", repoId).getSingleResult());
    }
}
