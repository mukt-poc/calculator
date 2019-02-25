package com.capita.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capita.calculator.engine.CalculatorEngine;
import com.capita.calculator.engine.Validator;
import com.capita.calculator.exception.InvalidInputException;
import com.capita.calculator.exception.InvalidTestCaseException;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) throws InvalidTestCaseException {
		try (Scanner scanner = new Scanner(System.in)) {

			// prompt for the user's name
			System.out.print("Number of Test Cases: ");

			// get their input as a String
			String tcCountString = scanner.next();

			System.out.println(tcCountString);
			int testCaseCount = Integer.parseInt(tcCountString);
			if (testCaseCount < 1 || testCaseCount > 100) {
				throw new InvalidTestCaseException("Please enter valid number of test cases");
			} else {
				List<String> testCases = new ArrayList<>();
				for (int i = 0; i < testCaseCount; i++) {
					testCases.add(scanner.next());
				}
				CalculatorEngine ce = new CalculatorEngine();
				for (int i = 0; i < testCases.size(); i++) {
					String input = testCases.get(i);
					try {
						System.out.print("Case #"+i+":"+ce.calculate(input).toString());
					} catch (InvalidInputException e) {
						System.out.print("Case #"+i+":"+Validator.INVALID_EXPRESSION);
					}
				}
			}
			
		} finally {
		}
		SpringApplication.run(CalculatorApplication.class, args);
	}

}
