package AirlineApp.data.repositories;

import AirlineApp.data.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> readByCompanyName(String company);

//    Optional<Company> findFirst();
}
