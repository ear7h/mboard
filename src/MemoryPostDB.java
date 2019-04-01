import com.google.gson.Gson;
import java.io.Reader;
import java.util.List;

class MemoryPostDB {
  private static final int PAGE_SIZE = 20;
  private List<Post> posts;

  // TODO
  public MemoryPostDB() {}

  public int numPosts() {
    return this.posts.size();
  }

  // numPosts() / pageSize();
  public int numPages() {
    return ( this.numPosts() / this.pageSize() );
  } 
  
  public int pageSize() {
    return PAGE_SIZE;
  } 



  // implement these one at a time, I think th easiest would be
  // getByLatest, but you can do whatever u like

  public Post[] getByLatest(int page) {
    if ( page > numPages() ) { return null; }
     
  } 

  public Post[] getByOldest(int page);
  public Post[] getByName(int page);
  public Post[] getByAuthor(int page);
  public Post[] getByVotes(int page);
  public Post[] getByStringSearch(String query, int page);
} // end class
