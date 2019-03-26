import com.google.gson.Gson;
import java.io.Reader;

class Post {
  public String title;
  public String author;
  public String body;
  public long timeStamp;
  public int votes; // negative or positive

  /**
   * Construct a Post object from a stream of data
   * ie a file or http body
   */
  public static Post fromJson(Reader source) {
    return new Gson().fromJson(source, Post.class);
  }

  public String toJson() {
    return new Gson().toJson(this);
  }

  // TODO: public getters for fields, private setters for fields
}
