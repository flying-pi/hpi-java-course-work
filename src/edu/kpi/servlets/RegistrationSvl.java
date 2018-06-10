package edu.kpi.servlets;

import edu.kpi.domain.User;
import edu.kpi.services.OrderService;
import edu.kpi.services.UserService;
import edu.kpi.servlets.beans.RegistrationBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/register")
public class RegistrationSvl extends HttpServlet {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PWD1 = "pwd1";
    private static final String PARAM_PWD2 = "pwd2";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_MAIL = "email";
    private static final String PARAM_ADVERT = "advert";

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService)config.getServletContext().getAttribute(Constants.ATTRIBUTE_USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationBean formBean = fillFormBean(request);
        Map<String, String> errors = validate(formBean);

        if (errors.size() > 0) {
            formBean.setPassword1("");
            formBean.setPassword2("");
            request.setAttribute("bean", formBean);
            request.setAttribute("errors", errors);
            request.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        userService.add(transformToDomain(formBean));
        CartUtils.redirect(request, response, "/login?reg_success=1");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationBean formBean = new RegistrationBean();
        request.setAttribute("regBean", formBean);
        request.setAttribute("errors", new HashMap<String, String>());
        request.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
    }

    private Map<String, String> validate(RegistrationBean bean) {
        Map<String, String> errors = new HashMap<>();
        validateString(bean.getLogin(), "\\w{2,16}", PARAM_LOGIN, errors);
        validateString(bean.getPassword1(), "\\w{4,32}", PARAM_PWD1, errors);
        validateString(bean.getName(), ".{2,32}", PARAM_NAME, errors);
        validateString(bean.getEmail(), "\\w+@(\\w+[.])+\\w+", PARAM_MAIL, errors);
        if (bean.getPassword1() != null && ! bean.getPassword1().equals(bean.getPassword2())){
            errors.put(PARAM_PWD2, "Passwords are different!");
        }
        return errors;
    }

    private boolean validateString(String data, String pattern, String key, Map<String, String> map) {
        if (data == null) {
            map.put(key, "Parameter is empty");
            return false;
        }
        if (! data.matches(pattern)) {
            map.put(key, "Parameter has invalid value");
            return false;
        }
        return true;
    }

    private RegistrationBean fillFormBean(HttpServletRequest request) {
        RegistrationBean bean = new RegistrationBean();
        bean.setLogin(request.getParameter(PARAM_LOGIN));
        bean.setPassword1(request.getParameter(PARAM_PWD1));
        bean.setPassword2(request.getParameter(PARAM_PWD2));
        bean.setName(request.getParameter(PARAM_NAME));
        bean.setEmail(request.getParameter(PARAM_MAIL));
        bean.setAdvertising(request.getParameter(PARAM_ADVERT) != null && !request.getParameter(PARAM_ADVERT).trim().equals(""));
        return bean;
    }

    private User transformToDomain(RegistrationBean bean) {
        User user = new User();
        user.setLogin(bean.getLogin());
        user.setPassword(bean.getPassword1());
        user.setEmail(bean.getEmail());
        user.setName(bean.getName());
        user.setAdvertising(bean.isAdvertising());
        return user;
    }
}
