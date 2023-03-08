package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.LicenseDTO;

import java.util.List;

public interface LicenseService {
    LicenseDTO createLicense(LicenseDTO licenseDTO, Long bankId);
    LicenseDTO updateLicense(LicenseDTO licenseDTO, Long id);
    void deleteLicense(Long id);
    List<LicenseDTO> getLicenseInfo();
}
