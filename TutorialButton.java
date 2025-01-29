import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TutorialButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TutorialButton extends Actor
{
    public TutorialButton() {
        GreenfootImage image = new GreenfootImage("tutorial.png");
        setImage(image);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Tutorial()); // Pindah ke layar tutorial
        }
    }
}

