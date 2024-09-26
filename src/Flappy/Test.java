package Flappy;

import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String [] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Flappy");
        GamePannel GamePannel = new GamePannel();
        window.add(GamePannel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        GamePannel.startGametrd();


    }
}
