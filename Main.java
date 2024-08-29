/* Arlo Insigne
   CMSC350
   Project 1
   May 29, 2023
   Description: Write a program that converts expression from prefix to postfix and from
   postfix to prefix.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public static void main(String[] args) {

        //Create frame
        JFrame window = new JFrame("Expression Converter");

        //Create label for "Result"
        JLabel lResult = new JLabel("Result");

        //Create lable for "Enter Expression"
        JLabel lInfix = new JLabel("Enter Expression");

        //Create text field where the result of the converstion will show up
        JTextField tfResult = new JTextField(20);

        lResult.setLabelFor(tfResult);

        //Create text fieled where user will put the expression
        JTextField tfInfix = new JTextField(20);

        lInfix.setLabelFor(tfInfix);

        //Create button for Prefix to Postfix
        JButton button1 = new JButton("Prefix to Postfix");

        //Add action listener that will trigger class method when pressed
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String expression = tfInfix.getText();

                PreToPost c = new PreToPost(expression);

                try {

                    String result = c.PrePostConverter(expression);
                    tfResult.setText(result);

                } catch (Exception g){

                    JOptionPane.showMessageDialog(window, "SyntaxError: empty field or invalid input." );

                }
            }
        });

        //Create button for Postfix to Prefix
        JButton button2 = new JButton("Postfix to Prefix");

        //Add action listener that will trigger class method when pressed
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String expression = tfInfix.getText();

                PostToPre d = new PostToPre(expression);

                try {

                    String result = d.PostToPreConverter(expression);
                    tfResult.setText(result);

                } catch (Exception f) {

                    JOptionPane.showMessageDialog(window, "SyntaxError: empty field or invalid input." );

                }
            }

        });

        //Combine all the swing components
        //Set layout of each components

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel.add(button1, BorderLayout.CENTER);
        panel.add(button2, BorderLayout.CENTER);
        panel2.add(tfInfix, BorderLayout.CENTER);
        panel2.add(lInfix, BorderLayout.WEST);
        panel3.add(tfResult, BorderLayout.CENTER);
        panel3.add(lResult, BorderLayout.WEST);

        window.add(panel, BorderLayout.CENTER);
        window.add(panel2, BorderLayout.NORTH);
        window.add(panel3, BorderLayout.SOUTH);
        window.setSize(400, 150);
        window.setLocation(300, 400);
        window.setVisible(true);
        window.setResizable(false);

    }

}



