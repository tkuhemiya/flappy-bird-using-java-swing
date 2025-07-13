import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener{
    int boardWidth;
    int boardHeight;
    boolean gameOver = false;
    double score = 0;

    // Images
    Image backgroundImg;
    Image birdImg;
    Image pipeUpImg;
    Image pipeDownImg;

    Bird bird;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipeTimer;

    FlappyBird(int boardWidth,
               int boardHeight,
               String backgroundImgPath,
               String birdImgPath,
               String pipeUpImgPath,
               String pipeDownPath)
    {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setFocusable(true);
        addKeyListener(this);


        backgroundImg = new ImageIcon(getClass().getResource(backgroundImgPath)).getImage();
        birdImg = new ImageIcon(getClass().getResource(birdImgPath)).getImage();
        pipeUpImg = new ImageIcon(getClass().getResource(pipeUpImgPath)).getImage();
        pipeDownImg = new ImageIcon(getClass().getResource(pipeDownPath )).getImage();

        bird = new Bird(birdImg,boardWidth/8, boardHeight/2, 34, 24);
        pipes = new ArrayList<Pipe>();

        gameLoop = new Timer(1000/60, this);
        pipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipe();
            }
        });
        pipeTimer.start();
        gameLoop.start();

    }

    public boolean collision(Bird bird, Pipe pipe){
        return bird.birdX < pipe.pipeX + pipe.pipeWidth &&
                bird.birdX + bird.width > pipe.pipeX &&
                bird.birdY < pipe.pipeY + pipe.pipeHeight &&
                bird.birdY + bird.height > pipe.pipeY;
    }

    public void placePipe(){
        int pipeY = (int)(-480 + Math.random()* (400));
        Pipe topPipe = new Pipe(pipeDownImg, boardWidth - 10, pipeY);
        pipes.add(topPipe);
        pipeY = (int) (100 + Math.random()*(200)) + topPipe.pipeHeight + pipeY; // random gap + end of top pipe = start of bottom pipe
        Pipe bottomPipe = new Pipe(pipeUpImg, boardWidth - 10, pipeY);
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics){
        graphics.drawImage(backgroundImg,0, 0, boardWidth, boardHeight, null);
        graphics.drawImage(bird.birdImg, bird.birdX, bird.birdY, bird.width, bird.height, null);

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            graphics.drawImage(pipe.pipeImg, pipe.pipeX, pipe.pipeY, pipe.pipeWidth, pipe.pipeHeight, null );
        }

        graphics.setColor(Color.white);
        graphics.setFont(new Font("mono", Font.BOLD, 30));
        if(gameOver){
            graphics.drawString(String.valueOf((int)score), (int)(boardWidth/2 - 15), 30 );
            graphics.drawString("Game Over", (int)(boardWidth/4), (int)(boardHeight/2) );
        }else{
            graphics.drawString(String.valueOf((int)score), (int)(boardWidth/2 - 15), 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(bird.birdY + bird.height >= boardHeight){
            gameOver = true;
        }

        bird.move();
        int offset = 0;

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i-offset);
            if(pipe.pipeX < -pipe.pipeWidth) {
                pipes.remove(i-offset);
                offset += 1;
            }
            if (!pipe.passsed && bird.birdX > pipe.pipeX + pipe.pipeWidth){
                pipe.passsed = true;
                // 0.5 per pipe passed
                score += 0.5;
            }
            if(collision(bird, pipe)){
                gameOver = true;
            }
            pipe.move();
        }

        if(gameOver){
            pipeTimer.stop();
            gameLoop.stop();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
             bird.velocityY = -15;
             if(gameOver){
                 //reset
                 bird = new Bird(birdImg,boardWidth/8, boardHeight/2, 34, 24);
                 pipes = new ArrayList<Pipe>();
                 score = 0.0;
                 gameOver = false;
                 pipeTimer.start();
                 gameLoop.start();

             }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}








