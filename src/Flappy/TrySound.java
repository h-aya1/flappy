package Flappy;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.util.Scanner;

public class TrySound {
    public static void main(String[] args) throws UnsupportedAudioFileException, java.io.IOException,
            javax.sound.sampled.LineUnavailableException {
        File file = new File("Me.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        String response="";
        Scanner keyboard = new Scanner(System.in);

        while(!response.equalsIgnoreCase("Q")){
            System.out.println("P = PLAY, S = STOP , R = RESET, Q = QUIT");
            System.out.println("enter your choice: ");
            response = keyboard.next();

            switch (response){
                case("P"):
                    clip.start();
                    break;
                case ("R") :
                    clip.setMicrosecondPosition(0);
                    break;
                case ("S"):
                    clip.stop();
                    break;
                case ("Q"):
                    clip.close();
                    break;
                default:
                    System.out.println("you entered unkown thing;");
                    System.out.println("try to enter  valid one: ");
                    response = keyboard.next();
                    break;
            }
        }
        System.out.println("have fun;!!");
    }
}
