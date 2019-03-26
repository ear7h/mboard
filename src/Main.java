import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
  public static void main(String[] args) {
    HttpServer srv = null;
    try {
      srv = Server.create(new InetSocketAddress(8080), args[0]);
    } catch(IOException e) {
      System.err.println(e.toString());
      System.exit(1);
    }

    System.out.println("listening on: " + srv.getAddress());
    srv.start();
  }
}
