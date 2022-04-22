package uz.pdp.lesson1task1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1task1.entity.Address;

public interface AddressRepo extends JpaRepository<Address,Integer> {
    boolean existsByHomeNumberAndStreet(String homeNumber, String street);
}
