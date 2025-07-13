import java.awt.*;

public class Bird {
    Image birdImg;
    int birdX;
    int birdY;
    int width;
    int height;
    int velocityY = 0;
    int gravity = 1;


    public Bird(Image birdImg, int birdX, int birdY, int width, int height) {
        this.birdImg = birdImg;
        this.birdX = birdX;
        this.birdY = birdY ;
        this.width = width;
        this.height = height;
    }

    public void move(){
        if(birdY < 0){
            birdY = 0;
            velocityY = 0;
        }else{
            birdY += velocityY;
        }
        if(velocityY > 5){
            velocityY = 5 ;
        }else{
            velocityY += gravity;
        }
    }
}
