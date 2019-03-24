/**
 * This interface defines a database of posts.
 * It has various methods for querying the database byt data, name, votes etc.
 * All the query methods take a `page` paremeter, this should be used by
 * the implementer so that it does not have to make large entire copies
 * of the database for each method call.
 */
public interface PostDB {

  public int numPosts();
  public int numPages(); // numPosts() / pageSize();
  public int pageSize(); // should be static const in impl

  // implement these one at a time, I think th easiest would be
  // getByLatest, but you can do whatever u like


  public Post[] getByLatest(int page);
  public Post[] getByOldest(int page);
  public Post[] getByName(int page);
  public Post[] getByAuthor(int page);
  public Post[] getByVotes(int page);
  public Post[] getByStringSearch(String query, int page);
}
