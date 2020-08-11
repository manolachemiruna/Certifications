package team7.Certifications.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team7.Certifications.entity.Quarter;
import team7.Certifications.entity.Status;
import team7.Certifications.entity.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

public List<Request> findAllByQuarter(Quarter quarter);
public List<Request> findAllByApprovalStatus(Status status);
public List<Request> findAllByQuarterAndApprovalStatus(Quarter quarter,Status status);
public List<Request> findAllByUserId(int id);
public List<Request> findByCertificationId(int id);
public List<Request> findAllByParticipantName(String participantName);


}
