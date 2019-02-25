package com.capita.calculator.engine;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.capita.calculator.exception.InvalidInputException;

public class Validator {
	static final String OPERATORS = "+-*/";
	public static final String INVALID_EXPRESSION = "INVALID EXPRESSION";
	static final Map<Character, Integer> precendenceMap = new HashMap<Character, Integer>();
	static {
		precendenceMap.put(OPERATORS.charAt(0), 1);
		precendenceMap.put(OPERATORS.charAt(1), 1);
		precendenceMap.put(OPERATORS.charAt(2), 2);
		precendenceMap.put(OPERATORS.charAt(3), 2);
	}
	public boolean isValidMathExpression(String input) throws InvalidInputException {
		if(input == null) {
			throw new InvalidInputException("Input expression null data.");
		}
		input = input.replaceAll("\\s", "");
		if(StringUtils.isEmpty(input)) {
			throw new InvalidInputException("Input expression empty expression.");
		}
		validateTrailingChar(input);
		int openParenthCount = 0;
        int closedParenthCount = 0;
        int consecutiveOpCount = 0;
        int numberOfOperator = 0;
        String expression = input;
        for (int i = 0; i < expression.length(); i++){
            if (expression.charAt(i) == '('){
                openParenthCount++;
                consecutiveOpCount = 0;
                if ((OPERATORS.indexOf(expression.charAt(i+1)) > -1) || (expression.charAt(i+1) == ')'))
                    return false; 
            }
            if (expression.charAt(i) == ')'){
                closedParenthCount++;
                consecutiveOpCount = 0;
                if (i < (expression.length()-1) && OPERATORS.indexOf(expression.charAt(i+1)) == -1)
                    return false;

            }
            if (OPERATORS.indexOf(expression.charAt(i)) > -1){
                consecutiveOpCount++;
                numberOfOperator++;
                //TEST 3: False if operator is preceded by opening paranthesis or followed by closing paranthesis
                if (expression.charAt(i-1) == '(' || expression.charAt(i+1) == ')')
                    return false; 
            }
            //TEST 4: False if 2 operators found next to each other
            if (OPERATORS.indexOf(expression.charAt(i)) > -1 || consecutiveOpCount > 0)
                return false; 
        }
        if (openParenthCount != closedParenthCount)
            return false;
        if(numberOfOperator >= (expression.length()/2)) 
        	return false;
        return true;
	}
	private void validateTrailingChar(String input) throws InvalidInputException {
		char firstChar = input.charAt(0);
		char lastChar = input.charAt(input.length()-1);
		if(firstChar == ')' || OPERATORS.indexOf(firstChar) > -1  || lastChar == '(' || OPERATORS.indexOf(lastChar) > -1 ) {
			throw new InvalidInputException("Input expression, Expression can not start with ')-+/*' and end with '(-+/*'.");
		}
	}
}
