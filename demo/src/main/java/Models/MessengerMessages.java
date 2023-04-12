package Models;


public class MessengerMessages {

  private long id;
  private String body;
  private String headers;
  private String queueName;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp availableAt;
  private java.sql.Timestamp deliveredAt;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }


  public String getHeaders() {
    return headers;
  }

  public void setHeaders(String headers) {
    this.headers = headers;
  }


  public String getQueueName() {
    return queueName;
  }

  public void setQueueName(String queueName) {
    this.queueName = queueName;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }


  public java.sql.Timestamp getAvailableAt() {
    return availableAt;
  }

  public void setAvailableAt(java.sql.Timestamp availableAt) {
    this.availableAt = availableAt;
  }


  public java.sql.Timestamp getDeliveredAt() {
    return deliveredAt;
  }

  public void setDeliveredAt(java.sql.Timestamp deliveredAt) {
    this.deliveredAt = deliveredAt;
  }

}
