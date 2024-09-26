package tile;


import Flappy.GamePannel;
import Flappy.keyhandler;
import entity.Player;


import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class TileManager {





    keyhandler KeyH ;
    public ArrayList<Integer> pipeHeight = new ArrayList<>();
    public ArrayList<Integer> pipeXPositions = new ArrayList<>();
    public ArrayList<Integer> groundXPositions = new ArrayList<>();
    public static int PipeGap = 30;
    public static int maximumPipeNum = 1000;
    GamePannel gp;
    public Tile [ ] tile;

    private  boolean[] scoreIncremented = new boolean[maximumPipeNum];



    public TileManager (GamePannel gp,keyhandler KeyH) {
        this.gp = gp;
        this.KeyH = KeyH;
        tile = new Tile[10];
        getTileImage();
    }
        public void getTileImage(){
            try {
                tile[0] = new Tile();
                tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flappy background3.png"));

                tile[1] = new Tile();
                tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flappy tube body eth.png"));

                tile[2] = new Tile();
                tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flappy tube head bottom eth.png"));


                tile[3] = new Tile();
                tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flappy ground eth.png"));

                tile[4] = new Tile();
                tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/flappy tube head top eth.png"));

                tile[5] = new Tile();
                tile[5].image = ImageIO.read(getClass().getResourceAsStream("/options/pause button.png"));


            }catch ( IOException e){
                System.out.println("Game Not Loaded successfully! Please try opening the game Again ! ");
            }

        }
    public void updatePipes() {


        // Ensure the array is initialized and set initial values
        if (pipeXPositions.isEmpty()) {
            for (int i = 0; i < maximumPipeNum; i++) {
                pipeXPositions.add(600 + (gp.tileSize * 2 * i)); // Set initial X positions
            }
        }
        if (groundXPositions.isEmpty()) {
            for (int i = 0; i < maximumPipeNum; i++) {
                groundXPositions.add(600 + (150  * i)); // Set initial X positions
            }
        }




            if (KeyH.gameState == KeyH.playState) {
                // Update X positions
                for (int i = 0; i < maximumPipeNum; i++) {
                    int newX = pipeXPositions.get(i) - 4; // Decrease X position by 2 (adjust the value for the speed)
                    pipeXPositions.set(i, newX);
                }
                for (int i = 0; i < maximumPipeNum; i++) {
                    int newX = groundXPositions.get(i) - 2; // Decrease X position by 2 (adjust the value for the speed)
                    groundXPositions.set(i, newX);
                }
            }
            // The rest of your method...



    }

        public void generatePipeOnce(){
            if (pipeHeight.isEmpty()) {
                Random randomizer = new Random();


                for (int i = 0; i<maximumPipeNum ; i++) {


                pipeHeight.add(randomizer.nextInt(450));




                     }



            }

        }
    public void draw(Graphics2D g2) {
        generatePipeOnce();

        if (KeyH.gameState == KeyH.playState) {

        updatePipes();

        }



        g2.drawImage(tile[0].image, 0, 0, 600, 800, null);

        for (int i = 0; i < maximumPipeNum; i++) {
            if (i < gp.maxScreencol) {
                int xPosition = pipeXPositions.get(i) + gp.tileSize * i;
                int groundXpos = groundXPositions.get(i) -600 ;


                // Draw top pipe virtual hitboxes (red)

//                       g2.setColor(Color.RED);
//                       g2.drawRect(xPosition + 4 , 0, gp.tileSize-10 , pipeHeight.get(i) );
//                       g2.setColor(Color.ORANGE);
//                       g2.drawRect(xPosition, pipeHeight.get(i) , gp.tileSize , gp.tileSize  );
//
              //Bottom pipe virtual hitboxes (blue)
//                       g2.setColor(Color.BLUE);
//                       g2.drawRect(xPosition , pipeHeight.get(i) + (TileManager.PipeGap * 10), gp.tileSize  , gp.tileSize );
//                       g2.setColor(Color.black);
//                       g2.drawRect(xPosition +6 , pipeHeight.get(i) + (int)(TileManager.PipeGap * 6.8) + gp.tileSize , gp.tileSize -11 , pipeHeight.get(i) + 500 );
                g2.setColor(Color.ORANGE);
                g2.drawRect(xPosition+105,pipeHeight.get(i)+gp.tileSize,10,PipeGap*7);

                //draw pause button
                g2.drawImage(tile[5].image,515 , 35, 70, 70, null);

                              // Draw pipes
                g2.drawImage(tile[1].image, xPosition , -400, gp.tileSize, pipeHeight.get(i)+400, null);
                g2.drawImage(tile[4].image, xPosition-8, pipeHeight.get(i) , gp.tileSize +18, gp.tileSize, null);
                g2.drawImage(tile[2].image, xPosition-8, pipeHeight.get(i) + (PipeGap * 10), gp.tileSize+18, gp.tileSize, null);
                g2.drawImage(tile[1].image, xPosition, pipeHeight.get(i) + (PipeGap * 10) + gp.tileSize, gp.tileSize, pipeHeight.get(i) + 500, null);
                g2.drawImage(tile[3].image,groundXpos , 727, 300, 73, null);


            }
        }


    }


    public boolean checkCollision() {
try{
    for (int i = 0; i < maximumPipeNum; i++) {
        if (i < gp.maxScreencol && KeyH.gameState == KeyH.playState) {
            int xPosition = pipeXPositions.get(i) + gp.tileSize * i;
            int groundXpos = groundXPositions.get(i) ;

            Rectangle pipeBodyTopHitBox = new Rectangle(xPosition + 4 , -400, gp.tileSize-10 , pipeHeight.get(i)+400);
            Rectangle pipeHeadTopHitBox = new Rectangle(xPosition-8, pipeHeight.get(i) , gp.tileSize +18 , gp.tileSize  );
            Rectangle pipeHeadBottomHitBox = new Rectangle(xPosition-8 , pipeHeight.get(i) + (PipeGap * 10), gp.tileSize +18  , gp.tileSize );
            Rectangle pipeBodyBottomHitBox = new Rectangle(xPosition +6 , pipeHeight.get(i) + (int)(PipeGap * 6.8) + gp.tileSize , gp.tileSize -11 , pipeHeight.get(i) + 500 );
            Rectangle groundHitBox = new Rectangle(groundXpos-600 , 720, 300, 300);
            Rectangle skyHitBox = new Rectangle(groundXpos -600,-200,300,200);

            if (Player.flappyHitRectangle.intersects(pipeBodyTopHitBox) || Player.flappyHitRectangle.intersects(pipeHeadTopHitBox) ||
                    Player.flappyHitRectangle.intersects(pipeBodyBottomHitBox) || Player.flappyHitRectangle.intersects(pipeHeadBottomHitBox) || Player.flappyHitRectangle.intersects(groundHitBox) || Player.flappyHitRectangle.intersects(skyHitBox)) {
                return true; // Collision detected with any part of the pipes
            }
        }
    }



}catch (NullPointerException e){

}
        return false; // No collision detected with any pipe
    }





    public boolean scoreCounter() {
        try {
            for (int i = 0; i < maximumPipeNum; i++) {
                if (i < gp.maxScreencol && !scoreIncremented[i]) {
                    int xPosition = pipeXPositions.get(i) + gp.tileSize * i;
                    Rectangle scoreCounterBox = new Rectangle(xPosition + 90, pipeHeight.get(i) + gp.tileSize, 10, PipeGap * 7);

                    if (Player.flappyHitRectangle.intersects(scoreCounterBox)) {
                        gp.score++;  // Increment the score from GamePannel
                        scoreIncremented[i] = true;  // Mark that the score has been incremented for this rectangle
                    }
                }
            }
        } catch (Exception ignored) {
        }

        return true;  // Return true since you want the method to stop checking intersections once score is incremented
    }






}































