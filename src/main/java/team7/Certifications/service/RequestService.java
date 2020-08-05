package team7.Certifications.service;

import org.springframework.stereotype.Service;
import team7.Certifications.dto.RequestDto;
import team7.Certifications.entity.*;
import team7.Certifications.mapper.RequestMapper;
import team7.Certifications.repository.CertificationRepository;
import team7.Certifications.repository.RequestRepository;
import team7.Certifications.repository.UserRepository;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RequestService {


    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;

    public RequestService(RequestRepository requestRepository,RequestMapper requestMapper,UserRepository userRepository,CertificationRepository certificationRepository) {
        this.requestRepository = requestRepository;
        this.requestMapper=requestMapper;
        this.userRepository=userRepository;
        this.certificationRepository=certificationRepository;
    }

    public RequestDto addRequest(RequestDto dtoRequest,Integer userId,Integer certificationId)
    {
        Request entityRequest =this.requestMapper.toEntity(dtoRequest);
        Optional<User> user=userRepository.findById(userId);
        entityRequest.setUser(user.get());
        Optional<Certification> certification=certificationRepository.findById(certificationId);
        entityRequest.setCertification(certification.get());
        Request savedEntityRequest=this.requestRepository.save(entityRequest);
        RequestDto dto=this.requestMapper.toDto(savedEntityRequest);

        return dto;

    }

    public RequestDto updateRequest(RequestDto dtoRequest,int requestId)
    {
        Optional<Request> existingRequest =this.requestRepository.findById(requestId);
        existingRequest.orElseThrow(() ->new IllegalArgumentException("there is no such request"));

        Request entityRequest =this.requestMapper.toEntity(dtoRequest);
        Request updatedEntityRequest=this.requestRepository.save(entityRequest);
        RequestDto dto=this.requestMapper.toDto(updatedEntityRequest);
        return  dto;

    }
    public RequestDto updateStatus(String requestStatus, Integer id)
    {
        System.out.println(requestStatus);
      Optional<Request> existingRequest=this.requestRepository.findById(id);
      existingRequest.orElseThrow(() ->new IllegalArgumentException("there is no such request"));
      Request request=existingRequest.get();
      Status statusType=Status.valueOf(requestStatus);
      request.setApprovalStatus(statusType);
      requestRepository.save(request);
      RequestDto updatedRequestDto=this.requestMapper.toDto(request);
      return updatedRequestDto;

    }

    public void deleteRequest(int id)
    {
        Optional<Request> existingRequest =this.requestRepository.findById(id);
        existingRequest.orElseThrow(() ->new IllegalArgumentException("there is no such request"));
        this.requestRepository.deleteById(id);
    }

    public List<RequestDto> getAllRequests()
    {
      List<Request> requests=requestRepository.findAll();
      List<RequestDto> dtoRequests =this.requestMapper.toDtoList(requests);

      return dtoRequests;
    }


    public List<RequestDto> getRequestsByUserId(int id)
    {
        List<Request> requests=requestRepository.findAllByUserId(id);
        List<RequestDto> dtoRequests=this.requestMapper.toDtoList(requests);
        return  dtoRequests;
    }

    public List<RequestDto> getRequestByQuarter(String quarter)
    {
        Quarter quarterType=Quarter.valueOf(quarter);
            List<Request> requests = requestRepository.findAllByQuarter(quarterType);
            List<RequestDto> dtoRequests = this.requestMapper.toDtoList(requests);
            return dtoRequests;

    }

    public List<RequestDto> getRequestByApprovalStatus(String status)
    {
        Status statusType=Status.valueOf(status);
        List<Request> requests=requestRepository.findAllByApprovalStatus(statusType);
        List<RequestDto> dtoRequests=this.requestMapper.toDtoList(requests);
        return  dtoRequests;
    }

    public List<RequestDto> getRequestByQuarterAndApprovalStatus(String quarter,String status)
    {
        Status statusType=Status.valueOf(status);
        Quarter quarterType=Quarter.valueOf(quarter);
        List<Request> requests=requestRepository.findAllByQuarterAndApprovalStatus(quarterType,statusType);
        List<RequestDto> dtoRequests=this.requestMapper.toDtoList(requests);
        return  dtoRequests;
    }

    public List<RequestDto> getRequestsByCertificationId(Integer id)
    {
        List<Request> requests=requestRepository.findByCertificationId(id);
        List<RequestDto> requestsDto=requestMapper.toDtoList(requests);
        return requestsDto;
    }





}
