package dev.nouhaila.customerservice;

import dev.nouhaila.customerservice.config.GlobalConfig;
import dev.nouhaila.customerservice.entities.Customer;
import dev.nouhaila.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
      return args -> {
          List<Customer> customerList = List.of(
                  Customer.builder()
                          .firstName("Nouhaila")
                          .lastName("Chkhonty")
                          .email("nohaila.chkhonty@gmail.com")
                          .build(),
                  Customer.builder()
                          .firstName("Oussama")
                          .lastName("Chkhonty")
                          .email("oussamachkhonty@gmail.com")
                          .build()
          ) ;
          customerRepository.saveAll(customerList);
      };
    }

}
