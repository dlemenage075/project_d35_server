package fr.univtln.project.d35.server;

import fr.jmallofre.crud.CrudServiceBean;
import org.apache.log4j.PatternLayout;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URI;

/**
 * Hello world!
 */
public class App {

    public static final int DEFAULT_PORT = 9998;

    private static int getPort(int defaultPort) {
        //grab port from environment, otherwise fall back to default port 9998
        String httpPort = System.getProperty("jersey.test.port");
        if (null != httpPort) {
            try {
                return Integer.parseInt(httpPort);
            } catch (NumberFormatException e) {
            }
        }
        return defaultPort;
    }


    /*private static URI getBaseURI() {
        return UriBuilder.fromUri("http://0.0.0.0/api/").port(getPort(DEFAULT_PORT)).build();
    }*/

    private static URI getBaseURI(String hostname, int port) {
        return UriBuilder.fromUri("http://" + hostname + "/api/").port(port).build();
    }

    public static URI baseUri;

    public static HttpServer startServer() throws IOException {
        CrudServiceBean.PU_DB = "h2";
        String hostname = System.getenv("HOSTNAME");

        if (hostname == null) {
            hostname = "localhost";
        }

        String port = System.getenv("PORT");
        if (port == null) {
            CrudServiceBean.PU_DB = CrudServiceBean.PU_DEFAULT;
            port = String.valueOf(DEFAULT_PORT);
        } else
            hostname  = "0.0.0.0";

        baseUri =  getBaseURI(hostname, Integer.valueOf(port));



        ResourceConfig config = new ResourceConfig().packages("fr.univtln.pm12016g3.Server_VOT");
        config.register(LoggingFilter.class);

        HttpHandler handler = ContainerFactory.createContainer(GrizzlyHttpContainer.class, config);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri);
        ServerConfiguration configuration = server.getServerConfiguration();
        configuration.addHttpHandler(handler, "/");

        return server;

    }

    public static void main(String[] args) throws IOException {

        // Grizzly 2 initialization
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                        + "%sapplication.wadl\nHit enter to stop it...",
                baseUri));



        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Stopping server..");
            httpServer.stop();
        }, "shutdownHook"));

        //Quartz Timer initialization
        //Runnable runnable =

        // run
        /*try {
            //new Thread(runnable).start();
            httpServer.start();

            TimerQuartz.run();
            System.out.println("Press CTRL^C to exit..");
            Thread.currentThread().join();
        } catch (Exception e) {
            System.out.println(String.format("There was an error while starting Grizzly HTTP server.\n%s", e.getLocalizedMessage()));
        }*/
    }
}
