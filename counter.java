import greenfoot.*;  

public class counter extends Actor {
    private int value = 0; // Nilai awal counter
    private String text = "Length: "; // Label counter
    private int fontSize = 20; // Ukuran font

    public counter() {
        updateImage();
    }

    public void act() {
        // Counter tidak memerlukan logika dalam act()
    }

    public void setValue(int newValue) {
        value = newValue;
        updateImage();
    }

    private void updateImage() {
        GreenfootImage image = new GreenfootImage(text + value, fontSize, Color.WHITE, Color.BLACK);
        setImage(image);
    }
}
