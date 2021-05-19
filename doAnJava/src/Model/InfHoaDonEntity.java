package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Inf_HoaDon", schema = "dbo", catalog = "ProjectOne")
public class InfHoaDonEntity {
    private String hoaDonId;
    private String soTien;
    private DetailInfRepairEntity detailInfRepairByDetailId;
    private InfDoanhThuSuaEntity infDoanhThuSuaByMdt;

    public InfHoaDonEntity() {
    }

    public InfHoaDonEntity(String hoaDonId, String soTien, DetailInfRepairEntity detailInfRepairByDetailId, InfDoanhThuSuaEntity infDoanhThuSuaByMdt) {
        this.hoaDonId = hoaDonId;
        this.soTien = soTien;
        this.detailInfRepairByDetailId = detailInfRepairByDetailId;
        this.infDoanhThuSuaByMdt = infDoanhThuSuaByMdt;
    }

    @Id
    @Column(name = "HoaDon_Id")
    public String getHoaDonId() {
        return hoaDonId;
    }

    public void setHoaDonId(String hoaDonId) {
        this.hoaDonId = hoaDonId;
    }

    @Basic
    @Column(name = "SoTien")
    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfHoaDonEntity that = (InfHoaDonEntity) o;
        return Objects.equals(hoaDonId, that.hoaDonId) &&
                Objects.equals(soTien, that.soTien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hoaDonId, soTien);
    }

    @ManyToOne
    @JoinColumn(name = "Detail_Id", referencedColumnName = "Detail_Id")
    public DetailInfRepairEntity getDetailInfRepairByDetailId() {
        return detailInfRepairByDetailId;
    }

    public void setDetailInfRepairByDetailId(DetailInfRepairEntity detailInfRepairByDetailId) {
        this.detailInfRepairByDetailId = detailInfRepairByDetailId;
    }

    @ManyToOne
    @JoinColumn(name = "MDT", referencedColumnName = "MDT")
    public InfDoanhThuSuaEntity getInfDoanhThuSuaByMdt() {
        return infDoanhThuSuaByMdt;
    }

    public void setInfDoanhThuSuaByMdt(InfDoanhThuSuaEntity infDoanhThuSuaByMdt) {
        this.infDoanhThuSuaByMdt = infDoanhThuSuaByMdt;
    }
}
