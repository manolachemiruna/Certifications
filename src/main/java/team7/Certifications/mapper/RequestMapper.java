package team7.Certifications.mapper;

import org.springframework.stereotype.Component;
import team7.Certifications.dto.RequestDto;
import team7.Certifications.entity.Request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RequestMapper {


    public RequestDto toDto(Request request)
    {
        RequestDto dto=new RequestDto();
        dto.setId(request.getId());
        dto.setBusinessJustification(request.getBusinessJustification());
        dto.setCategory(request.getCategory());
        dto.setCertificationTitle(request.getCertificationTitle());
        dto.setParticipantName(request.getParticipantName());
        dto.setQuarter(request.getQuarter());
        dto.setApprovalStatus(request.getApprovalStatus());
        Double cost=request.getCertification().getCost();
        dto.setCost(cost);



        return dto;
    }

    public Request toEntity(RequestDto dto)
    {
        Request request=new Request();

        request.setId(dto.getId());
        request.setBusinessJustification(dto.getBusinessJustification());
        request.setCategory(dto.getCategory());
        request.setCertificationTitle(dto.getCertificationTitle());
        request.setParticipantName(dto.getParticipantName());
        request.setQuarter(dto.getQuarter());
        request.setApprovalStatus(dto.getApprovalStatus());



        return request;

    }
    public List<RequestDto> toDtoList(List<Request> requests)
    {
        List <RequestDto> dtoRequests =new ArrayList<>();
        Iterator<Request> iterator=requests.iterator();
        while(iterator.hasNext())
        {
            dtoRequests.add(toDto(iterator.next()));
        }
        return dtoRequests;

    }

    public List<Request> toEntityList(List<RequestDto> dtoRequests)
    {
        List <Request> requests =new ArrayList<>();
        Iterator<RequestDto> iterator=dtoRequests.iterator();
        while(iterator.hasNext())
        {
            requests.add(toEntity(iterator.next()));
        }
        return requests;
    }

}
