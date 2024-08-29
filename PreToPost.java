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

public class PreToPost {

    String expression;

    //Creating reverse stack
    Stack<String> reverseStack = new Stack<String>();

    //Creating operand stack
    Stack<String> operandStack = new Stack<String>();

    public PreToPost(String expression) {

        this.expression = expression;

    }

    public String PrePostConverter(String expression) {


        //Use addSpace method to get add spaces even if the user does not include spaces
        //in the original input
        String modified = addSpace(expression);

        //Tokenize the input from the user
        StringTokenizer tokenizer = new StringTokenizer(modified, " ", false);

        //Evaluate each token and place them to the stack
        //Return the final string that contains the converted expression
        while (tokenizer.hasMoreTokens()) {

            String t = tokenizer.nextToken();

            reverseStack.push(t);
        }

        //Evaluate each token and place them to the stack
        //Return the final string that contains the converted expression
        while (!reverseStack.isEmpty()) {

            String nextToken = reverseStack.pop();

            if (nextToken.matches("\\d+")) {

                operandStack.push(nextToken);

            } else {

                String combined = operandStack.pop() + " " + operandStack.pop() + " " + nextToken;

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

