package com.demo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com.demo.JWT") // Replace with the actual package of CorsConfig
public class ResDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResDemoApplication.class, args);
	}
	
	@Bean
  public WebMvcConfigurer configurer(){
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry reg){
        reg.addMapping("/**").allowedOrigins("*");
      }
    };
  }
}
