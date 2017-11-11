/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.util.List;
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
import model.VanDongVien;
import org.springframework.stereotype.Controller;

/**
 *
 * @author HIEU
 */
@Controller
public class Control {
    
    private ApplicationContext context = null;
    private JDBCTemplate jdbcTemplate = null;
    
    public Control() {
        context = new ClassPathXmlApplicationContext("Beans.xml");
        jdbcTemplate = (JDBCTemplate) context.getBean("jdbcTemplate");
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
    
    @RequestMapping(value = "/list_van_dong_vien", method = RequestMethod.GET)
    public ModelAndView list() {
        return new ModelAndView("list_van_dong_vien", "command", new VanDongVien());
    }

    @RequestMapping(value = "/listVanDongVien", method = RequestMethod.POST)
    public String listVanDongVien(@ModelAttribute("SpringWeb") VanDongVien vdv, ModelMap model) {
        List<VanDongVien> listVDV = jdbcTemplate.getListVanDongVien();
        model.addAttribute("listVDV", listVDV);
        System.out.println(listVDV);
        return "list_van_dong_vien";
    }
    
    
    
    
    
    
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
