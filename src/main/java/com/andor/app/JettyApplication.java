package com.andor.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.andor.employee.util.DBUtil;

public class JettyApplication {
	private JettyApplication() {
	}
	 public static void main(String[] args) throws Exception {
	        ServletContextHandler context = new ServletContextHandler();
	        context.setContextPath("/Employee/");
	        Server jettyServer = new Server(Integer.parseInt(System.getProperty("port")));
	        jettyServer.setHandler(context);

	        ServletHolder jerseyServlet = context.addServlet(
	             org.glassfish.jersey.servlet.ServletContainer.class, "/*");
	        jerseyServlet.setInitOrder(0);

	        jerseyServlet.setInitParameter("jersey.config.server.provider.packages"
	        								,"com.andor.employee.rest");
	        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames"
	        								,"org.glassfish.jersey.moxy.json.MoxyFeature");
	        

	        try {
	            jettyServer.start();
	            jettyServer.join();

	        } finally {
	        	DBUtil.closeDBConnection();
	            jettyServer.destroy();
	        }
	    }
}
