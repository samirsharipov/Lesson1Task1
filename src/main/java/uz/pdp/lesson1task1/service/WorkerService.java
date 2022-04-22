package uz.pdp.lesson1task1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1task1.entity.Address;
import uz.pdp.lesson1task1.entity.Department;
import uz.pdp.lesson1task1.entity.Worker;
import uz.pdp.lesson1task1.payload.ApiResponse;
import uz.pdp.lesson1task1.payload.WorkerDto;
import uz.pdp.lesson1task1.repository.AddressRepo;
import uz.pdp.lesson1task1.repository.DepartmentRepo;
import uz.pdp.lesson1task1.repository.WorkerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepo workerRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    public ResponseEntity<List<Worker>> getAll() {
        List<Worker> all = workerRepo.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Integer id) {
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        if (optionalWorker.isPresent()) {
            return new ResponseEntity<>(optionalWorker.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("not found worker", false), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> add(WorkerDto workerDto) {
        Worker worker = new Worker();
        boolean numberAndIdNot = workerRepo.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (numberAndIdNot) {
            return new ResponseEntity<>(new ApiResponse("bunday phone number mavjud!",false),HttpStatus.CONFLICT);
        }
        boolean exists = workerRepo.existsByNameAndPhoneNumberAndAddress_IdAndDepartment_Id(workerDto.getName(), worker.getPhoneNumber(), workerDto.getAddressId(), workerDto.getDepartmentId());
        if (exists)
            return new ResponseEntity<>(new ApiResponse("bunday worker mavjud", false), HttpStatus.BAD_REQUEST);

        Optional<Address> optionalAddress = addressRepo.findById(workerDto.getAddressId());
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            Optional<Department> optionalDepartment = departmentRepo.findById(workerDto.getDepartmentId());
            if (optionalDepartment.isPresent()) {
                Department department = optionalDepartment.get();
                worker.setName(workerDto.getName());
                worker.setPhoneNumber(workerDto.getPhoneNumber());
                worker.setAddress(address);
                worker.setDepartment(department);
                workerRepo.save(worker);
                return new ResponseEntity<>(new ApiResponse("saved", true), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse("not found department", false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse("not found address",false),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ApiResponse> edit(Integer id, WorkerDto workerDto) {
        boolean numberAndIdNot = workerRepo.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (numberAndIdNot) {
             return new ResponseEntity<>(new ApiResponse("bunday phone number mavjud!",false),HttpStatus.CONFLICT);
        }
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        if (optionalWorker.isPresent()) {
            Worker worker = optionalWorker.get();
            Optional<Address> optionalAddress = addressRepo.findById(workerDto.getAddressId());
            if (optionalAddress.isPresent()) {
                Address address = optionalAddress.get();
                Optional<Department> optionalDepartment = departmentRepo.findById(workerDto.getDepartmentId());
                if (optionalDepartment.isPresent()) {
                    Department department = optionalDepartment.get();
                    worker.setName(workerDto.getName());
                    worker.setPhoneNumber(workerDto.getPhoneNumber());
                    worker.setAddress(address);
                    worker.setDepartment(department);
                    workerRepo.save(worker);
                    return new ResponseEntity<>(new ApiResponse("edited", true), HttpStatus.OK);
                }
                return new ResponseEntity<>(new ApiResponse("not found department", false), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(new ApiResponse("not found address",false),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(new ApiResponse("not found worker",false),HttpStatus.CONFLICT);
    }

    public ResponseEntity<ApiResponse> delete(Integer id) {
        Optional<Worker> optionalWorker = workerRepo.findById(id);
        if (optionalWorker.isPresent()) {
            workerRepo.deleteById(id);
            return new ResponseEntity<>(new ApiResponse("deleted", true), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("not found worker",false),HttpStatus.CONFLICT);
    }
}
