package team7.Certifications.dto;

import com.sun.istack.NotNull;
import team7.Certifications.entity.Quarter;
import team7.Certifications.entity.Status;


public class RequestDto {

    private Integer id;
    @NotNull
    private Quarter quarter;
    @NotNull
    private String participantName;
    @NotNull
    private String certificationTitle;
    @NotNull
    private String category;
    @NotNull
    private Status approvalStatus;
    @NotNull
    private String businessJustification;

    private Double cost;



    public Integer getId() {
        return id;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getCertificationTitle() {
        return certificationTitle;
    }

    public String getCategory() {
        return category;
    }

    public String getBusinessJustification() {
        return businessJustification;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public Status getApprovalStatus() {
        return approvalStatus;
    }

    public Double getCost() {
        return cost;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public void setCertificationTitle(String certificationTitle) {
        this.certificationTitle = certificationTitle;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBusinessJustification(String businessJustification) {
        this.businessJustification = businessJustification;
    }

    public void setQuarter(Quarter quarter) {
        this.quarter = quarter;
    }

    public void setApprovalStatus(Status approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }


}

