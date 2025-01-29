import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tutorial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorial extends World
{
    
    GreenfootImage bg = new GreenfootImage("tutorialview.png"); 
    public Tutorial() {    
        super(30, 30, 20);
        prepare();
    }

    private void prepare() {
        // Set background
        setBackground(bg);

        // Tambahkan tombol kembali
        addObject(new Back(),6,26);
    }
}
