package team7.Certifications.service;


import org.springframework.stereotype.Service;
import team7.Certifications.dto.CertificationDto;
import team7.Certifications.entity.Certification;
import team7.Certifications.entity.Request;
import team7.Certifications.mapper.CertificationMapper;
import team7.Certifications.repository.CertificationRepository;
import team7.Certifications.repository.RequestRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final CertificationMapper certificationMapper;
    private final RequestRepository requestRepository;

    public CertificationService(CertificationRepository certificationRepository,CertificationMapper certificationMapper,RequestRepository requestRepository) {
        this.certificationRepository = certificationRepository;
        this.certificationMapper=certificationMapper;
        this.requestRepository=requestRepository;
    }

    public List<CertificationDto> getAllCertifications()
    {
        List<Certification> certifications= certificationRepository.findAll();
        List<CertificationDto> dtoCertifications=certificationMapper.toDtoList(certifications);
        return  dtoCertifications;
    }

    public CertificationDto addCertification(CertificationDto certificationDto)
    {
        Certification certification=certificationMapper.toEntity(certificationDto);
        Certification savedCertification=certificationRepository.save(certification);
        CertificationDto dtoCertification=certificationMapper.toDto(savedCertification);

        return  dtoCertification;
    }

    public CertificationDto updateCertification(Integer id,CertificationDto certificationDto)
    {
        Optional<Certification> existingCertification=certificationRepository.findById(id);
        existingCertification.orElseThrow(()->new IllegalArgumentException("there is no such certification"));

        Certification certification=certificationMapper.toEntity(certificationDto);
        certificationRepository.save(certification);
        certificationRepository.deleteById(id);
        CertificationDto updatedCertificationDto=certificationMapper.toDto(certification);

        return updatedCertificationDto;
    }
    public void deleteCertification (int id)
    {
        Optional<Certification> existingCertification=certificationRepository.findById(id);
        existingCertification.orElseThrow(()->new IllegalArgumentException("there is no such certification"));
        List<Request> requests=this.requestRepository.findByCertificationId(id);
        Iterator<Request> iterator=requests.iterator();
        while (iterator.hasNext())
        {
            int requestId=iterator.next().getId();
            requestRepository.deleteById(requestId);
        }
        this.certificationRepository.deleteById(id);
    }


}
