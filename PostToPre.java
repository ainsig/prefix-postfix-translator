/* Arlo Insigne
   CMSC350
   Project 1
   May 29, 2023
   Description: Write a program that converts expression from prefix to postfix and from
   postfix to prefix.
 */

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class PostToPre {

    String expression;

    //Creating stack for operands
    Stack<String> operandStack = new Stack<String>();

    public PostToPre(String expression) {
        this.expression = expression;
    }

    public String PostToPreConverter(String expression) {

        //Use addSpace method to get add spaces even if the user does not include spaces
        //in the original input
        String modified = addSpace(expression);

        //Tokenize the input from the user
        StringTokenizer tokenizer = new StringTokenizer(modified, " ", false);

        //Evaluate each token and place them to the stack
        //Return the final string that contains the converted expression
        while (tokenizer.hasMoreTokens()) {

            String nextToken = tokenizer.nextToken();

            if (nextToken.isEmpty()) {

                //Ignore the token if it is a white space

            } else if (nextToken.matches("\\d+")) {

                operandStack.push(nextToken);

            } else {

                String operand1 = operandStack.pop();

                String operand2 = operandStack.pop();

                String combined = nextToken + " " + operand2 + " " + operand1;

                operandStack.push(combined);
            }
        }

        //Pop the final string conversion
        return operandStack.pop();
    }

    //Method to add space if user does not
    private String addSpace(String inExpress) {

        String finString = "";

        ArrayList<String> strArray = new ArrayList<String>();

        StringTokenizer tokenizer = new StringTokenizer(inExpress, " ", false);

        while (tokenizer.hasMoreTokens()) {

            String token = tokenizer.nextToken();
            strArray.add(token);
        }

        for (int i = 0; i < strArray.size(); i++) {

            String str = strArray.get(i);

            //Use regex to add space if the token is an operator preceded or followed by numbers of any digit
            // and operators
            if (str.matches(".*[-+*^/].*")) {

                str = str.replaceAll("(?<=\\d)([-+*/^])|([-+*/^])(?=[-+*/^\\d])", " $1$2 ");
                strArray.set(i, str);

            //Add space if the token is lower or upper case letter and all the punctuation
            //Used for detecting invalid characters and returning SyntaxError
            } else if (str.matches("[a-zA-Z\\p{Punct}]")) {

                str = str.replaceAll("(?<=\\d)([a-zA-Z\\p{Punct}])|([a-zA-Z\\p{Punct}])(?=[a-zA-Z\\p{Punct}\\d])"
                        , " $1$2 ");
            }
        }

        //Add space to the token that matches the above regex
        for (int i = 0; i < strArray.size(); i++) {
            finString += strArray.get(i) + " ";

        }

        return finString;
    }

}
