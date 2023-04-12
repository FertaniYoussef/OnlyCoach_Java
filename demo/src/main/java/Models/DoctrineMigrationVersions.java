package Models;


public class DoctrineMigrationVersions {

  private String version;
  private java.sql.Timestamp executedAt;
  private long executionTime;


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  public java.sql.Timestamp getExecutedAt() {
    return executedAt;
  }

  public void setExecutedAt(java.sql.Timestamp executedAt) {
    this.executedAt = executedAt;
  }


  public long getExecutionTime() {
    return executionTime;
  }

  public void setExecutionTime(long executionTime) {
    this.executionTime = executionTime;
  }

}
