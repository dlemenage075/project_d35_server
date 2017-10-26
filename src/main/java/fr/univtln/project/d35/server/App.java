package fr.univtln.project.d35.server;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import fr.univtln.project.d35.server.entities.Job;
import fr.univtln.project.d35.server.entities.Profile;
import fr.univtln.project.d35.server.resources.Resource;
import fr.univtln.project.d35.server.resources.ProfileResource;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    private static URI baseUri;
    private static String hostname;
    private static int port;
    private static ResourceConfig resourceConfig;
    private static final Logger LOG = Logger.getLogger(Resource.class.getName());

    private static URI getBaseURI(String hostname, int port) {
        return UriBuilder.fromUri("http://" + hostname + "/api/").port(port).build();
    }

    private static void loadServerProperties() throws IOException {
        // Retrieve properties
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/config.conf"));

        hostname = properties.getProperty("server.hostname");
        port = Integer.parseInt(properties.getProperty("server.port"));


        if (System.getenv("HOSTNAME") != null)
            hostname = System.getenv("HOSTNAME");

        if (System.getenv("PORT") != null)
            port = Integer.valueOf(System.getenv("PORT"));

        resourceConfig = new PackagesResourceConfig("fr.univtln.project.d35.server");

        baseUri =  getBaseURI(hostname, port);
    }

    private static void displayInfo() {
        LOG.log(Level.INFO,String.format("Jersey app started with WADL available at %sapplication.wadl\n", baseUri));
        LOG.log(Level.INFO,"Starting grizzly2...");
        LOG.log(Level.INFO,String.format("BASE_URI : %s ", baseUri));
    }

    protected static HttpServer startServer() throws IOException {
        return GrizzlyServerFactory.createHttpServer(baseUri, resourceConfig);
    }

    public static void main(String[] args) throws IOException {

        loadServerProperties();
        displayInfo();

        // Grizzly 2 initialization
        HttpServer httpServer = startServer();

        Job job = new Job();
        job.setName(Job.NAME.DEVELOPER);

        Profile profile = new Profile();
        profile.setName("Damien");
        profile.setSurname("LEMÉNAGER");
        profile.getJobs().add(job);

        ProfileResource profileResource = new ProfileResource();
        profileResource.insert(profile);

        LOG.warning("Press a key and then on 'Enter' to exit...");
        if ( System.getenv("PORT") == null ) {
            System.in.read();
            httpServer.stop();
        } else {
            System.in.read();
        }





    }
}
