package team7.Certifications.mapper;

import org.springframework.stereotype.Component;
import team7.Certifications.dto.CertificationDto;
import team7.Certifications.entity.Certification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CertificationMapper {

    public Certification toEntity(CertificationDto dtoCertification)
    {
        Certification certification=new Certification();
        certification.setId(dtoCertification.getId());
        certification.setTitle(dtoCertification.getTitle());
        certification.setCategory(dtoCertification.getCategory());
        certification.setCost(dtoCertification.getCost());
        return  certification;
    }

    public CertificationDto toDto(Certification certification)
    {
        CertificationDto dtoCertification=new CertificationDto();
        dtoCertification.setId(certification.getId());
        dtoCertification.setTitle(certification.getTitle());
        dtoCertification.setCategory(certification.getCategory());
        dtoCertification.setCost(certification.getCost());
        return  dtoCertification;
    }

    public List<CertificationDto> toDtoList(List<Certification> certifications)
    {
        List<CertificationDto> dtoCertifications=new ArrayList<>();
        Iterator<Certification> iterator=certifications.iterator();
        while (iterator.hasNext())
        {
            dtoCertifications.add(toDto(iterator.next()));
        }
        return dtoCertifications;
    }


}
