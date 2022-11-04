package com.ahsan;

import java.awt.*;

public class BricksMap {
    public int map[][];
    public int BrickWidth;
    public int BrickHeight;
    public BricksMap(int row , int Column){
        map =new int[row][Column];
        for(int i =0 ; i<map.length ; i++){
            for (int j =0 ; j<map[0].length ; j++){
                map[i][j] =1;   //detect that this bricks haven't intersect with the ball
            }
        }
        BrickWidth = 540/Column;  //set Brick Width
        BrickHeight = 100/row;    //set Brick Height
    }
    public void draw(Graphics2D g){
        for(int i =0 ; i<map.length ; i++){
            for (int j =0 ; j<map[0].length ; j++) {
                if(map[i][j] > 0){
                    g.setColor(Color.WHITE);
                    g.fillRect(j*BrickWidth+80,i*BrickHeight+50,BrickWidth,BrickHeight);

                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(j*BrickWidth+80,i*BrickHeight+50,BrickWidth,BrickHeight);



                }
            }
        }
    }
    public void setBrickValue(int value , int row ,int Column){
        map[row][Column] = value;
    }
}