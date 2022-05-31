package springmvc1.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import springmvc1.servlet.web.springmvc.v1.SpringMemberFormControllerV1;

@ServletComponentScan //servlet
@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}

/*
	@Bean //@Component 와 동일한 동작
	SpringMemberFormControllerV1 springMemberFormControllerV1() {
		return new SpringMemberFormControllerV1();
	}
*/

}
