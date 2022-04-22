package uz.pdp.lesson1task1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1task1.entity.Company;
import uz.pdp.lesson1task1.entity.Department;
import uz.pdp.lesson1task1.payload.ApiResponse;
import uz.pdp.lesson1task1.payload.DepartmentDto;
import uz.pdp.lesson1task1.repository.CompanyRepo;
import uz.pdp.lesson1task1.repository.DepartmentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    CompanyRepo companyRepo;

    public ResponseEntity<List<Department>> getAll() {
        return new ResponseEntity<>(departmentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if (optionalDepartment.isPresent()) {
            return new ResponseEntity<>(optionalDepartment.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("not found department",false),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> add(DepartmentDto departmentDto) {
        Department department = new Department();
        Optional<Company> optionalCompany = companyRepo.findById(departmentDto.getCompanyId());
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            department.setName(departmentDto.getName());
            department.setCompany(company);
            departmentRepo.save(department);
            return new ResponseEntity<>(new ApiResponse("saved",true),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("Error!",false),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> edit(Integer id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            Optional<Company> optionalCompany = companyRepo.findById(departmentDto.getCompanyId());
            if (optionalCompany.isPresent()) {
                Company company = optionalCompany.get();
                department.setName(departmentDto.getName());
                department.setCompany(company);
                departmentRepo.save(department);
                return new ResponseEntity<>(new ApiResponse("edited",true),HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse("not found company",false),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse("not found department",false),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> delete(Integer id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(id);
        if (optionalDepartment.isPresent()) {
            departmentRepo.deleteById(id);
            return new ResponseEntity<>(new ApiResponse("deleted!", true),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("not found department", false),HttpStatus.BAD_REQUEST);
    }


}
