import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpContext;
import java.io.OutputStream;
import java.net.URI;
import java.io.*;

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
		
			//exchange.sendResponseHeaders(200, response.length());
			//exchange.getResponseHeaders().add("Content-type","text/html");

			OutputStream outStream  = exchange.getResponseBody();
			//outStream.write(response.getBytes());
		
			outStream.close();
	    });

        server.start();
		System.out.println("Hello Web Server Running...");
    }
}
