package Flappy;

import javax.swing.*;

public class Swinger {
    public static void main(String[] args){
        String input;
        int value;
        input = JOptionPane.showInputDialog("enter your sallary: ");
        value = Integer.parseInt(input);
        JOptionPane.showMessageDialog(null,"your 12 month sallary is " + value*12);

        System.exit(0);
    }
}
