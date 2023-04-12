package Models;


public class Adherents {

  private long id;
  private long userId;
  private long coursId;
  private java.sql.Date date;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getCoursId() {
    return coursId;
  }

  public void setCoursId(long coursId) {
    this.coursId = coursId;
  }


  public java.sql.Date getDate() {
    return date;
  }

  public void setDate(java.sql.Date date) {
    this.date = date;
  }

}
