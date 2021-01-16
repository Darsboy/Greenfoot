import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * WA picture that shows at the beginning of the game
 * 
 * @author (Mercedes) 
 * @version (June 2019)
 */
public class BunchOfCards extends Actor
{
    /**
     * Act - do whatever the BunchOfCards wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public BunchOfCards()
    {
        GreenfootImage myImage = getImage();
        int myNewHeight = (int)myImage.getHeight()/3;
        int myNewWidth = (int)myImage.getWidth()/3;
        myImage.scale(myNewWidth, myNewHeight);
    }
    public void act() 
    {
        // Add your action code here.
    }    
}
