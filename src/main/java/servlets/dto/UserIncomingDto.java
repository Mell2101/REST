package servlets.dto;

public class UserIncomingDto {
    private String userName;
    private int id;

    public UserIncomingDto() {}

    public UserIncomingDto(String userName,int id) {
        this.userName = userName;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
