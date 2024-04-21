package servlets.dto;

public class UserIncomingDto {
    private String userName;

    public UserIncomingDto(String name) {
        this.userName = name;
    }

    public UserIncomingDto() {}


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
