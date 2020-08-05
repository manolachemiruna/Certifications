package team7.Certifications.dto;
import com.sun.istack.NotNull;


public class UserDto {

    private Integer id;
    @NotNull
    private String name;

    @NotNull
    private String role;



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

