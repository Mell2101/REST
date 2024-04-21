package servlets.dto;

public class UserOutcomingDto {
    public UserOutcomingDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserOutcomingDto() {}

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
    private String id;

    private String name;

}
