package id.co.mii.overtimeserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import id.co.mii.overtimeserverapp.utils.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class OvertimeServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OvertimeServerAppApplication.class, args);
		
		System.out.println("Overtime Server App is Running...");
	}

}
