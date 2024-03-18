package com.telusko.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable int id, @RequestBody Company company){
        if (companyService.updateCompany(id,company)){
            return new ResponseEntity<>("Company Updated Successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Company Not Updated Successfully",HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
       companyService.createCompany(company);
       return new ResponseEntity<>("Company Created Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Integer id){
        if(companyService.deleteCompany(id)){
            return new ResponseEntity<>("company deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("oops! company not deleted",HttpStatus.OK);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id){
        Company company=companyService.getCompany(id);
        if (company!=null){
            return new ResponseEntity<>(company,HttpStatus.OK);
    }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
