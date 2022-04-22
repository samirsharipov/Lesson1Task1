package uz.pdp.lesson1task1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1task1.entity.Address;
import uz.pdp.lesson1task1.payload.AddressDto;
import uz.pdp.lesson1task1.payload.ApiResponse;
import uz.pdp.lesson1task1.repository.AddressRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepo addressRepo;

    public ResponseEntity<List<Address>> getAll() {
        List<Address> all = addressRepo.findAll();
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<ApiResponse> add(AddressDto addressDto) {
        Address address = new Address();

        boolean existsByHomeNumberAndStreet = addressRepo.existsByHomeNumberAndStreet(addressDto.getHomeNumber(), addressDto.getStreet());

        if (existsByHomeNumberAndStreet) {
            return new ResponseEntity<>(new ApiResponse("bunday address mavjud",false), HttpStatus.BAD_REQUEST);
        }

        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());

        addressRepo.save(address);
        return new ResponseEntity<>(new ApiResponse("saved",true), HttpStatus.OK);
    }


    public ResponseEntity<ApiResponse> edit(AddressDto addressDto, Integer id) {
        Optional<Address> addressRepoById = addressRepo.findById(id);

        if (addressRepoById.isPresent()) {
            Address address = addressRepoById.get();
            address.setStreet(addressDto.getStreet());
            address.setHomeNumber(addressDto.getHomeNumber());
            addressRepo.save(address);
            return new ResponseEntity<>(new ApiResponse("edited",true),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("not found", false),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<ApiResponse> delete(Integer id) {
        Optional<Address> byId = addressRepo.findById(id);
        if (byId.isPresent()) {
            addressRepo.deleteById(id);
            return new ResponseEntity<>(new ApiResponse("deleted!",true),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("not found",false),HttpStatus.BAD_REQUEST);
    }

}
