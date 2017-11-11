/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.QuocGia;
import model.VanDongVien;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author HIEU
 */
public class VDVMapper implements RowMapper<VanDongVien> {

    @Override
    public VanDongVien mapRow(ResultSet rs, int rowNum) throws SQLException {
        VanDongVien v = new VanDongVien();
        v.setId(rs.getInt("id"));
        v.setHoten(rs.getString("ho_ten"));
        v.setNgaySinh(rs.getDate("ngay_sinh"));
        v.setGioiTinh(rs.getString("gioi_tinh"));
        v.setDiem(rs.getInt("diem"));
        v.setAnh(rs.getString("anh"));
        v.setMota(rs.getString("mo_ta"));
        int id_quoc_gia =  rs.getInt("id_quoc_gia");
        DAO dao = new DAO();
        QuocGia q = dao.getQuocGia(id_quoc_gia);
        v.setQuocGia(q);
        return v;
    }
    
}
