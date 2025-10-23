package com.healthcare;

import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.ServerConnector;

public class Application {

    public static void main(String[] args) throws Exception {
        int port = 5000;
        
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server();
        
        ServerConnector connector = new ServerConnector(server);
        connector.setHost("0.0.0.0");
        connector.setPort(port);
        server.addConnector(connector);

        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setResourceBase("src/main/webapp");
        
        webapp.setConfigurations(new org.eclipse.jetty.webapp.Configuration[] {
            new AnnotationConfiguration(),
            new org.eclipse.jetty.webapp.WebXmlConfiguration(),
            new org.eclipse.jetty.webapp.WebInfConfiguration(),
            new org.eclipse.jetty.webapp.MetaInfConfiguration(),
            new org.eclipse.jetty.webapp.FragmentConfiguration(),
            new org.eclipse.jetty.plus.webapp.EnvConfiguration(),
            new org.eclipse.jetty.plus.webapp.PlusConfiguration(),
            new org.eclipse.jetty.webapp.JettyWebXmlConfiguration()
        });
        
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*\\.jar$");
        webapp.setParentLoaderPriority(true);
        
        server.setHandler(webapp);
        
        System.out.println("Starting Healthcare Management System on port " + port);
        System.out.println("Access the application at: http://localhost:" + port);
        
        server.start();
        server.join();
    }
}
