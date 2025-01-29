import greenfoot.*;

public class TouchButton extends Actor {
    private String direction;
    private GreenfootImage baseImage;
    private GreenfootImage pressedImage;
    private boolean isPressed = false;

    public TouchButton(String direction) {
        this.direction = direction;
        createImages();
        setImage(baseImage);
    }

    private void createImages() {
        // Gambar tombol normal
        baseImage = new GreenfootImage(60, 60);
        baseImage.setColor(new Color(100, 100, 100, 150));
        baseImage.fillOval(0, 0, 60, 60);
        baseImage.setColor(Color.WHITE);
        baseImage.drawString(direction, 20, 35);

        // Gambar tombol saat ditekan
        pressedImage = new GreenfootImage(60, 60);
        pressedImage.setColor(new Color(200, 200, 200, 150));
        pressedImage.fillOval(0, 0, 60, 60);
        pressedImage.setColor(Color.BLACK);
        pressedImage.drawString(direction, 20, 35);
    }

    public void act() {
        if (Greenfoot.mousePressed(this)) {
            setImage(pressedImage);
            isPressed = true;
        }
        // if (Greenfoot.mouseClicked(null) && isPressed) {
            // setImage(baseImage);
            // isPressed = false;
            // ((SnakeWorld) getWorld()).getSnake().setDirection(direction);
        // }
    }
}