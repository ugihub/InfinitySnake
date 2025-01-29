import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main extends World
{
    int ThisLevel = 0;
    GreenfootImage bg = new GreenfootImage("main.png");
    
    public Main()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(30, 30, 20); 
        
        setLevel(ThisLevel);
    }
    
    private void setLevel(int b)
    {
        //SetLevel game
        ThisLevel=b;

        switch(b)
        {
            case 0: selector(); break;
            case 1: game(); break;
        }
    }
    
    private void selector()
    {
        // bg.setColor(greenfoot.Color.GRAY);
        // bg.fillRect(0,0,60,60);
        setBackground(bg);
        
        addObject(new start(), 23, 12);
        addObject(new TutorialButton(), 23, 16);
    }
    
    private void game()
    {
        Greenfoot.setWorld(new SnakeWorld());
    }
}
