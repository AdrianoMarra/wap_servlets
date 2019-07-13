package com.pantanalshop.dao;

import com.mongodb.MongoClientException;
import com.pantanalshop.dao.manager.ConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    ConnectionManager con;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext ctx = servletContextEvent.getServletContext();

        //initialize DB Connection when context is initialized
        String url = ctx.getInitParameter("dbURL");
        String user = ctx.getInitParameter("dbUser");
        String pwd = ctx.getInitParameter("dbPassword");
        String database = ctx.getInitParameter("dbDatabase");

        try{
            this.con = new ConnectionManager(url, user, pwd, database);
            ctx.setAttribute("dbdatabase", this.con.getMongodb());
            System.out.println("Mongodb connected!");
        }catch(MongoClientException e){
            System.out.println("Mongodb connection ERROR!! \n" + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        this.con.close();
    }

}
