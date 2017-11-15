/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DangKyThiDauCaNhan;
import model.DangKyThiDauDoi;
import model.Doi;
import model.NoiDung;
import model.QuocGia;
import model.San;
import model.TranDau;
import model.User;
import model.VanDongVien;

/**
 *
 * @author HIEU
 */
public class DAO {
    
    private Connection con;
    private String dbURL = "jdbc:sqlserver://localhost\\EVALUATION:1433; databasename=BTL; username=sa; password=12345678";
    private String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    public DAO() {
        try {
            Class.forName(driverClass);
            con = DriverManager.getConnection(dbURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public NoiDung[] getNoiDungList() {
        NoiDung [] listND = null;
        String sql = "SELECT * FROM tbl_noi_dung";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listND = new NoiDung[rs.getRow()];
                rs.beforeFirst();
                
                int i=0;
                while(rs.next()) {
                    listND[i++] = new NoiDung(rs.getInt("id"), rs.getString("ten"), rs.getString("mo_ta"));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listND;
    }
    
    public QuocGia getQuocGia(int id) {
        String sql = "SELECT * FROM tbl_quoc_gia WHERE id = ? ";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    QuocGia q = new QuocGia(rs.getInt("id"), rs.getString("ten"), rs.getString("anh"));
                    return q;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User [] check(User user) {
        User [] listUser = null;
        String sql = "SELECT * FROM tbl_user "
                + "WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listUser = new User[rs.getRow()];
                rs.beforeFirst();
                int i = 0;
                while(rs.next()) {
                    listUser[i++] = new User(rs.getInt("id"),
                                             rs.getString("username"),
                                             rs.getString("password"));
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return listUser;
    }
    
    public TranDau [] getTranDauList() {
        TranDau [] listTD = null;
        String sql = "SELECT * FROM tbl_tran_dau";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listTD = new TranDau[rs.getRow()];
                rs.beforeFirst();
                
                int i=0;
                while(rs.next()) {
                    int id = rs.getInt("id");
                    int id_san = rs.getInt("id_san");
                    int id_noi_dung = rs.getInt("id_noi_dung");
                    int vong = rs.getInt("vong");
                    int cap = rs.getInt("cap");
                    int diem = rs.getInt("diem_thuong");
                    
                    Timestamp tg = rs.getTimestamp("thoi_gian");
                    
                    NoiDung nd = getNoiDungById(id_noi_dung);
                    San s = getSanById(id_san);
                    
                    
                    
                    TranDau td = new TranDau();
                    td.setId(id);
                    td.setSan(s);
                    td.setNoiDung(nd);
                    td.setThoiGian(tg);
                    td.setVong(vong);
                    td.setCap(cap);
                    td.setDiemThuong(diem);
                    td.setSet11(rs.getInt("set1_1"));
                    td.setSet21(rs.getInt("set2_1"));
                    td.setSet31(rs.getInt("set3_1"));
                    td.setSet12(rs.getInt("set1_2"));
                    td.setSet22(rs.getInt("set2_2"));
                    td.setSet32(rs.getInt("set3_2"));
                    if(id_noi_dung < 3) {
                        ArrayList<DangKyThiDauCaNhan> listDK = getDangKyCaNhanByTranDau(id);
                        td.setListDangKyCN(listDK);
                    }
                    else {
                        ArrayList<DangKyThiDauDoi> listDK = getDangKyDoiByTranDau(td);
                        td.setListDangKyDoi(listDK);
                    }
                    listTD[i++] = td;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTD;
    }
    
     public NoiDung getNoiDungById(int id) {
        String sql = "SELECT * FROM tbl_noi_dung WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    NoiDung n = new NoiDung(rs.getInt("id"), rs.getString("ten"), rs.getString("mo_ta"));
                    return n;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public San getSanById(int id) {
        String sql = "SELECT * FROM tbl_san "
                + "WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    San s = new San(rs.getInt("id"), rs.getString("ten"), rs.getString("mo_ta"));
                    return s;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public ArrayList<DangKyThiDauCaNhan> getDangKyCaNhanByTranDau(int idTranDau) {
        ArrayList<DangKyThiDauCaNhan> list = null;
        String sql = "SELECT DISTINCT id_van_dong_vien, id_tran_thi_dau, is_first, ghi_chu, dk.id "
                + "FROM tbl_dang_ky_thi_dau_ca_nhan as dk, tbl_tran_dau as td "
                + "WHERE td.id = ? "
                + "AND td.id = dk.id_tran_thi_dau "
                + "ORDER BY dk.id ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idTranDau);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                list = new ArrayList<DangKyThiDauCaNhan>();
                rs.beforeFirst();
                int i=0;
                while(rs.next()) {
                    DangKyThiDauCaNhan dk = new DangKyThiDauCaNhan();
                    VanDongVien v = getVanDongVienById(rs.getInt("id_van_dong_vien"));
                    dk.setVdv(v);
                    dk.setIsFirst(rs.getBoolean("is_first"));
                    dk.setGhiChu(rs.getString("ghi_chu"));
                    list.add(dk);
                }
                return list;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public VanDongVien getVanDongVienById(int idVDV) {
        String sql = "SELECT * FROM tbl_van_dong_vien WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idVDV);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    VanDongVien v = new VanDongVien();
                    v.setId(rs.getInt("id"));
                    v.setHoten(rs.getString("ho_ten"));
                    v.setGioiTinh(rs.getString("gioi_tinh"));
                    
                    int idQuocGia = rs.getInt("id_quoc_gia");
                    QuocGia q = getQuocGiaById(idQuocGia);
                    v.setQuocGia(q);
                    return v;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public QuocGia getQuocGiaById(int idQG) {
        String sql = "SELECT * FROM tbl_quoc_gia WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idQG);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                rs.beforeFirst();
                while(rs.next()) {
                    QuocGia q = new QuocGia(rs.getInt("id"), rs.getString("ten"), rs.getString("anh"));
                    return q;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
     public ArrayList<DangKyThiDauDoi> getDangKyDoiByTranDau(TranDau td) {
        Doi [] listDoi = getDoiByTranDau(td);
        ArrayList<DangKyThiDauDoi> list = null;
        String sql = "SELECT DISTINCT id_doi, id_tran_thi_dau, is_first, ghi_chu, dk.id "
                + "FROM tbl_dang_ky_thi_dau_dong_doi as dk, tbl_tran_dau as td "
                + "WHERE td.id = ? "
                + "AND td.id = dk.id_tran_thi_dau"
                + "ORDER BY dk.id ASC";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, td.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                list = new ArrayList<DangKyThiDauDoi>();
                rs.beforeFirst();
                int i=0;
                while(rs.next()) {
                    DangKyThiDauDoi dk = new DangKyThiDauDoi();
                    dk.setIsFirst(rs.getBoolean("is_first"));
                    dk.setGhiChu(rs.getString("ghi_chu"));
                    list.add(dk);
                }
                list.get(0).setDoi(listDoi[0]);
                list.get(1).setDoi(listDoi[1]);
                return list;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
    public Doi [] getDoiByTranDau(TranDau td) {
        Doi [] list = null;
        String sql = "SELECT d.id as id, d.ten as ten, d.mo_ta as mo_ta "
                + "FROM tbl_doi as d, tbl_dang_ky_thi_dau_dong_doi as dk, tbl_tran_dau as td "
                + "WHERE dk.id_doi = d.id "
                + "AND dk.id_tran_thi_dau = td.id "
                + "AND td.id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, td.getId());
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                list = new Doi[rs.getRow()];
                rs.beforeFirst();
                int i=0;
                while(rs.next()) {
                    Doi d = new Doi();
                    int idDoi = rs.getInt("id");
                    d.setId(idDoi);
                    ArrayList<VanDongVien> listVDV = getVanDongVienByDoi(idDoi);
                    d.setListVDV(listVDV);
                    d.setTenDoi(rs.getString("ten"));
                    d.setMota(rs.getString("mo_ta"));
                    list[i++] = d;
                }
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<VanDongVien> getVanDongVienByDoi(int idDoi) {
        ArrayList<VanDongVien> listVDV = null;
        String sql = "SELECT v.id as id, v.gioi_tinh as gioi_tinh, v.ho_ten as ho_ten, v.id_quoc_gia as id_quoc_gia "
                + "FROM tbl_van_dong_vien as v, tbl_van_dong_vien_doi as vd, tbl_doi as d "
                + "WHERE d.id = ? "
                + "AND vd.id_doi = d.id "
                + "AND vd.id_van_dong_vien = v.id";
        try {
            PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, idDoi);
            ResultSet rs = ps.executeQuery();
            if(rs.last()) {
                listVDV = new ArrayList<VanDongVien>();
                rs.beforeFirst();
                while(rs.next()) {
                    VanDongVien v = new VanDongVien();
                    v.setId(rs.getInt("id"));
                    v.setHoten(rs.getString("ho_ten"));
                    v.setGioiTinh(rs.getString("gioi_tinh"));
                    
                    int idQuocGia = rs.getInt("id_quoc_gia");
                    QuocGia q = getQuocGiaById(idQuocGia);
                    v.setQuocGia(q);
                    listVDV.add(v);
                }
                return listVDV;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public TranDau getTranDauById(int idTranDau) {
        TranDau [] listTD = getTranDauList();
        for(TranDau td: listTD) {
            if(td.getId() == idTranDau)
                return td;
        }
        return null;
    }
    
    public boolean updateKetQuaTranDau(TranDau td) {
        System.out.println("ID "+ td.getId());
        String sql = "UPDATE tbl_tran_dau "
                + "SET set1_1 = ?, "
                + "set1_2 = ?, "
                + "set2_1 = ?, "
                + "set2_2 = ?, "
                + "set3_1 = ?, "
                + "set3_2 = ? "
                + "WHERE id = ?";
        //Không có tỉ số hòa trong 2 set đầu tiên
        if(td.getSet11() == td.getSet12() || 
                td.getSet21() == td.getSet22())
            return false;
        //Nếu thắng 2 set đầu thì không cập nhật kết quả set cuối
        if((td.getSet11() > td.getSet12() && td.getSet21() > td.getSet22()) ||
           (td.getSet11() < td.getSet12() && td.getSet21() < td.getSet22())) {
            td.setSet31(0);
            td.setSet32(0);
        }
        else if(td.getSet31() == td.getSet32()) {
                return false;
        }
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, td.getSet11());
            ps.setInt(2, td.getSet12());
            ps.setInt(3, td.getSet21());
            ps.setInt(4, td.getSet22());
            ps.setInt(5, td.getSet31());
            ps.setInt(6, td.getSet32());
            ps.setInt(7, td.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public char [] getTiSo(TranDau td) {
        char [] tiso = new char[2];
        String call = "{call usp_getTiSo(?,?)}";
        try {
            CallableStatement cs = con.prepareCall(call);
            cs.setInt(1, td.getId());
            cs.registerOutParameter(2, java.sql.Types.CHAR);
            cs.executeUpdate();
            
            String tisoString = cs.getString(2);
            tiso[0] =  tisoString.charAt(0);
            tiso[1] =  tisoString.charAt(1);
            return tiso;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public TranDau getTranDauByNoiDungVongCap(NoiDung nd, int vong, int cap) {
        TranDau [] listTD = getTranDauList();
        for(TranDau td: listTD) {
            if(td.getNoiDung().getId() == nd.getId() && td.getVong() == vong && td.getCap() == cap)
                return td;
        }
        return null;
    }
    
    public ArrayList<TranDau> getTranDauByIdNoiDung(int idNoiDung) {
        ArrayList<TranDau> listTD = new ArrayList<TranDau>();
        TranDau [] list = getTranDauList();
        for(TranDau td: list) {
            if(td.getNoiDung().getId() == idNoiDung)
                listTD.add(td);
        }
        return listTD;
    }
}
