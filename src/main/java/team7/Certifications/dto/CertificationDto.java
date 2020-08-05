package team7.Certifications.dto;

import com.sun.istack.NotNull;

public class CertificationDto {

    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String category;

    @NotNull
    private Double cost;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
        return cost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
