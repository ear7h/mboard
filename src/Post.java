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
  public String getTitle() { return this.title; }
  public String getAuthor() { return this.author; }
  public String getBody() { return this.body; }
  public long getTimeStamp() { return this.timeStamp; }
  public int getVotes() { return this.votes; }

  private void setTitle(String title) { this.title = title; }
  private void setAuthor(String author) { this.author = author; }
  private void setBody(String body) { this.body = body; }
  private void setTimeStamp(long timeStamp) { this.timeStamp = timeStamp; }
  private void setVotes(int votes) { this.votes = votes; }
}
