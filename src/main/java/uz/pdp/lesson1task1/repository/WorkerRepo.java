package uz.pdp.lesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1task1.entity.Worker;

public interface WorkerRepo extends JpaRepository<Worker,Integer> {
    boolean existsByNameAndPhoneNumberAndAddress_IdAndDepartment_Id(String name, String phoneNumber, Integer address_id, Integer department_id);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

    boolean existsByPhoneNumber(String phoneNumber);
}
