package Flappy;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyhandler implements KeyListener {


    public boolean upPressed, downPressed,leftPressed, rightPressed,escapePressed;
    public int gameState  ;
    public final int pauseState = 1;

    public int playState ;




    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;

        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;

        }
        if (code == KeyEvent.VK_SPACE) {

            if (gameState == playState) {

                gameState = pauseState;

            }
            else if (gameState == pauseState) {
                gameState = playState;

            }

        }

    }
        public void keyReleased (KeyEvent e) {
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_W) {
                upPressed = false;
            }

            if (code == KeyEvent.VK_A) {
                leftPressed = false;

            }
            if (code == KeyEvent.VK_S) {
                downPressed = false;
            }

            if (code == KeyEvent.VK_D) {
                rightPressed = false;

            }
        }
    }
