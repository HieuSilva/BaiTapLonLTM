/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import javax.servlet.http.HttpSession;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import org.apache.axis.encoding.XMLType;
import javax.xml.rpc.ParameterMode;
import model.IDNoiDung;
import model.NoiDung;
import model.TranDau;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author HIEU
 */
@Controller
public class Control {
    
    private ApplicationContext context = null;
    private JDBCTemplate jdbcTemplate = null;
    private DAO dao = null;
    
    public Control() {
        context = new ClassPathXmlApplicationContext("Beans.xml");
        jdbcTemplate = (JDBCTemplate) context.getBean("jdbcTemplate");
        dao = new DAO();
    }

    @RequestMapping(value = "/admin_login", method = RequestMethod.GET)
    public ModelAndView userLogin() {
        return new ModelAndView("admin_login", "command", new User());
    }

    @RequestMapping(value = "/checkAdminLogin", method = RequestMethod.POST)
    public String checkAdminLogin(@ModelAttribute("SpringWeb") User user, ModelMap model) {
        String username = user.getUsername();
        String password = user.getPassword();
        if(checkLogin(username, password)) {
            return "admin_home";
        }
        return "login_error";
    }
    
    @RequestMapping(value="/lichThiDau", method= RequestMethod.POST)
    public String lichThiDau() {
        TranDau [] listTD = dao.getTranDauList();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("listTranDau", listTD);
        
        return "lich_thi_dau";
    }
    
    @RequestMapping(value = "/cap_nhat_ket_qua", method = RequestMethod.GET)
    public ModelAndView ketQua() {
        return new ModelAndView("cap_nhat_ket_qua", "command", new TranDau());
    }

    @RequestMapping(value = "/updateKetQuaTranDau", method = RequestMethod.POST)
    public String updateKetQuaTranDau(@ModelAttribute("SpringWeb") TranDau trandau, ModelMap model) {
        if(dao.updateKetQuaTranDau(trandau)) {
            return "update_success";
        }
        return "update_error";
    }
    
    @RequestMapping(value = "/chon_nhanh", method = RequestMethod.GET)
    public ModelAndView nhanh() {
        return new ModelAndView("chon_nhanh", "command", new IDNoiDung());
    }
    
    @RequestMapping(value="/chonNhanhNoiDung", method=RequestMethod.POST)
    public String nhanhThiDau(@ModelAttribute("command") IDNoiDung idNoiDung) {
        NoiDung nd = dao.getNoiDungById(idNoiDung.getIdNoiDung());
        System.out.println(nd.getTen());
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("noiDung", nd);
        //if(nd.getId() < 3)
            return "chon_nhanh";
        //return "nhanh_thi_dau_doi";
    }
    
//    @RequestMapping(value = "/list_van_dong_vien", method = RequestMethod.GET)
//    public ModelAndView list() {
//        return new ModelAndView("list_van_dong_vien", "command", new VanDongVien());
//    }
//
//    @RequestMapping(value = "/listVanDongVien", method = RequestMethod.POST)
//    public String listVanDongVien(@ModelAttribute("SpringWeb") VanDongVien vdv, ModelMap model) {
//        List<VanDongVien> listVDV = jdbcTemplate.getListVanDongVien();
//        model.addAttribute("listVDV", listVDV);
//        System.out.println(listVDV);
//        return "list_van_dong_vien";
//    }
    
    
    
    
    
    
    private boolean checkLogin(String username, String password) {
        boolean result = false;
        String endpointURL = "http://localhost:8080/axis/AppWebService.jws";
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpointURL));
            call.setOperationName("checkLogin");
            call.addParameter("username", XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);
            call.addParameter("password", XMLType.XSD_STRING,ParameterMode.PARAM_MODE_IN);
            call.setReturnType(XMLType.XSD_BOOLEAN);
            result = (Boolean) call.invoke(new Object[]{username, password});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
