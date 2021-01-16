import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * In this world the player will login and start the game.
 * Also the images for the cards are also initiated here
 * 
 * @author (Darcy Liu) 
 * @version (6/6/2019)
 */
public class StartWorld extends World
{

    /**
     * Constructor for objects of class StartWorld.
     * 
     */

    static boolean initiated=false;
    Button startButton=new Button();
    toRankingBoard Torb=new toRankingBoard();
    Label player=new Label("Welcome",40);
    

    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(Game.width, Game.height, 1); 
        if(!initiated){
            initiated=true;
            initiateCards();
        }

       

        Label logtorb = new Label("Click to go to score board", 40);
        addObject(logtorb, getWidth()/2, getHeight()/2 + 170);
        addObject(Torb, getWidth()/2,getHeight()/2 + 220);

        addObject(player, getWidth()/2,getHeight()/2);
        Label Start=new Label("",6);
        prepare();
        Intofile.loadPlayers();

        // Login
        Label log = new Label("Login to save your score!", 40);
        log.setFillColor(Color.CYAN);
        log.setLineColor(Color.CYAN);
        addObject(log, getWidth()/2, getHeight()/2 - 170);

        // How to login
        Label log1 = new Label("Press 'g' for returning users", 30);
        log1.setFillColor(Color.PINK);
        log1.setLineColor(Color.PINK);
        addObject(log1, getWidth()/2, getHeight()/2 - 120);

        Label log2 = new Label("Press 'n' to create new login", 30);
        log2.setFillColor(Color.YELLOW);
        log2.setLineColor(Color.YELLOW);
        addObject(log2, getWidth()/2, getHeight()/2 - 70);

        // Are you ready?
        Label ready = new Label("Are you ready?", 50);
        ready.setFillColor(Color.MAGENTA);
        ready.setLineColor(Color.WHITE);
        addObject(ready, getWidth()/2, getHeight()/2 + 60);
        addObject(startButton, getWidth()/2,getHeight()/2 + 110);
    }

    GreenfootImage getCardImage(int rank,int suit){
        int imageNumber=suit*13+rank;
        String imageName=Integer.toString(imageNumber);
        if(imageNumber<10)imageName="0"+Integer.toString(imageNumber);
        return new GreenfootImage("tile0"+imageName+".png");

    }

    public void initiateCards(){
        for(int rank=0; rank<13; rank++){
            for(int suit=0;suit<4;suit++){
                GreenfootImage k=getCardImage(rank,suit);
                k.scale(Card.length,Card.height);
                Card.cardsImages[rank][suit]= k;
                //addObject(new Card(rank,suit),0,0);

            }
        }

    }

    private boolean isNumber(String str){

        for(int i=0; i<str.length(); i++)
            if(!Character.isDigit(str.charAt(i)))return false;
        return true;

    }
    String key="";
    boolean newPlayer,getPlayer;
    public void act(){
        key=Greenfoot.getKey();
        if(Intofile.currentPlaying!=null)
            player.setValue("Welcome\n"+Intofile.currentPlaying.name);

        if(startButton.clicked()){
            if(Intofile.currentPlaying==null){
                /*
                JOptionPane.showMessageDialog(frame,
                "Enter username",
                "User not found",
                JOptionPane.PLAIN_MESSAGE);
                 */
                Intofile.getPlayer();
                startButton.clickSec=true;
            }else{
                int cards=Intofile.currentPlaying.level;
                cards=Math.min(52,cards);

                // Greenfoot.setWorld(new Game(cards,cards*5000,cards*5000000));
                Greenfoot.setWorld(new Game(cards,cards*5000,cards*3000));
            }
        }
        if(Torb.clicked()){
            Greenfoot.setWorld(new RankingBoard());
        }
        newPlayer=Greenfoot.isKeyDown("N");
        getPlayer=Greenfoot.isKeyDown("G");
        if(newPlayer){
            //player.move(1);
            Intofile.newPlayer();
            return;
        }
        if(getPlayer){

            Intofile.getPlayer();
            return;
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
