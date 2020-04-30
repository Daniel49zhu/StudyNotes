package mapstructdemo.bo;

public class UserDetailBO {

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public UserDetailBO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String toString() {
        return "UserDetailBO{" +
                "userId=" + userId +
                '}';
    }
}
