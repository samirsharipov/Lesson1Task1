package uz.pdp.lesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1task1.entity.Department;
import uz.pdp.lesson1task1.payload.ApiResponse;
import uz.pdp.lesson1task1.payload.DepartmentDto;
import uz.pdp.lesson1task1.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentService service;

    @GetMapping
    public ResponseEntity<List<Department>> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody DepartmentDto departmentDto){
        return service.add(departmentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id, @Valid @RequestBody DepartmentDto departmentDto){
        return service.edit(id,departmentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
