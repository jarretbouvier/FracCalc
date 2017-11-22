package fracCalc;

import java.io.Console;
import java.util.Scanner;

public class FracCalc {

	public static String fractionOne;
	public static String operator;
	public static String fractionTwo;
	public static int wholeNumberOne;
	public static int wholeNumberTwo;
	public static int numeratorOne;
	public static int numeratorTwo;
	public static int denominatorOne;
	public static int denominatorTwo;

	public static void main(String[] args) 
	{
		System.out.println("Welcome to the Fraction calculator!");
		System.out.print("Enter an expression (or \"quit\"): ");
		Scanner console = new Scanner(System.in);
		String value = "";
		
		while (true)
		{
			value = console.next();
			
			if (value.equalsIgnoreCase("quit"))
			{
				break; //exit while loop if we get quit
			}
			
			fractionOne = value;
			operator = console.next();
			fractionTwo = console.next();
				
			System.out.println(processFractions(fractionOne, operator, fractionTwo));
			
			System.out.println("Enter an expression (or \"quit\"): ");
			
		}
		
		System.out.println("Goodbye!");
		console.close(); //close console session

	}

	public static String produceAnswer(String input) {

		String array[] = input.split(" ");
		
		fractionOne = array[0];
		operator = array[1];
		fractionTwo = array[2];
				
		return processFractions(fractionOne, operator, fractionTwo);
		
	}
	
	public static String processFractions(String fractionOne, String operator, String fractionTwo) {
		// get int variables from fractions
		// testing fraction 1 to get int values
		if (fractionOne.contains("_")) { // testing for mixed number
			wholeNumberOne = Integer.parseInt(fractionOne.substring(0, fractionOne.indexOf("_")));
			numeratorOne = Integer.parseInt(fractionOne.substring(fractionOne.indexOf("_") + 1, fractionOne.indexOf("/")));
			denominatorOne = Integer.parseInt(fractionOne.substring(fractionOne.indexOf("/") + 1));
			if (fractionOne.contains("-")) {
				numeratorOne = wholeNumberOne * denominatorOne - numeratorOne;
			} else {
				numeratorOne = wholeNumberOne * denominatorOne + numeratorOne;
			}
		} else if (fractionOne.contains("/")) { // testing for fraction
			numeratorOne = Integer.parseInt(fractionOne.substring(0, fractionOne.indexOf("/")));
			denominatorOne = Integer.parseInt(fractionOne.substring(fractionOne.indexOf("/") + 1));
		} else {// testing for whole number
			wholeNumberOne = Integer.parseInt(fractionOne.substring(0));
			numeratorOne = wholeNumberOne;
			denominatorOne = 1;
		} // end if, else if, else method

		// testing fraction 2 to get int values
		if (fractionTwo.contains("_")) { // mixed fraction
			wholeNumberTwo = Integer.parseInt(fractionTwo.substring(0, fractionTwo.indexOf("_")));
			numeratorTwo = Integer.parseInt(fractionTwo.substring(fractionTwo.indexOf("_") + 1, fractionTwo.indexOf("/")));
			denominatorTwo = Integer.parseInt(fractionTwo.substring(fractionTwo.indexOf("/") + 1));
			if (fractionTwo.contains("-")) {
				numeratorTwo = wholeNumberTwo * denominatorTwo - numeratorTwo;
			} else {
				numeratorTwo = wholeNumberTwo * denominatorTwo + numeratorTwo;
			}
		} else if (fractionTwo.contains("/")) { // fraction
			numeratorTwo = Integer.parseInt(fractionTwo.substring(0, fractionTwo.indexOf("/")));
			denominatorTwo = Integer.parseInt(fractionTwo.substring(fractionTwo.indexOf("/") + 1));
		} else { // whole number
			wholeNumberTwo = Integer.parseInt(fractionTwo.substring(0));
			numeratorTwo = wholeNumberTwo;
			denominatorTwo = 1;
		} // end if, else if, else method

		String answer = dotheMath(numeratorOne, numeratorTwo, denominatorOne, denominatorTwo, operator);
		return answer;

	}// end processFraction method

