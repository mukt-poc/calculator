package com.capita.calculator.engine;

import java.util.Stack;

import com.capita.calculator.exception.InvalidInputException;

public class CalculatorEngine {
	public Integer calculate(String input) throws InvalidInputException {
		Integer output = 0;
		Validator validator = new Validator();
		validator.isValidMathExpression(input);
		Stack<Character> operatorStack = new Stack<Character>();
		Stack<Integer> numberStack = new Stack<Integer>();
		char[] tokens = input.toCharArray();
		boolean error = false;
		// Main loop - process all input tokens
        for (int n = 0; n < tokens.length; n++) {
            char nextToken = tokens[n];
            boolean isOperator = false;
            try {
            	Integer num = Integer.parseInt(Character.toString(nextToken));
            	numberStack.push(num);
            } catch (NumberFormatException e) {
            	isOperator = true;
            }
            if (isOperator) {
                if (operatorStack.isEmpty() || Validator.precendenceMap.get(nextToken) > Validator.precendenceMap.get(operatorStack.lastElement())) {
                    operatorStack.push(nextToken);
                } else {
                    while (!operatorStack.isEmpty() && Validator.precendenceMap.get(nextToken) <= Validator.precendenceMap.get(operatorStack.lastElement())) {
                        Character toProcess = operatorStack.lastElement();
                        operatorStack.pop();
                        processOperator(toProcess, numberStack);
                    }
                    operatorStack.push(nextToken);
                }
            } else if (nextToken == '(') {
                operatorStack.push(nextToken);
            } else if (nextToken == ')') {
                while (!operatorStack.isEmpty() && Validator.OPERATORS.indexOf(operatorStack.lastElement()) > -1) {
                    Character toProcess = operatorStack.lastElement();
                    operatorStack.pop();
                    processOperator(toProcess, numberStack);
                }
                if (!operatorStack.isEmpty() && operatorStack.lastElement() == '(') {
                    operatorStack.pop();
                } else {
                    System.out.println("Error: unbalanced parenthesis.");
                    error = true;
                }
            }

        }
        // Empty out the operator stack at the end of the input
        while (!operatorStack.isEmpty() && Validator.OPERATORS.indexOf(operatorStack.lastElement()) > -1) {
        	processOperator(operatorStack.lastElement(), numberStack);
            operatorStack.pop();
        }
        // Print the result if no error has been seen.
        if(error == false) {
            if (!operatorStack.isEmpty() || !numberStack.isEmpty()) {
                System.out.println("Expression error.");
            } else {
            	output = numberStack.lastElement();
            }
        }

		return output;
	}
	
	private void processOperator(Character t, Stack<Integer> numberStack) throws InvalidInputException {
        Integer A = null, B = null;
        if (numberStack.isEmpty()) {
        	throw new InvalidInputException("Invalid Expression");
        } else {
            B = numberStack.lastElement();
            numberStack.pop();
        }
        if (numberStack.isEmpty()) {
        	throw new InvalidInputException("Invalid Expression");
        } else {
            A = numberStack.lastElement();
            numberStack.pop();
        }
        Integer R = operate(t, A, B);
        numberStack.push(R);
    }
	
	private Integer operate(Character o, Integer a, Integer b) {
		return null;
	}

}
