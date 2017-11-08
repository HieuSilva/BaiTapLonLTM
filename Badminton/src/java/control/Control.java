/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
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
import org.springframework.stereotype.Controller;

/**
 *
 * @author HIEU
 */
@Controller
public class Control {
    
    private ApplicationContext context = null;
    private UserJDBCTemplate userJDBCTemplate = null;
    
    public Control() {
        context = new ClassPathXmlApplicationContext("Beans.xml");
        userJDBCTemplate = (UserJDBCTemplate) context.getBean("userJDBCTemplate");
    }

    @RequestMapping(value = "/admin_login", method = RequestMethod.GET)
    public ModelAndView userLogin() {
        return new ModelAndView("admin_login", "command", new User());
    }

    @RequestMapping(value = "/checkAdminLogin", method = RequestMethod.POST)
    public String checkAdminLogin(@ModelAttribute("SpringWeb") User user, ModelMap model) {
        
        return "login_success";
    }
    
    private User[] checkLogin(User user) {
        User [] userList = null;
        String endpointURL = "http://localhost:8080/axis/CheckLogin.jws";
        Service service = new Service();
        try {
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpointURL));
            call.setOperationName("checkLogin");
            call.addParameter("user", XMLType.XSD_ANYTYPE, ParameterMode.PARAM_MODE_IN);
            call.setReturnType(XMLType.XSD_ANYTYPE);
            userList = (User[]) call.invoke(new Object[]{user});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }
}
