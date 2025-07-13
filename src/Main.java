import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int windowWidth = 360;
        int windowHeight = 640;

        JFrame frame = new JFrame("Flappy Bird");

        frame.setSize(windowWidth, windowHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappyBird = new FlappyBird(
                windowWidth,
                windowHeight,
                "assets/background.png",
                "assets/bird.png",
                "assets/pipeup.png",
                "assets/pipedown.png");
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();

        frame.setVisible(true);

    }
}