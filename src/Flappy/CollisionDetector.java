package Flappy;

import entity.Entity;
import entity.Player;
import tile.Tile;
import tile.TileManager;

import java.awt.*;

public  class CollisionDetector {

   Player player;
    TileManager tileManager;


    GamePannel gp;
    public CollisionDetector(GamePannel gp) {
        this.gp = gp;
    }



    //
    public void checkTile(Entity entity, Player player, TileManager tileManager) {

        if (player == null) {
            System.out.println("Player object is null!");
            return;
        }

    }



}
//        int entityLeftWorldX = entity.worldX + entity.flappyHitBox.x;
//        int entityRightWorldX = entity.worldX + entity.flappyHitBox.x + entity.flappyHitBox.width;
//        int entityTopWorldY = entity.worldY + entity.flappyHitBox.y;
//        int entityBottomWorldY = entity.worldY + entity.flappyHitBox.y + entity.flappyHitBox.height;
//
//        int entityLeftCol = entityLeftWorldX/gp.tileSize;
//        int entityRightcol = entityRightWorldX/gp.tileSize;
//        int entityTopRow = entityTopWorldY/gp.tileSize;
//        int entityBottomRow = entityBottomWorldY/gp.tileSize;
//
//        int tileNum1;
//        int tileNum2;
//
//        switch (entity.direction){
//
//            case "up":
//                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
//
//                break;
//            case "down":
//                break;
//            case "left":
//                break;
//            case "right":
//                break;
//
//        }
//    }
