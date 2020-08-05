package team7.Certifications.entity;
import javax.persistence.*;


@Entity
@Table
public class Request {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false)
    private Quarter quarter;
    @Column(nullable = false)
    private String participantName;
    @Column(nullable = false)
    private String certificationTitle;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private  Status approvalStatus;
    @Column(nullable = false)
    private String businessJustification;


    @OneToOne()
    @JoinColumn( name = "user_id", referencedColumnName = "id",nullable = false)
    private User user;

    @OneToOne()
    @JoinColumn( name = "certification_id",referencedColumnName = "id",nullable =false)
    private Certification certification;


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


    public User getUser() {
        return user;
    }

    public Certification getCertification() {
        return certification;
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

    public void setBusinessJustification(String bussinessJustification) {
        this.businessJustification = bussinessJustification;
    }

    public void setQuarter(Quarter quarter) {
        this.quarter = quarter;
    }

    public void setApprovalStatus(Status approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCertification(Certification certification) {
        this.certification = certification;
    }
}
