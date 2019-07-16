package com.pantanalshop.controller;

import com.mongodb.client.MongoDatabase;
import com.pantanalshop.dao.UserDAO;
import com.pantanalshop.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/API/register")
public class registerController extends HttpServlet {

    private UserDAO dao;
    private ServletContext sc;

    @Override
    public void init() throws ServletException {
        this.dao = new UserDAO();
        this.sc = this.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MongoDatabase con = (MongoDatabase) sc.getAttribute("dbdatabase");
        String user = req.getParameter("fullName");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String confirm = req.getParameter("confirmPassword");
        String address = req.getParameter("address");
        HttpSession session = req.getSession();
        if(validate(user, email, pass, confirm, address)){
            User me = dao.authenticate(con, email);
            if(me == null){
                int total = dao.countUsers(con);
                if(dao.registerUser(con, total ,user, email, pass, address)){
                    resp.sendRedirect("/?E=0");
                    return;
                }
            }
            req.getRequestDispatcher("/pages/signup.jsp?E=1").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/pages/signup.jsp?E=2").forward(req, resp);
    }

    private boolean validate(String user, String email, String pass, String confirm, String address){
        if(user == null || (email == null || (pass == null || (confirm == null || (address == null)))))
            return false;
        if(!user.matches("^.{3,50}$")) return false;
        if(!email.matches("^.+@\\w+\\.\\w+$")) return false;
        if(!pass.matches("([a-zA-Z]{4,}[0-9]{1,})")) return false;
        if(!pass.equals(confirm)) return false;

        return true;
    }
}
