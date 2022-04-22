package uz.pdp.lesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1task1.entity.Company;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
    boolean existsByCorpNameAndDirectorNameAndAddress_Id(String corpName, String directorName, Integer address_id);
}
