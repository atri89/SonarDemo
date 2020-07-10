package com.example.sonardemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SonarDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SonarDemoApplication.class, args);
		System.out.println("Hello World");
	}
	
	public void methodTest(int input1, int input2, int input3, int input4, int input5, int input6) {
		  System.out.println(input1+input2+input3+input4+input5+input6);
	}

	public void methodTest(int input1, int input2, int input3) {
		  System.out.println(input1+input2+input3);
	}

}
