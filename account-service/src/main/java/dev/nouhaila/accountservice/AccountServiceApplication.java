package dev.nouhaila.accountservice;

import dev.nouhaila.accountservice.clients.CustomerRestClient;
import dev.nouhaila.accountservice.entities.BankAccount;
import dev.nouhaila.accountservice.enums.AccountType;
import dev.nouhaila.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository accountRepository, CustomerRestClient customerRestClient){
		return args -> {
			customerRestClient.allCustomers().forEach(c -> {
				BankAccount bankAccount1 = BankAccount.builder()
						.accountId(UUID.randomUUID().toString())
						.currency("MAD")
						.balance(Math.random()*800000)
						.createAt(LocalDate.now())
						.type(AccountType.CURRENT_ACCOUNT)
						.customerId(c.getId())
						.build();
				BankAccount bankAccount2 = BankAccount.builder()
						.accountId(UUID.randomUUID().toString())
						.currency("MAD")
						.balance(Math.random()*65432)
						.createAt(LocalDate.now())
						.type(AccountType.SAVING_ACCOUNT)
						.customerId(c.getId())
						.build();
				accountRepository.save(bankAccount1);
				accountRepository.save(bankAccount2);
			});
		};
	}

}
