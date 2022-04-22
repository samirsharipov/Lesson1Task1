package uz.pdp.lesson1task1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1task1.entity.Address;
import uz.pdp.lesson1task1.entity.Company;
import uz.pdp.lesson1task1.payload.ApiResponse;
import uz.pdp.lesson1task1.payload.CompanyDto;
import uz.pdp.lesson1task1.repository.AddressRepo;
import uz.pdp.lesson1task1.repository.CompanyRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    AddressRepo addressRepo;

    public ResponseEntity<List<Company>> getAll() {
        List<Company> all = companyRepo.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }


    public ResponseEntity<Company> getById(Integer id) {
        Optional<Company> companyRepoById = companyRepo.findById(id);
        if (companyRepoById.isPresent()) {
            Company company = companyRepoById.get();
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        return new ResponseEntity<>(new Company(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> add(CompanyDto companyDto) {
        Company company = new Company();

        boolean exists = companyRepo.existsByCorpNameAndDirectorNameAndAddress_Id(companyDto.getCorpName(), companyDto.getDirectorName(), companyDto.getAddressId());
        if (exists) {
            return new ResponseEntity<>(new ApiResponse("bunday company mavjud!",false),HttpStatus.BAD_REQUEST);
        }
        Optional<Address> optionalAddress = addressRepo.findById(companyDto.getAddressId());
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            company.setAddress(address);
            company.setCorpName(companyDto.getCorpName());
            company.setDirectorName(companyDto.getDirectorName());
            companyRepo.save(company);
            return new ResponseEntity<>(new ApiResponse("saved!",true),HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse("not found address", false),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<ApiResponse> edit(Integer id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            Optional<Address> optionalAddress = addressRepo.findById(companyDto.getAddressId());
            if (optionalAddress.isPresent()) {
                Address address = optionalAddress.get();
                company.setCorpName(companyDto.getCorpName());
                company.setDirectorName(companyDto.getDirectorName());
                company.setAddress(address);
                companyRepo.save(company);
                return  new ResponseEntity<>(new ApiResponse("edited",true),HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse("not found address",false),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse("not found company",false),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<ApiResponse> delete(Integer id) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            Integer id1 = company.getAddress().getId();
            companyRepo.deleteById(id);
            addressRepo.deleteById(id1);
            return new ResponseEntity<>(new ApiResponse("deleted!",true),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("not found company1",false),HttpStatus.BAD_REQUEST);
    }
}
