package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDTO;

import java.util.List;


public interface AtmService {
    AtmDTO createAtm(AtmDTO atmDTO, Long branchId);
    AtmDTO updateAtm(AtmDTO atmDTO, Long id);
    void deleteAtm(Long id);
    List<AtmDTO> getAllAtmInfo();

}
