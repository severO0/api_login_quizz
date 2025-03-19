package testedeapi.com.testeapi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "testedeapi.com")
@EnableJpaRepositories(basePackages = "testedeapi.com.repository")
@EntityScan(basePackages = "testedeapi.com.models")
public class TesteapiApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TesteapiApplication.class, args);
	} 

}
 