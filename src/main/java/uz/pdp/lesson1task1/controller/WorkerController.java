package uz.pdp.lesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1task1.entity.Worker;
import uz.pdp.lesson1task1.payload.ApiResponse;
import uz.pdp.lesson1task1.payload.WorkerDto;
import uz.pdp.lesson1task1.service.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {
    @Autowired
    WorkerService service;

    @GetMapping
    public ResponseEntity<List<Worker>> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody WorkerDto workerDto){
        return service.add(workerDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@PathVariable Integer id, @Valid @RequestBody WorkerDto workerDto){
        return service.edit(id,workerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
