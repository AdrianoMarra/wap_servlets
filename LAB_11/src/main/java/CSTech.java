import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@WebServlet(
        name = "cstechsupport",
        urlPatterns = {"/cstechsupport"},
        initParams = {
                @WebInitParam(name = "email", value ="cstech@mum.edu")
        } )
public class CSTech extends HttpServlet {

    private String supportEmail, ticket;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletConfig ctx = this.getServletConfig();
        supportEmail = ctx.getInitParameter("email");
        ticket = (new Date()).toString();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String mail = req.getParameter("email");

        PrintWriter out = resp.getWriter();
        out.print("<html><head><title>Test</title></head><body>");
        out.print("Thank you! " + name + " for contacting us." +
                "We should receive reply from us with in 24 hrs in your email address " + mail +
                ". Let us know in our support email " + supportEmail + " if you don’t receive reply within 24 hrs. "+
                "Please be sure to attach your reference " + ticket + " in your email.");
        out.print("</body></html>");
    }
}
