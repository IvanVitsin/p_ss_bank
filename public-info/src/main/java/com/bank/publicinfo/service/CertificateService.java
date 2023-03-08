package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDTO;

import java.util.List;

public interface CertificateService {
    CertificateDTO createCertificate(CertificateDTO certificateDTO, Long bankId);
    CertificateDTO updateCertificate(CertificateDTO certificateDTO, Long id);
    void deleteCertificate(Long id);
    List<CertificateDTO> getCertificateInfo();
}
