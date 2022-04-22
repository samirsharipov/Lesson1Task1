package uz.pdp.lesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1task1.entity.Company;
import uz.pdp.lesson1task1.payload.ApiResponse;
import uz.pdp.lesson1task1.payload.CompanyDto;
import uz.pdp.lesson1task1.service.CompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService service;

    @GetMapping
    public ResponseEntity<List<Company>> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody CompanyDto companyDto){
        return service.add(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id, @Valid @RequestBody CompanyDto companyDto){
        return service.edit(id,companyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
