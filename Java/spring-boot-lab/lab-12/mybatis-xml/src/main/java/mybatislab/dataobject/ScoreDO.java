package mybatislab.dataobject;

public class ScoreDO {
    private Integer id;
    private String userId;
    private String subject;
    private Double score;
    private String deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "ScoreDO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
