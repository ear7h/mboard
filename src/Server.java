import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;
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
      return;
    };

    String p = ex.getRequestURI().getPath();

    // get index.html when user requests a directory
    if (p.endsWith("/")) {
      p += "index.html";
    }

    // prevent requests to files above our asset root
    // by removing "/../" sequences
    p = this.assetDir + p.replaceAll("\\/\\.\\.\\/", "/");


    File f = new File(p);
    if (!(f.isFile() && f.canRead())) {
      Http.error(ex, Http.NOT_FOUND, "resource not found " + ex.
          getRequestURI().getPath());
      return;
    }

    // pretty sure we'll get no more errors from here out
    ex.sendResponseHeaders(Http.OK, f.length());

    InputStream src = new FileInputStream(p);
    OutputStream dst = ex.getResponseBody();

    // copy via 1 kb buffer
    byte[] buf = new byte[1024];
    for (int n = src.read(buf); n != -1; n = src.read(buf)) {
      dst.write(buf, 0, n);
    }

    // release our resources
    src.close();
    dst.close();
  }

}
