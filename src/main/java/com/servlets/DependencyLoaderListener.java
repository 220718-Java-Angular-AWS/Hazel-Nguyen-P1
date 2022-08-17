package com.servlets;

import com.services.DatasourceService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Do things for servlet context initialization...
        System.out.println("Initializing servlet context...");
        DatasourceService.getConnection();

        System.out.println("Servlet context initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Do things for servlet context destruction...
        System.out.println("Servlet context destroy()");

    }
}