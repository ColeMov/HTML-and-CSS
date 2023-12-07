import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;
import java.io.OutputStream;
import java.net.URI;
import java.io.*;
import java.nio.file.Files;

public class WebServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000),0);
        HttpContext context = server.createContext("/");
        context.setHandler(exchange -> {
			URI path = exchange.getRequestURI();
			System.out.println("Server Received HTTP Request");
			
			String requestedFile = path.toString().replace("/","");
			File inFile = new File(requestedFile);

			//Headers responseHeaders = exchange.getResponseHeaders();
			exchange.sendResponseHeaders(200, inFile.length());

			OutputStream outStream  = exchange.getResponseBody();

			Files.copy(inFile.toPath(), outStream);
			outStream.close();
	    });

        server.start();
		System.out.println("Hello Web Server Running...");
    }
}
