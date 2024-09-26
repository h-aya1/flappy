package entity;
import Flappy.GamePannel;
import Flappy.keyhandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity {
    int animationFrameCounter = 0;
    int MAX_FRAME_COUNT = 5;
    private int msecToClimb = 0;  // Represents milliseconds left to climb
    public static Rectangle flappyHitRectangle;
    GamePannel gp;
    keyhandler KeyH;
    private double gravity = 2;
    public final int screenX ;
    public  int screenY;





    public Player(GamePannel gp, keyhandler KeyH){
this.gp = gp;
this.KeyH = KeyH;

screenX = gp.screenWidth/25;
screenY = gp.screenHieght/5 ;

setDefaultValues();
getPlayerImage();

    }





    public void setDefaultValues(){
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 26;
        speed = 7;
        direction = "down";
        System.out.println(screenX);
        System.out.println(screenY);
    }




    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/flappy bird trial1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/flappy bird flap up1.png")));
            up3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/flappy bird flap down.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/flappy down facing 1.png")));
        }catch (IOException e ){
            System.out.println("Bird image not found !! ");
        }

    }





    public void update() {


        // milliseconds
        int CLIMB_DURATION = 200;
        if (KeyH.upPressed) {
            direction = "up";
            msecToClimb = CLIMB_DURATION;
            gravity = 0;
            spriteCounter++ ;
        } else {

            gravity += 0.3; // Add a constant gravity factor
        }

        if (msecToClimb > 0) {
            double climbTimeFraction = (double) msecToClimb / CLIMB_DURATION;
            double CLIMB_SPEED = 5;
            double smoothAccentSpeed = CLIMB_SPEED * (1 - Math.cos(climbTimeFraction * Math.PI));
            screenY -= (int) smoothAccentSpeed;
            msecToClimb -= (1000 / GamePannel.FPS);
        } else {
            double SINK_SPEED = 0.45;
            screenY += (int) (gravity * SINK_SPEED); // Use gravity as a multiplier for sinking
            direction = "down1";
        }

            animationFrameCounter++; // Increment the animation frame counter

            // Change sprite after a certain number of frames
            if (animationFrameCounter >= MAX_FRAME_COUNT) {

                spriteNum = (spriteNum % 3) + 1; // Cycle through sprites 1, 2, 3
                animationFrameCounter = 0; // Reset the frame counter
            }

    }





public void draw(Graphics2D g2){

    BufferedImage image = null;
    switch (direction){
        case "up":
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            if(spriteNum == 3){
                image = up3;
            }
        break;
        case "down":
            image = up1;
            break;
        case "down1":
            image = down1;
            break;

    }

    if (KeyH.gameState == KeyH.playState) {

    g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);



    }

    if (KeyH.gameState == KeyH.pauseState) {
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

        try{
            g2.setColor(new Color(0, 0, 0,150));
            g2.fillRect(0,0,600,800);
            BufferedImage playButton = ImageIO.read(getClass().getResourceAsStream("/options/play button 2.png"));
            BufferedImage backButton = ImageIO.read(getClass().getResourceAsStream("/options/back button.png"));
            BufferedImage retryButton = ImageIO.read(getClass().getResourceAsStream("/options/retry button 2 transparent.png"));
            BufferedImage buttonTray = ImageIO.read(getClass().getResourceAsStream("/options/eth tray edited.png"));


            g2.drawImage(buttonTray,80,0,440,800,null);
//            g2.drawImage(retryButton,150,700,250,90,null);


//            g2.drawImage(backButton,360,600,150,80,null);
//            g2.drawImage(playButton,100,600,150,80,null);
//            g2.drawImage(retryButton,220,500,150,80,null);




        }catch (IOException ignored){}
    }



    flappyHitRectangle = new Rectangle(screenX+23,screenY+23,gp.tileSize-48,gp.tileSize-48);




                             //Bird Hit Box Visualization Box
//                                 g2.setColor(Color.green);
//                                 g2.drawRect(screenX+25,screenY+25,gp.tileSize-48,gp.tileSize-48);




}



}