	// dotheMath determines the operator
	public static String dotheMath(int numeratorOne, int numeratorTwo, int denominatorOne, int denominatorTwo, String operator) {

		String answer = "";

		if (operator.equals("+")) {
			answer = add(numeratorOne, numeratorTwo, denominatorOne, denominatorTwo);
		} else if (operator.equals("-")) {
			numeratorTwo = -1 * numeratorTwo;
			answer = add(numeratorOne, numeratorTwo, denominatorOne, denominatorTwo);
		} else if (operator.equals("*")) {
			answer = multiply(numeratorOne, numeratorTwo, denominatorOne, denominatorTwo);
		} else {
			int x = numeratorTwo;
			int y = denominatorTwo;
			denominatorTwo = x;
			numeratorTwo = y;
			answer = multiply(numeratorOne, numeratorTwo, denominatorOne, denominatorTwo);
		} // end the if, else if, else statement

		return answer;
	}// end dotheMath method

	public static String Reduce(int newn, int newd) {
		String number = "";
		String fraction = "";
		// if newd == 1 then it is a whole number and just return
		if (newd == 1) {
			return Integer.toString(newn);
		}
		else if (newn == 0) // it's zero
		{
			return Integer.toString(0);
		}

		int gcd = gcd(newn, newd);
		if (gcd != 0) {
			newn = newn / gcd;
			newd = newd / gcd;
			
			if (newd == 1) //then it is a whole number
			{
				return Integer.toString(newn);
			}
			else if (Math.abs(newn) > newd) //Math.abs returns the absolute value, if newn is bigger than newd and gcd is no 0 it can be reduced
			{
				number = Integer.toString(newn / newd);
				if (newd < 0) //remove the negative since it is reflected on number 
				{
					newd = newd * -1;
				}
				else if (newn < 0) //remove the negative since it is reflected on number
				{
					newn = newn * -1;
				}
				fraction = Integer.toString(newn % newd) + "/" + Integer.toString(newd);
			}
			else if (newn == newd) //then it is 1
			{
				number = Integer.toString(1);
				return number;
			}
			else if (newd == 1) //then just need newn
			{
				return Integer.toString(newn);
				
			}
			else
			{
				fraction = Integer.toString(newn) + "/" + Integer.toString(newd);
			}
			
			if (number == "")
			{
				return fraction;
			}
			else
			{
				return number + "_" + fraction;
			}
			 
		}
		return "Something bad happened in Reduce";
	}

	/*
	public static int gcd(int newn, int newd) {
		int s;
		if (newn > newd)
			s = newd;
		else
			s = newn;
		for (int i = s; i > 0; i--) {
			if ((newn % i == 0) && (newd % i == 0))
				return i;
		}
		return -1;
	}
   */

    public static int gcd(int newn, int newd) {
        while (true) {
            if (newd == 0) {
                return newn;
            } else {
                int temp = newn % newd;
                newn = newd;
                newd = temp;
            }
        }
    }
 
	
	public static String add(int numeratorOne, int numeratorTwo, int denominatorOne, int denominatorTwo) {
		int newn = (numeratorOne * denominatorTwo) + (numeratorTwo * denominatorOne);
		int newd = denominatorOne * denominatorTwo;
		
		boolean isNegative = false;
		String value = "";
		
		//Removing negative will add back
		if (newn < 0) 
		{
			isNegative = true;
			newn = newn * -1;
		}	
		
		value = Reduce(newn, newd);
		
		if (isNegative)
		{
			return "-" + value;
		}
			
		return value;
	}// end add method

	public static String multiply(int numeratorOne, int numeratorTwo, int denominatorOne, int denominatorTwo) {
		int newn = numeratorOne * numeratorTwo;
		int newd = denominatorOne * denominatorTwo;

		return Reduce(newn, newd);
	}// end multiply method
}// end of class
