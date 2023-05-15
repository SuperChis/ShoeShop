package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    CustomerEntity findByUserName(String userName);

    CustomerEntity findByUserId(int userId);

    CustomerEntity save(CustomerEntity user);
}
