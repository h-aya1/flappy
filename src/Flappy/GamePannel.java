    package Flappy;
    import entity.Entity;
    import entity.Player;
    import tile.Tile;
    import tile.TileManager;
    import javax.imageio.ImageIO;
    import javax.swing.*;
    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.IOException;
    import java.util.Objects;


    public class GamePannel extends JPanel implements Runnable {

        Graphics2D g2;

        public Tile [] numbersTile ;

        public static boolean isFrozen = false;
        public int score;
        boolean running = true;
        private Image backgroundImage;
        final int orginalTileSize = 32;
        final int scale = 3;
        public final int tileSize = orginalTileSize * scale;
        public final int maxScreencol = 40;
        public final int maxScreenrow = 15;
        public final int screenWidth = tileSize * maxScreencol;
        public final int screenHieght = tileSize * maxScreenrow;
                     // FPS
        public static final int FPS = 60;
        TileManager tileM ;
        public keyhandler KeyH = new keyhandler();
        Thread gamethread;
        Player player = new Player(this,KeyH);
        CollisionDetector collisionDetector = new CollisionDetector(this);

        public GamePannel() {
            this.setPreferredSize(new Dimension(screenWidth, screenHieght));
            this.setBackground(Color.gray);
            this.setDoubleBuffered(true);
            this.addKeyListener(KeyH);
            this.setFocusable(true);
            this.numbersTile = new Tile[10];
            getNumImage();
            setPreferredSize(new Dimension(600, 800)); // Set preferred size of the panel
            tileM = new TileManager(this,KeyH);
            collisionDetector.player = player;

        }



        public void startGametrd() {
            gamethread = new Thread(this);
            gamethread.start();

        }


        @Override
        public void run(){
            double drawInterval = 1000000000/FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;

            while (running) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime)/drawInterval;
                lastTime = currentTime;
                if(delta>=1) {
                    update();
                    repaint();
                    delta --;
                }



                }


            }




        public void update() {
            if (KeyH.gameState == KeyH.playState) {
            player.update();



            }

            if (KeyH.gameState == KeyH.pauseState) {

            }

//            boolean collisionDetected = tileM.checkCollision();
//            if (collisionDetected) {
//                // Trigger game over or appropriate actions
//                System.out.println("Game Over");
//                gameOver();
//            }


            tileM.scoreCounter();



        }
        public void gameOver(){
            isFrozen = true;
            running = false;




        }


        public void paintComponent(Graphics g) {



            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            tileM.draw(g2);
            player.draw(g2);
            drawScore(g2);
            gameOverScreen(g);



        }

        public void gameOverScreen(Graphics g) {
            if (isFrozen) {
                Graphics2D g2 = (Graphics2D) g;
                try {
                    BufferedImage gameOverImage = ImageIO.read(getClass().getResourceAsStream("/tiles/flappy game over.png"));
                    if (gameOverImage != null) {
                        g2.drawImage(gameOverImage, 150, 350, 300, 80, null);
                    }
                } catch (IOException e) {
                    System.out.println("sorry");
                }

            }
        }
public void getNumImage(){
            try {
                numbersTile[0] = new Tile();
                numbersTile[0].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit0.png"));

                numbersTile[1] = new Tile();
                numbersTile[1].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit1.png"));

                numbersTile[2] = new Tile();
                numbersTile[2].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit2.png"));

                numbersTile[3] = new Tile();
                numbersTile[3].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit3.png"));

                numbersTile[4] = new Tile();
                numbersTile[4].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit4.png"));

                numbersTile[5] = new Tile();
                numbersTile[5].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit5.png"));

                numbersTile[6] = new Tile();
                numbersTile[6].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit6.png"));

                numbersTile[7] = new Tile();
                numbersTile[7].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit7.png"));

                numbersTile[8] = new Tile();
                numbersTile[8].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit8.png"));

                numbersTile[9] = new Tile();
                numbersTile[9].image = ImageIO.read(getClass().getResourceAsStream("/numbers/digit9.png"));



            }catch (IOException e){
                System.out.println("number images not loaded successfully !");
            }
        }
        private void drawScore(Graphics2D g2) {
            String scoreString = String.valueOf(score); // Convert the score to a string

            // Calculate the total width of the score display
            int totalWidth = scoreString.length() * 70;

            // Calculate the starting x-coordinate to center the score display
            int startX = (screenWidth - totalWidth) / 2;

            // Define the space between digits
            int spaceBetweenDigits = 10;

            // Loop through each digit in the score string
            for (int i = 0; i < scoreString.length(); i++) {
                // Convert the current digit to an integer
                int digit = Character.getNumericValue(scoreString.charAt(i));

                // Draw the digit image
                if (digit >= 0 && digit < numbersTile.length) {
                    g2.drawImage(numbersTile[digit].image, 2 + 55*i, 20, 70, 70, null);
                    System.out.println(digit + " image loaded");
                }
            }
        }


    }













