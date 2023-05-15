package server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.entity.CustomerEntity;
import server.service.CustomerService;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> requestBody) {

        String userName = (String) requestBody.get("userName");
        String password = (String) requestBody.get("password");

        CustomerEntity storedUser = customerService.getCustomer(userName);

        if (storedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
        if (!storedUser.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
        return ResponseEntity.ok().body(storedUser);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CustomerEntity user) {

        CustomerEntity storedUser = customerService.getCustomer(user.getUserName());

        if (storedUser != null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User already exist.");
        } else {
            customerService.createCustomer(user);
            return ResponseEntity.ok().body(user);
        }
    }
}
