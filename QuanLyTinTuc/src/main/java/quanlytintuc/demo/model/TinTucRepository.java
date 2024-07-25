package quanlytintuc.demo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TinTucRepository extends CrudRepository<TinTuc, String> {
    @Query("SELECT t FROM TinTuc t WHERE 1=1 And" +
            "(t.maTinTuc = :tuKhoa OR t.tieuDe LIKE %:tuKhoa% OR t.tacGia LIKE %:tuKhoa%) And "+"(t.maChuDe Like %:maCD% )" )


    List<TinTuc> timKiemTinTuc(@Param("tuKhoa") String tuKhoa ,@Param("maCD") String maCD);

}
