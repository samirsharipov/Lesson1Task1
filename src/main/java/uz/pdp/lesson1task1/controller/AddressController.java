package uz.pdp.lesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1task1.entity.Address;
import uz.pdp.lesson1task1.payload.AddressDto;
import uz.pdp.lesson1task1.payload.ApiResponse;
import uz.pdp.lesson1task1.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService service;


    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody AddressDto addressDto){
        return service.add(addressDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@Valid @RequestBody AddressDto addressDto, @PathVariable Integer id){
        return service.edit(addressDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        return service.delete(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
