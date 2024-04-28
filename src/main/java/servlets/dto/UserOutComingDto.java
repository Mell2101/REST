package servlets.dto;

public class UserOutComingDto {
    private String id;
    private String name;

    public UserOutComingDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserOutComingDto() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

}
