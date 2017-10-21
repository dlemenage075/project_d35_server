package fr.univtln.project.d35.server;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import fr.univtln.project.d35.server.entities.Job;
import fr.univtln.project.d35.server.entities.Profile;
import fr.univtln.project.d35.server.resources.ProfileResource;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

/**
 * Hello world!
 */
public class App {

    private static String hostname = "0.0.0.0";
    private static int port = 9908;

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

    private static URI getBaseURI(String hostname, int port) {
        return UriBuilder.fromUri("http://" + hostname + "/api/").port(port).build();
    }

    public static URI baseUri;

    protected static HttpServer startServer() throws IOException {

        if (System.getenv("HOSTNAME") != null)
            hostname = System.getenv("HOSTNAME");

        if (System.getenv("PORT") != null)
            port = Integer.valueOf(System.getenv("PORT"));

        ResourceConfig resourceConfig = new PackagesResourceConfig("fr.univtln.project.d35.server");

        baseUri =  getBaseURI(hostname, port);

        System.out.println("Starting grizzly2...");
        System.out.println( "BASE_URI : "+baseUri);
        return GrizzlyServerFactory.createHttpServer(baseUri, resourceConfig);
    }

    public static void main(String[] args) throws IOException {


        // Grizzly 2 initialization
        HttpServer httpServer = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                        + "%sapplication.wadl\nHit enter to stop it...",
                getBaseURI(hostname, port)));

        Client c = Client.create();
        WebResource webResource = c.resource(baseUri);



        Job job = new Job();
        job.setName(Job.NAME.DEVELOPER);

        Profile profile = new Profile();
        profile.setName("Damien");
        profile.setSurname("LEMÉNAGER");
        profile.getJobs().add(job);

        ProfileResource profileResource = new ProfileResource();
        profileResource.insert(profile);


        //Send a get with a String as response
        /*String response0 = webResource.path("profile/my").get(String.class);
        System.out.println(response0);*/

        if ( System.getenv("PORT") == null ) {
            System.in.read();
            httpServer.stop();
        } else {
            while (true) {
                System.in.read();
            }
        }


    }
}
