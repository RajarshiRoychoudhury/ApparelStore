package com.example.apparel;

//import org.apache.catalina.Context;
//
//import org.apache.catalina.connector.Connector;
//import org.apache.tomcat.util.descriptor.web.SecurityCollection;
//import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//
//
//public class ServerConfig {
//	@Bean
//	public TomcatServletWebServerFactory servletContainer() {
//	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//	        @Override
//	        protected void postProcessContext(Context context) {
//	            SecurityConstraint securityConstraint = new SecurityConstraint();
//	            securityConstraint.setUserConstraint("CONFIDENTIAL");
//	            SecurityCollection collection = new SecurityCollection();
//	            collection.addPattern("/*");
//	            securityConstraint.addCollection(collection);
//	            context.addConstraint(securityConstraint);
//	        }
//	    };
//	    tomcat.addAdditionalTomcatConnectors(redirectConnector());
//	    return tomcat;
//	}
//
//	private Connector redirectConnector() {
//	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//	    connector.setScheme("http");
//	    connector.setPort(8080);
//	    connector.setSecure(false);
//	    connector.setRedirectPort(8443);
//	    return connector;
//	}
//#server.http.port = 8080
//#server.port = 8443
//#server.ssl.key-store=/home/rajarshi/Desktop/ssl/keystore.p12
//#server.ssl.key-store-password=password
//#server.ssl.key-store-type = pkcs12
//#server.ssl.key-alias = tomcat
//#server.ssl.key-password = password
//}
