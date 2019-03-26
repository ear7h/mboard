import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Http related constants
 */
public class Http {
  public int x = 0;
  public static final int OK = 200;
  public static final int BAD_REQUEST = 400;
  public static final int FORBIDDEN = 403;
  public static final int NOT_FOUND = 404;
  public static final int METHOD_NOT_ALLOWED = 405;

  public static String codeToString(int code) {
    switch (code) {
      case OK:
        return "OK";
      case BAD_REQUEST:
        return "Bad Request";
      case FORBIDDEN:
        return "Forbidden";
      case NOT_FOUND:
        return "Not Found";
      case METHOD_NOT_ALLOWED:
        return "Method Not Allowed";
      default:
        return "Code " + Integer.toString(code);
    }
  }

  /**
   * Helper function for sending back an error
   */
  public static void error(HttpExchange ex, int code, String msg)
      throws IOException{

    ex.getResponseHeaders().set("Status", Http.codeToString(code));
    ex.sendResponseHeaders(code, msg.length());

    OutputStream body = ex.getResponseBody();
    body.write(msg.getBytes());
    body.close();
  }
}
