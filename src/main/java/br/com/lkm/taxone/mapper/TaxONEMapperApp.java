package br.com.lkm.taxone.mapper;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TaxONEMapperApp {

	public static void main(String... args) {
		System.out.println(System.getProperties());
		System.out.println("currentDir:" + new File(".").getAbsolutePath());
		SpringApplication.run(TaxONEMapperApp.class);
	}
	
}
