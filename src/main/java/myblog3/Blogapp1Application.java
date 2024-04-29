package myblog3;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Blogapp1Application {

	public static void main(String[] args) {
		SpringApplication.run(Blogapp1Application.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){


		return new ModelMapper();
	}


}
