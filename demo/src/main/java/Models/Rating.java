package Models;


public class Rating {

  private long id;
  private long userId;
  private long coursId;
  private long note;


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


  public long getNote() {
    return note;
  }

  public void setNote(long note) {
    this.note = note;
  }

}
