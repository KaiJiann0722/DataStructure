
package control;

import entity.Tutor;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author tankj
 */
public class ModificationRecord {

    private Tutor modifiedTutor;
    private Date modificationDate;
    private String modificationType;

    public ModificationRecord() {
    }

    public ModificationRecord(Tutor modifiedTutor, Date modificationDate, String modificationType) {
        this.modifiedTutor = modifiedTutor;
        this.modificationDate = modificationDate;
        this.modificationType = modificationType;
    }

    public Tutor getModifiedTutor() {
        return modifiedTutor;
    }

    public void setModifiedTutor(Tutor modifiedTutor) {
        this.modifiedTutor = modifiedTutor;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getModificationType() {
        return modificationType;
    }

    public void setModificationType(String modificationType) {
        this.modificationType = modificationType;
    }

}
