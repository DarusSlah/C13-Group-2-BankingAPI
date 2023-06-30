package C13Group2.BankingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class
BankingApiApplication {
	public static Logger logger = Logger.getGlobal();

	public static void main(String[] args) {
		SpringApplication.run(BankingApiApplication.class, args);
	}

}
