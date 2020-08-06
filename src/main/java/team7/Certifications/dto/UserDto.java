package team7.Certifications.dto;
import com.sun.istack.NotNull;


public class UserDto {

    private Integer id;
    @NotNull
    private String name;

    @NotNull
    private  String password;


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}

