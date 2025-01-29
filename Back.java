import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Back here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Back extends Actor
{
    public Back() {
        GreenfootImage image = new GreenfootImage("back.png");
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Main()); // Kembali ke menu utama
        }
    }
}

