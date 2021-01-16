import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The instructions screen one of the first screens that was visited to provide instruction to the player
 * 
 * @author (Mercedes) 
 * @version (June 2019)
 */
public class Instructions extends World
{
    
    /**
     * Constructor for objects of class Instructions.
     * 
     */
    Button button = new Button();
    public Instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Game.width, Game.height, 1);
        
        // Header 1
        Label h1 = new Label("Ever thought of testing your memory?", 40);
        h1.setFillColor(Color.WHITE);
        h1.setLineColor(Color.WHITE);
        addObject(h1, getWidth()/2, getHeight()/2 - 200);
        
        // Header 2
        Label h2 = new Label("Well, now is your chance to show how great you are!", 40);
        h2.setFillColor(Color.WHITE);
        h2.setLineColor(Color.WHITE);
        addObject(h2, getWidth()/2, getHeight()/2 - 150);
        
        // Subtitle 1
        Label sub1 = new Label("How to Play", 40);
        sub1.setFillColor(Color.YELLOW);
        sub1.setLineColor(Color.YELLOW);
        addObject(sub1, getWidth()/2, getHeight()/2 - 50);
        
        // Line 1
        Label ln1 = new Label("1. Remember the order of cards within the time given.", 30);
        ln1.setFillColor(Color.CYAN);
        ln1.setLineColor(Color.CYAN);
        addObject(ln1, getWidth()/2, getHeight()/2-20);
        
        // Line 2
        Label ln2 = new Label("2. Once the cards disappear, you are given a specific time to \n complete the game. Click on the number you want to choose.", 30);
        ln2.setFillColor(Color.PINK);
        ln2.setLineColor(Color.PINK);
        addObject(ln2, getWidth()/2, getHeight()/2 + 40);
        
        // Line 3
        Label ln3 = new Label("3.To select card Click on the desired suit on the card.", 30);
        ln3.setFillColor(Color.GREEN);
        ln3.setLineColor(Color.WHITE);
        addObject(ln3, getWidth()/2, getHeight()/2 + 100);
        
        // Line 4
        Label ln4 = new Label("4. Click on the desired yellow spot on the top left of the screen \nto set the slot to the desiered card.", 30);
        ln4.setFillColor(Color.MAGENTA);
        ln4.setLineColor(Color.MAGENTA);
        addObject(ln4, getWidth()/2, getHeight()/2 + 145);
        
        // Line 5
        Label ln5 = new Label("5. If you're satisfied with your choices before time is up, click submit.", 30);
        ln5.setFillColor(Color.RED);
        ln5.setLineColor(Color.RED);
        addObject(ln5, getWidth()/2, getHeight()/2 + 210);
        
        addObject(button, getWidth()/2, getHeight()/2 + 250);
    }
    public void act()
    {
        if(button.clicked())
        {
            Greenfoot.setWorld(new StartWorld());
        }
    }
}
