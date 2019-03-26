import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
  public static HttpServer create(InetSocketAddress sock, String assetDir)
    throws IOException {

    HttpServer ret = HttpServer.create(sock, 0);
    ret.createContext("/", new FileHandler(assetDir));
    ret.setExecutor(null);
    return ret;
  }
}

/**
 * Handles serving of files from a diretory
 */
class FileHandler implements HttpHandler{
  private String assetDir;

  public FileHandler(String assetDir) {
    this.assetDir = assetDir;
  }

  public void handle(HttpExchange ex) throws IOException {
    if (!ex.getRequestMethod().equals("GET")) {
      Http.error(ex, Http.METHOD_NOT_ALLOWED, "got " + ex.getRequestMethod()
          + " GET");
    };

    // escape relative path segments
    String p = ex.getRequestURI().getPath();
    int last = p.indexOf('?');
    p = this.assetDir + p.substring(0, last).replaceAll("\\/\\.?\\.\\/", "");

    InputStream src = new FileInputStream(p);
    OutputStream dst = ex.getResponseBody();

    byte[] buf = new byte[1024];
    for (int n = src.read(buf); n != -1; n = src.read(buf)) {
      dst.write(buf, 0, n);
    }

    dst.close();
  }

}
