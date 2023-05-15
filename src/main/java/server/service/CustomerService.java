package server.service;

import org.springframework.stereotype.Service;
import server.entity.CustomerEntity;

import java.util.List;

@Service
public interface CustomerService {
    List<CustomerEntity> getAllCustomer();

    CustomerEntity getCustomer(String userName);

    CustomerEntity getCustomerById(int userId);

    CustomerEntity createCustomer(CustomerEntity user);
}
