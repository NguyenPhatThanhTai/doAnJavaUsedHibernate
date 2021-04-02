package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "Detail_Inf_Repair", schema = "dbo", catalog = "ProjectOne")
public class DetailInfRepairEntity {
    private String detailId;
    private String repairReason;
    private String repairNote;
    private String repairStatus;
    private Date repairAppointment;
    private String repairMoney;
    private InfRepairEntity infRepairByRepairId;

    public DetailInfRepairEntity(String detailId, String repairReason, String repairNote, String repairStatus, Date repairAppointment, String repairMoney, InfRepairEntity infRepairByRepairId) {
        this.detailId = detailId;
        this.repairReason = repairReason;
        this.repairNote = repairNote;
        this.repairStatus = repairStatus;
        this.repairAppointment = repairAppointment;
        this.repairMoney = repairMoney;
        this.infRepairByRepairId = infRepairByRepairId;
    }

    public DetailInfRepairEntity() {
    }

    @Id
    @Column(name = "Detail_Id")
    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    @Basic
    @Column(name = "Repair_Reason")
    public String getRepairReason() {
        return repairReason;
    }

    public void setRepairReason(String repairReason) {
        this.repairReason = repairReason;
    }

    @Basic
    @Column(name = "Repair_Note")
    public String getRepairNote() {
        return repairNote;
    }

    public void setRepairNote(String repairNote) {
        this.repairNote = repairNote;
    }

    @Basic
    @Column(name = "Repair_Status")
    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    @Basic
    @Column(name = "Repair_Appointment")
    public Date getRepairAppointment() {
        return repairAppointment;
    }

    public void setRepairAppointment(Date repairAppointment) {
        this.repairAppointment = repairAppointment;
    }

    @Basic
    @Column(name = "Repair_Money")
    public String getRepairMoney() {
        return repairMoney;
    }

    public void setRepairMoney(String repairMoney) {
        this.repairMoney = repairMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailInfRepairEntity that = (DetailInfRepairEntity) o;
        return Objects.equals(detailId, that.detailId) &&
                Objects.equals(repairReason, that.repairReason) &&
                Objects.equals(repairNote, that.repairNote) &&
                Objects.equals(repairStatus, that.repairStatus) &&
                Objects.equals(repairAppointment, that.repairAppointment) &&
                Objects.equals(repairMoney, that.repairMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailId, repairReason, repairNote, repairStatus, repairAppointment, repairMoney);
    }

    @ManyToOne
    @JoinColumn(name = "Repair_Id", referencedColumnName = "Repair_Id", nullable = false)
    public InfRepairEntity getInfRepairByRepairId() {
        return infRepairByRepairId;
    }

    public void setInfRepairByRepairId(InfRepairEntity infRepairByRepairId) {
        this.infRepairByRepairId = infRepairByRepairId;
    }
}
