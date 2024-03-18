package com.telusko.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
boolean updateCompany(int id, Company updatedCompany);

void createCompany(Company company);
boolean deleteCompany(Integer id);
Company getCompany(Integer id);
}
