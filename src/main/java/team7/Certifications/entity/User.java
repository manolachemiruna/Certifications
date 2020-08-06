package team7.Certifications.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table()

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private String permissions="";

    private int active;



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getPermissions() {
        return permissions;
    }

    public int getActive() {
        return active;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<String> getPermissionList(){
        if(this.permissions.length()>0){
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }


    public List<String> getRoleList(){
        if(this.role.length() > 0){
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }

}

