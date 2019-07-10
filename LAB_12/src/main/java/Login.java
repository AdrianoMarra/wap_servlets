import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/login")
public class Login extends HttpServlet {

    private UserList list = new UserList();


    @Override
    public void init(ServletConfig config) throws ServletException {

        for(int i = 1; i < 10; i++){
            list.addUser(new User("User_" + i, "123"));
        }
        list.addUser(new User("Adriano", "123"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("username");
        String pass = req.getParameter("pwd");
        String remember = req.getParameter("remember");
        if(list.validate(user, pass)){
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(5 * 60);
            session.setAttribute("user", user);
            if(remember == null){
                removeCookie(req.getCookies(), resp);
            } else {
                setCookie("user", user, resp);
            }
            setCookie("promo", "$100", resp);
            resp.sendRedirect("/welcome");
            return;
        }
        resp.sendRedirect("/");

    }

    private void setCookie(String name, String value, HttpServletResponse resp){
        int lifetime = 30 * 24 * 60 * 60;
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(lifetime);
        resp.addCookie(cookie);
    }

    private void removeCookie(Cookie[] cookies, HttpServletResponse resp){
        for(Cookie ck: cookies){
            if(ck.getName().equals("user")){
                ck.setMaxAge(0);
                resp.addCookie(ck);
                return;
            }
        }
    }
}
