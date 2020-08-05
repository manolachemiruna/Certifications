package team7.Certifications.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.Certifications.dto.CertificationDto;
import team7.Certifications.service.CertificationService;

import java.util.List;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @PostMapping(value="/admin/addCertification",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity <CertificationDto> createCertification( @RequestBody CertificationDto certificationDto)
    {
        CertificationDto newCertificationDto=certificationService.addCertification(certificationDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(newCertificationDto);
    }

    @DeleteMapping(value="/admin/deleteCertification/{id}",produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<String> deleteCertification(@PathVariable("id") Integer id)
    {
        certificationService.deleteCertification(id);
        return ResponseEntity.ok().body("Certification with id:"+id+" was deleted successfully!");
    }

    @GetMapping(value="/allCertifications",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificationDto>> getAllCertifications()
    {
        List<CertificationDto> certificationsDto =this.certificationService.getAllCertifications();
        return ResponseEntity.ok().body(certificationsDto);
    }

    @PutMapping(value = "/admin/editCertification/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CertificationDto> editCertification(@PathVariable("id") Integer id,@RequestBody CertificationDto certificationDto)
    {
        CertificationDto newCertificationDto=certificationService.updateCertification(id,certificationDto);
        return ResponseEntity.ok().body(newCertificationDto);
    }


}

