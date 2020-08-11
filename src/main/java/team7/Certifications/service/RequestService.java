package team7.Certifications.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import team7.Certifications.dto.RequestDto;
import team7.Certifications.entity.*;
import team7.Certifications.exceptions.CustomException;
import team7.Certifications.mapper.RequestMapper;
import team7.Certifications.repository.CertificationRepository;
import team7.Certifications.repository.RequestRepository;
import team7.Certifications.repository.UserRepository;



import java.util.ArrayList;
import java.util.List;
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
        if(dtoRequest.getId()!=null)throw new CustomException(HttpStatus.EXPECTATION_FAILED,"New request should not have an ID");
        Request entityRequest =this.requestMapper.toEntity(dtoRequest);
        Optional<User> user=userRepository.findById(userId);
        entityRequest.setUser(user.get());
        Optional<Certification> certification=certificationRepository.findById(certificationId);
        entityRequest.setCertification(certification.get());
        entityRequest.setApprovalStatus(Status.pending);
        Request savedEntityRequest=this.requestRepository.save(entityRequest);
        RequestDto dto=this.requestMapper.toDto(savedEntityRequest);

        return dto;

    }

    public RequestDto updateRequest(RequestDto dtoRequest,int requestId)
    {
        Optional<Request> existingRequest =this.requestRepository.findById(requestId);
        existingRequest.orElseThrow(() ->new CustomException(HttpStatus.NOT_FOUND,"Request with id:"+requestId+" not found"));

        Request entityRequest =this.requestMapper.toEntity(dtoRequest);
        entityRequest.setApprovalStatus(existingRequest.get().getApprovalStatus());
        entityRequest.setCertification(existingRequest.get().getCertification());
        entityRequest.setUser(existingRequest.get().getUser());
        requestRepository.deleteById(requestId);
        Request updatedEntityRequest=this.requestRepository.save(entityRequest);
        RequestDto dto=this.requestMapper.toDto(updatedEntityRequest);
        return  dto;

    }
    public RequestDto updateStatus(String requestStatus, Integer id)
    {
        System.out.println(requestStatus);
      Optional<Request> existingRequest=this.requestRepository.findById(id);
      existingRequest.orElseThrow(() ->new CustomException(HttpStatus.NOT_FOUND,"Request with id:"+id+" not found"));
      Request request=existingRequest.get();
        Status statusType=null;
        Integer verify=1;

      try {
          statusType = Status.valueOf(requestStatus);

      }catch (IllegalArgumentException exception)
      {
          verify=0;
          throw new CustomException(HttpStatus.BAD_REQUEST,"There is no status called: "+requestStatus);

      }

      finally {
          if(verify==1)
          {
              request.setApprovalStatus(statusType);
              requestRepository.save(request);
              RequestDto updatedRequestDto=this.requestMapper.toDto(request);
              return updatedRequestDto;
          }
      }

     return requestMapper.toDto(existingRequest.get());
    }

    public void deleteRequest(int id)
    {
        Optional<Request> existingRequest =this.requestRepository.findById(id);
        existingRequest.orElseThrow(() ->new CustomException(HttpStatus.NOT_FOUND,"Request with id:"+id+" not found"));
        this.requestRepository.deleteById(id);
    }

    public List<RequestDto> getAllRequests()
    {
      List<Request> requests=requestRepository.findAll();
      List<RequestDto> dtoRequests =this.requestMapper.toDtoList(requests);

      return dtoRequests;
    }


    public List<RequestDto> getRequestsByParticipantName(String participantName)
    {
        List<Request> requests=requestRepository.findAllByParticipantName(participantName);
        List<RequestDto> dtoRequests=this.requestMapper.toDtoList(requests);
        return  dtoRequests;
    }

    public List<RequestDto> getRequestByQuarter(String quarter)
    {
        Quarter quarterType=null;
        Integer verify=1;
        try{
             quarterType=Quarter.valueOf(quarter);

        }catch (IllegalArgumentException e)
        {
            verify=0;
            throw new CustomException(HttpStatus.BAD_REQUEST,"There is no quarter called: "+quarter);

        }
          finally {
            if(verify==1) {
                List<Request> requests = requestRepository.findAllByQuarter(quarterType);
                List<RequestDto> dtoRequests = this.requestMapper.toDtoList(requests);
                return dtoRequests;
            }
        }

        return new ArrayList<>();

    }

    public List<RequestDto> getRequestByApprovalStatus(String status)
    {
        Status statusType=null;
        Integer verify=1;
       try{
           statusType=Status.valueOf(status);
       }catch (IllegalArgumentException e)
       {
           verify=0;
           throw new CustomException(HttpStatus.BAD_REQUEST,"There is no status called: "+status);
       }
       finally {

          if(verify==1) {
              List<Request> requests = requestRepository.findAllByApprovalStatus(statusType);
              List<RequestDto> dtoRequests = this.requestMapper.toDtoList(requests);
              return dtoRequests;
          }
       }

       return new ArrayList<>();

    }

    public List<RequestDto> getRequestByQuarterAndApprovalStatus(String quarter,String status)
    {
        Status statusType=null;
        Quarter quarterType=null;
        Integer verify=1;

        try {
            statusType = Status.valueOf(status);
            quarterType = Quarter.valueOf(quarter);
        }catch (IllegalArgumentException e)
        {
            verify=0;
            if(statusType==null)throw new CustomException(HttpStatus.BAD_REQUEST,"There is no status called: "+status);
            else throw new CustomException(HttpStatus.BAD_REQUEST,"There is no quarter called: "+quarter);
        }

       if(verify==1) {
           List<Request> requests = requestRepository.findAllByQuarterAndApprovalStatus(quarterType, statusType);
           List<RequestDto> dtoRequests = this.requestMapper.toDtoList(requests);
           return dtoRequests;
       }
       return new ArrayList<>();
    }

    public List<RequestDto> getRequestsByCertificationId(Integer id)
    {
        List<Request> requests=requestRepository.findByCertificationId(id);
        List<RequestDto> requestsDto=requestMapper.toDtoList(requests);
        return requestsDto;
    }





}
