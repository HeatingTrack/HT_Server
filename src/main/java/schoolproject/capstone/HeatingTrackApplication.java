package schoolproject.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HeatingTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeatingTrackApplication.class, args);
	}

}
