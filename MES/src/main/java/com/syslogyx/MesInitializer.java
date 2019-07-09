package com.syslogyx;


//import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//import com.syslogyx.config.CORSFilter;
import com.syslogyx.config.JPAConfiguration;

//comment below class if you want to create jar file and want to test on tomcat
//	 server running on terminal

//uncomment the below text while creating war file
/**
 * This class uses for CORS filter
 * 
 * @author namrata
 *
 */
@ComponentScan(basePackages = { "com.syslogyx" })
public class MesInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(JPAConfiguration.class);
		ctx.setServletContext(container);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(ctx));

		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		servlet.setMultipartConfig(
				new MultipartConfigElement("/", 1024 * 1024 * 1024, 1024 * 1024 * 1024, 1024 * 1024 * 1024));

//		FilterRegistration.Dynamic fr1 = container.addFilter("corsFilter", new CORSFilter());
//		fr1.setInitParameter("encoding", "UTF-8");
//		fr1.setInitParameter("forceEncoding", "true");
//		fr1.addMappingForUrlPatterns(null, false, "/*");
		ctx.close();

	}
}
