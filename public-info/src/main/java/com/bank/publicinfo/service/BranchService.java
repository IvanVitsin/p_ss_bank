package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    BranchDTO createBranch(BranchDTO branchDTO);
    BranchDTO updateBranch(BranchDTO branchDTO, Long id);
    void deleteBranch(Long id);
    List<BranchDTO> getBranch();
}
