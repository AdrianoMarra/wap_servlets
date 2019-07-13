package com.pantanalshop.controller;

import com.google.gson.Gson;
import com.mongodb.client.MongoDatabase;
import com.pantanalshop.dao.ProductDAO;
import com.pantanalshop.model.Product;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/API/product")
public class ProductController extends HttpServlet {

    private ProductDAO dao;
    Gson mapper = new Gson();
    ServletContext sc;

    @Override
    public void init() throws ServletException {
        this.dao = new ProductDAO();
        this.sc = this.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        MongoDatabase con = (MongoDatabase) sc.getAttribute("dbdatabase");
        if(req.getParameter("id") != null){
            List<Product> products = dao.getProduct(con, "id", req.getParameter("id"));
            out.print(mapper.toJson(products));
        } else if(req.getParameter("type") != null){
            List<Product> products = dao.getProduct(con, "type", req.getParameter("type"));
            out.print(mapper.toJson(products));
        } else {
            List<Product> products = dao.getAllProducts(con);
            out.print(mapper.toJson(products));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MongoDatabase con = (MongoDatabase) sc.getAttribute("dbdatabase");
        HttpSession session = req.getSession();
        String id = req.getParameter("id");
        String qtd = req.getParameter("qtd");
        String email = (String) session.getAttribute("user");

        if(req.getParameter("remove") != null){
            // Remove a product from cart
            List<String[]> items = (List<String[]>) session.getAttribute("cart");
            List<String[]> newItems = items.stream().filter(item -> !item[7].equals(id))
                                        .collect(Collectors.toList());
            setAttributes(newItems, session);
            dao.removeFromCart(con, newItems, email);
            resp.sendRedirect("/checkout?E=0");
            return;

        } else if(req.getParameter("checkout") != null){
            // Remove all products from cart
            List<String[]> items = Arrays.asList();
            setAttributes(items, session);
            dao.removeAllFromCart(con, email);
            resp.sendRedirect("/checkout?E=10");
            return;
        }

        // Add products to cart
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String pic = req.getParameter("pic");
        String desc = req.getParameter("desc");
        String type = req.getParameter("type");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = timestamp.getTime() + "";
        String[] itemData = {name, id, price, pic, desc, type, qtd, time};
        List<String> product = Arrays.asList(itemData);

        if(dao.addToCart(con, product, email) && email != null){
            List<String[]> items = (List<String[]>) session.getAttribute("cart");
            items.add(itemData);
            setAttributes(items, session);

            resp.sendRedirect("/checkout?E=0");
            return;
        }
        resp.sendRedirect("/product?id=" + id + "&E=1");
    }

    private void setAttributes(List<String[]> items, HttpSession session){
        Double total = items.stream().mapToDouble(item -> Double.parseDouble(item[2]) * Double.parseDouble(item[6])).sum();
        session.setAttribute("cart", items);
        session.setAttribute("total", total);
        session.setAttribute("items", items.size());
    }

    private void emptyCart(List<String[]> items, HttpSession session){
        session.setAttribute("cart", items);
        session.setAttribute("total", 0.0);
        session.setAttribute("items", 0);
    }
}
