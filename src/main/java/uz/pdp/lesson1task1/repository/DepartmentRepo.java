package uz.pdp.lesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1task1.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department,Integer> {
}
