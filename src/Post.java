import com.google.gson;

class Post {
  private String title;
  private String author;
  private String body;
  private long timeStamp;
  private int votes; // negative or positive

  /**
   * Construct a Post object from a stream of data
   * ie a file or http body
   */
  public static Post fromJson(InputStream source) {
    return gson.fromJson(source);
  }

  public String toJson() {
    return new Gson().toJson(this);
  }

  // TODO: public getters for fields, private setters for fields
}
