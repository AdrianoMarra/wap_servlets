package com.pantanalshop.controller;

import com.google.gson.Gson;
import com.mongodb.client.MongoDatabase;
import com.pantanalshop.dao.UserDAO;
import com.pantanalshop.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/auth")
public class LoginController extends HttpServlet {

    private UserDAO dao;
    private Gson mapper = new Gson();
    private ServletContext sc;
    private int maxAge = 60 * 60;


    @Override
    public void init() throws ServletException {
        this.dao = new UserDAO();
        this.sc = this.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        String email = req.getParameter("user");
        if(key != null){
            MongoDatabase con = (MongoDatabase) sc.getAttribute("dbdatabase");
            User me = dao.authenticate(con, email);
            HttpSession session = req.getSession();
            if(me != null){
                String item = me.getKey() + "";
                List<String[]> cart = me.getCart();
                int items = me.getCart().size();
                if(item.equals(key)){
                    prepareSession(resp, session, me, email, items, cart);
                    return;
                }
            }
            resp.sendRedirect("/");
        } else {
            auth(req, resp, email);
        }
    }

    private void auth(HttpServletRequest req, HttpServletResponse resp, String email)  throws ServletException, IOException {

        MongoDatabase con = (MongoDatabase) sc.getAttribute("dbdatabase");
        String pass = req.getParameter("pass");
        User me = dao.authenticate(con, email);
        HttpSession session = req.getSession();
        if(me != null){
            if(me.validate(email, pass)){
                List<String[]> cart = me.getCart();
                int items = me.getCart().size();
                prepareSession(resp, session, me, email, items, cart);
                return;
            }
        }
        resp.sendRedirect("/?E=10");
    }

    private void prepareSession(HttpServletResponse resp, HttpSession session, User me, String email, int items, List<String[]> cart)
            throws ServletException, IOException{

        session.setMaxInactiveInterval(maxAge);
        me.setTotal();
        session.setAttribute("user", email);
        session.setAttribute("address", me.getAddress());
        session.setAttribute("cart", cart);
        session.setAttribute("items", items);
        session.setAttribute("total", me.getTotal());


        // Solve the bug with the spaces
        Cookie cookie = new Cookie("user", me.getName());
        cookie.setMaxAge(maxAge);
        resp.addCookie(cookie);
//        cookie = new Cookie("mail", me.getEmail());
//        cookie.setMaxAge(maxAge);
//        resp.addCookie(cookie);
//        cookie = new Cookie("holder", me.getKey() + "");
//        cookie.setMaxAge(maxAge);
//        resp.addCookie(cookie);
        resp.sendRedirect("/");
    }


}
