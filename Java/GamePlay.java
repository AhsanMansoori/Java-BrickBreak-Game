package com.ahsan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;


public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean Play = false;
    private int Score = 0;
    private int TotalBricks = 30;
    private Timer timer;
    private int Delay = 8;

    private int Player_X = 310;
    private int BallPositionX = 120;
    private int BallPositionY = 350;
    private int BallDirectionX = -1;
    private int BallDirectionY = -2;
    private BricksMap map;


    //Constructor
    public GamePlay() {
        map = new BricksMap(3, 10);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(Delay, this);
        timer.start();

    }

    public void paint(Graphics g) {
        //BackGround
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        //Drawing Bricks
        map.draw((Graphics2D) g);

        //Borders
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        //Score

                g.setColor(Color.YELLOW);
                g.setFont(new Font("Serif", Font.BOLD, 25));
                g.drawString("Score:" + Score, 590, 30);



        //Paddle
        g.setColor(Color.GREEN);
        g.fillRect(Player_X, 550, 100, 8);
        //Ball
        g.setColor(Color.MAGENTA);
        g.fillOval(BallPositionX, BallPositionY, 20, 20);

        //if no bricks remain you won

                if (TotalBricks <= 0) {
                    Play = false;
                    BallDirectionX = 0;
                    BallDirectionY = 0;
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Serif", Font.BOLD, 30));
                    g.drawString("YOU WON  SCORE:" + Score, 260, 300);

                    g.setFont(new Font("Serif", Font.BOLD, 20));
                    g.drawString("PRESS ENTER TO RESTART:", 230, 350);
                }




        //during game if ball fall down and did not touch tha paddle Game is over

                if (BallPositionY > 570) {
                    Play = false;
                    BallDirectionX = 0;
                    BallDirectionY = 0;
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Serif", Font.BOLD, 30));
                    g.drawString("GAME OVER  SCORE:" + Score, 190, 300);

                    g.setFont(new Font("Serif", Font.BOLD, 20));
                    g.drawString("PRESS ENTER TO RESTART:", 230, 350);
                }

        g.dispose();
            }




    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        // ball touch to Paddle
      if(Play){
          if(new Rectangle(BallPositionX,BallPositionY,20,20).intersects(new Rectangle(Player_X,550,100,8))){
              BallDirectionY = -BallDirectionY;
          }
         A:  for (int i = 0; i<map.map.length; i++){
              for(int j = 0; j<map.map[0].length; j++) {
                  if (map.map[i][j] > 0){   //Ball touch brick to break
                      int BrickX = j*map.BrickWidth + 80;
                      int BrickY = i*map.BrickHeight + 50;
                      int BrickWidth = map.BrickWidth;
                      int BrickHeight = map.BrickHeight;

                      Rectangle rect = new Rectangle(BrickX,BrickY,BrickWidth,BrickHeight);
                      Rectangle BallRect = new Rectangle(BallPositionX , BallPositionY,20,20);
                      Rectangle BrickRect =rect;


                      //No Bricks Remain You won
                      if(BallRect.intersects(BrickRect)){
                          map.setBrickValue(0,i,j);
                          TotalBricks --;
                          Score += 5;

                          //ball touch the brick remain back
                          if (BallPositionX+19 <= BrickRect.x || BallPositionX +1 >= BrickRect.x + BrickRect.width) {
                              BallDirectionX = -BallDirectionX;
                          }else{
                              BallDirectionY = -BallDirectionY;
                          }
                          break A;
                      }
                  }
              }
          }

          BallPositionX += BallDirectionX;
          BallPositionY += BallDirectionY;
          if (BallPositionX < 0){
              BallDirectionX = -BallDirectionX;
          }
          if (BallPositionY < 0){
              BallDirectionY = -BallDirectionY;
          }
          if (BallPositionX > 670){
              BallDirectionX = -BallDirectionX;
          }
      }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(Player_X >600){
                Player_X = 600;
            }else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(Player_X < 10){
                Player_X = 10;
            }else {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!Play){
                Play = true;
                BallPositionX = 120;
                BallPositionY = 350;
                BallDirectionX = -1;
                BallDirectionY = -2;
                Player_X = 310;
                Score = 0;
                TotalBricks =30;
                map =new BricksMap(3,10);

                repaint();

            }
        }

    }
//spped of paddle
    private void moveRight() {
        Play = true;
                Player_X += 20;

    }
    private void moveLeft() {
        Play = true;
               Player_X -= 20;

    }


}