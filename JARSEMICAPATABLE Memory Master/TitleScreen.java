import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The screen that appears when the game opens
 * 
 * @author (Mercedes) 
 * @version (May 2019)
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    GreenfootSound backgroundMusic=new GreenfootSound("background.mp3");
    Button startButton = new Button();
    public TitleScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Game.width, Game.height, 1); 

        BunchOfCards bunchOfCards = new BunchOfCards();
        addObject(bunchOfCards, getWidth()/2, getHeight()/2 - 200);

        // Title
        Label title = new Label("Memory Master", 90);
        title.setFillColor(Color.WHITE);
        title.setLineColor(Color.WHITE);
        addObject(title, getWidth()/2, getHeight()/2);

        // Subtitle
        Label subtitle = new Label("Are you up for a challenge?", 40);
        subtitle.setFillColor(Color.YELLOW);
        subtitle.setLineColor(Color.YELLOW);
        addObject(subtitle, getWidth()/2, getHeight()/2 + 80);
        addObject(startButton, getWidth()/2, getHeight()/2 + 150);

        backgroundMusic.playLoop();
    }

    public void act()
    {
        if(startButton.clicked())
        {
            Greenfoot.setWorld(new Instructions());
        }
    }
}
