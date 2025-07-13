import java.awt.*;

public class Pipe {
    int pipeX;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512 ;
    int velocityX = -4;
    boolean passsed = false;
    Image pipeImg;

    public Pipe(Image pipeImg, int pipeX, int pipeY) {
        this.pipeImg = pipeImg;
        this.pipeX = pipeX;
        this.pipeY = pipeY;
    }

    public void move(){
        pipeX += velocityX;
    }
}
