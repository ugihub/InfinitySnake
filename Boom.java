import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


//Animasi meledak
public class Boom  extends Actor
{
    private int timer = 30; // Durasi ledakan dalam frame
    
    public Boom()
    {
        GreenfootImage image = new GreenfootImage(20,20);
        setImage(image);
        image.setColor(Color.GREEN);
        image.fillRect(0,0,20,20);
    }//End contructor

    public void act() 
    {
    }//End act
}//End class
