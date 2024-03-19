package com.telusko.company.impl;

import com.telusko.company.Company;
import com.telusko.company.CompanyRepo;
import com.telusko.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepo companyRepo;

    @Autowired
    public CompanyServiceImpl(CompanyRepo companyRepo) {
        this.companyRepo = companyRepo;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }


    @Override
    public boolean updateCompany(int id, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepo.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(updatedCompany.getDescription());
            companyToUpdate.setJobs(updatedCompany.getJobs());
            companyToUpdate.setName(updatedCompany.getName());
            companyRepo.save(companyToUpdate);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void createCompany(Company company) {
        companyRepo.save(company);
    }


    @Override
    public boolean deleteCompany(Integer id) {
        boolean deleted = false;
        Optional<Company> companyOptional = companyRepo.findById(id);
        if (companyOptional.isPresent()) {
            Company companyToDelete = companyOptional.get();
            companyRepo.delete(companyToDelete);
            deleted = true;
        }
        return deleted;
    }


    @Override
    public Company getCompany(Integer id) {
        Optional<Company> companyOptional = companyRepo.findById(id);
        return companyOptional.orElse(null);
    }
}
