import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Square Tail, Food and tail.
 */
public class Tail extends Actor {
    private GreenfootImage image = new GreenfootImage("tail.png"); // Ukuran ekor
    public boolean picked; // Status apakah ekor sudah ditambahkan ke ular

    public Tail() {
        setImage(image);
        picked = false; // Awalnya ekor belum diambil
    }

    public void act() {
        // Logika ekor hanya mengikuti kepala, tidak ada aksi tambahan
    }
}
