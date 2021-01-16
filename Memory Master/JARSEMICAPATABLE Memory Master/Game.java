import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.util.*;

/**
 * The main game world that shows the cards
 * 
 * 
 * @author (Darcy Liu) 
 * @version (6/7/2019)
 */
public class Game extends greenfoot.World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    static int height=600,width=(int)(1.6*height);

    int topCard=50;
    CardSlot Clicked=null;
    Card selectedCard=null;

    Label numbers=new Label("",20);
    Label isRight=new Label("",40);

    String code="";
    Random rand=new Random();
    boolean pass=false;
    int difficulty;
    List<CardSlot> slots=new LinkedList<CardSlot>();
    Vector<Card>cards=new Vector<Card>();
    SimpleTimer timer=new SimpleTimer();

    Label countDown=new Label("Count Down:",20);
    Label player=new Label("Welcome",40);
    Button button=new Button();
    Button button1=new Button();
    Button endButton=new Button();
    CardSlot testSlot=new CardSlot(new Card(0,0));

    int imagelength=Card.length+5;
    int imageHeight=Card.height+5;

    SubmitButton submitButton = new SubmitButton();
    int lookingTime,solvingTime;
    public Game(int difficulty,int lt,int st)
    {   
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(width, height, 1); 
        this.difficulty=Math.min(difficulty,52);
        lookingTime=lt;
        solvingTime=st;
        shuffleCards(cards);
        

        addObject(button, getWidth()/2,getHeight()-200);

        addObject(numbers,getWidth()/2,height-40);
        addObject(isRight,getWidth()/2,height-80);
        //addObject(testSlot,getWidth()/4,getHeight()/2);

        addObject(countDown,getWidth()-getWidth()/16,16);
        addObject(player, getWidth()/2,getHeight()/2);
        player.setValue("Are you ready to memorize "+difficulty+" cards "+Intofile.currentPlaying.name+"\nPress the following button to start");
        isRight.setFillColor(Color.RED);
        isRight.setLineColor(Color.RED);
    }
    int yCord=432;
    /**
     * parameters:
     * start the amount of pixels that is shifted tight
     * amount the amount of cards that is being placed
     * typ the type of generation 0 for just placeing cards
     * 1 for generating the card slots
     */
    public void generate(int start, int amuont,int typ){
        if(typ==0){
            for(int i=0; i<amuont; i+=1){
                int x=(i*imagelength)%(13*imagelength)+start, y=topCard+imageHeight*((i)/(13));
                slots.add(new CardSlot(cards.get(i)));
                addObject(cards.get(i),x,y);

            }
        }
        if(typ==1){
            removeCards();

            for(int rank=0; rank<13; rank++){
                //for(int suit=0;suit<4;suit++){

                Card place=new Card(rank,0);
                GreenfootImage newimg= place.getImage();

                //newimg.scale(30,48);
                place.setImage(newimg);
                addObject(place,rank*(newimg.getWidth()+5)+64,yCord);

                // }
            }

            for(int i=0; i<amuont; i+=1){
                int x=(i*imagelength)%(13*imagelength)+start, y=topCard+imageHeight*(i/(13));
                addObject(slots.get(i),x,y);
            }

        }
    }

    /**
     * a debug code that solved all the blanks
     */
    public void cheat(){
        for(CardSlot k:slots){
            /*
            Card occupie=null;
            Card needed=null;
             */
            k.occupie=k.needed;

        }
    }

    /**
     * removes all of the cards
     */
    public void removeCards(){
        List remove = getObjects( Card.class );

        if (remove != null) { removeObjects(remove); }
    }

    /**
     * formally used method to obtain the card proven to be too slow for the player to drag and drop
     */
    public Card  returnCard(){
        Object[] possibleValues = { "Ace", "2", "3","4","5","6","7","8","9","10","Jack","Queen","King" };
        HashMap<Object,Integer> mapInd = new HashMap<Object,Integer>();
        for(int i=0; i<possibleValues.length;i++){
            mapInd.put(possibleValues[i],i);

        }
        Object selectedValue = JOptionPane.showInputDialog(null,"Choose rank", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0]);
        if(selectedValue==null)return null;
        Object[] possibleValuesSuit = {"Clovers","Heart","Spade","Diamond"};
        Object selectedValueSuit = JOptionPane.showInputDialog(null,"Choose rank", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValuesSuit, possibleValuesSuit[0]);
        if(selectedValue==null||selectedValueSuit==null)return null;
        HashMap<Object,Integer> mapIndSuit = new HashMap<Object,Integer>();
        for(int i=0; i<possibleValuesSuit.length;i++){
            mapIndSuit.put(possibleValuesSuit[i],i);

        }
        //System.out.println(selectedValue+" "+selectedValueSuit);
        if(selectedValue==null||selectedValueSuit==null)return null;

        numbers.setValue(mapInd.get(selectedValue)+" "+mapIndSuit.get(selectedValueSuit));
        return new Card(mapInd.get(selectedValue),mapIndSuit.get(selectedValueSuit));

        //return new Card(mapInd.get(selectedValue),mapIndSuit.get(possibleValuesSuit));
    }

    int oldrank=-1;
    Card[] Show=new Card[4];
    /**
     * the method used to show the card pannel
     * The cards will be clicked and selected for being placed on the slot
     */
    public void show(){

        for(int rank=0; rank<13; rank++){
            if(selectedCard!=null&&selectedCard.rank==rank&&rank!=oldrank){
                oldrank=rank;
                for(int suit=1; suit<4; suit++){
                    removeObject(Show[suit]);  
                }
                for(int suit=1; suit<4; suit++){

                    Card place=new Card(selectedCard.rank,suit);
                    GreenfootImage newimg= place.getImage();

                    Show[suit]=place;  
                    //suit
                    // addObject(place,selectedCard.rank*(newimg.getWidth()+5)+64,300+suit*(newimg.getHeight()+5));
                    addObject(place,(suit+selectedCard.rank-1)*(newimg.getWidth()+5)+64 ,yCord+(newimg.getHeight()+5));
                }

            }else if(selectedCard==null){
                for(int suit=1; suit<4; suit++){
                    removeObject(Show[suit]);  
                }
            }
        }
    }

    /**
     * Method that takes in a list of 52 cards and shuffles them
     */
    public void shuffleCards(List<Card>cards){
        cards.clear();
        for(int rank=0; rank<13; rank++){
            for(int suit=0;suit<4;suit++){
                int imageNumber=suit*13+rank;
                String imageName=Integer.toString(imageNumber);
                if(imageNumber<10)imageName="0"+Integer.toString(imageNumber);

                cards.add(new Card(rank,suit));
                //addObject(new Card(rank,suit),0,0);

            }
        }
        for(int i=0; i<cards.size(); i++){
            int randomIndex = rand.nextInt(52);
            Card x = cards.get(i);
            Card y =  cards.get(randomIndex);

            cards.set(i, y);
            cards.set(randomIndex, x);
        }
    }

    /**
     * Check did the player get the order of the cards correct
     * 
     */
    private boolean checkTrue(){
        boolean ret=true;
        int i=0;
        for(CardSlot k:slots){
            ret=ret&&k.isRight();
            int x=(i*imagelength)%(13*imagelength)+leftBorder, y=topCard+imageHeight*((i)/(13));
            i++;

            GreenfootImage image = new GreenfootImage("CardSlot.png");
            image.scale(Card.length,Card.height);
            if(k.occupie==null)addObject(new CardCorrection(image,k.needed), x,y);
            else addObject(new CardCorrection(k.occupie,k.needed), x,y);
        }
        return ret;
    } 
    boolean solving =false,started=false,end=false,correct,manuallyStartSolvingPhase=false,subMitButonExists=false,dialogue=true;
    int ithCard=0,leftBorder=64;
    int lookElapsed=0,solveElapsed=0;
    SimpleTimer second=new SimpleTimer();
    Label b1=new Label("Click button to pass",40);

    public void act(){
        // press start

        if(selectedCard!=null&&Clicked!=null){
            Clicked.occupie=selectedCard;
            Clicked=null;
            dialogue=true;
        }

        if(button.clicked()&&!started){
            // see time start of looking phase
            started=true;
            timer.mark();
            removeCards();
            generate(leftBorder,difficulty,0);
            ithCard=0;
            lookElapsed++;
            second.mark();
            removeObject(button); 

            addObject(b1, getWidth()/2,getHeight()-60);
            addObject(button1, getWidth()/2,getHeight()-20);
            player.setValue("");
            manuallyStartSolvingPhase=false;
        }
        int timeLeft=0;
        if(started&&!solving&&!end){
            // looking phase
            manuallyStartSolvingPhase=manuallyStartSolvingPhase||button1.clicked();
            timeLeft=(lookingTime-timer.millisElapsed())/1000;//in seconds
            int hours=timeLeft/3600;
            int minutes=(timeLeft%3600)/60;
            int seconds=(timeLeft%3600)%60;
            countDown.setValue("Count Down:\n"+hours+"H"+minutes+"M"+seconds+"S");
            solveElapsed++;
        }else if(started&&solving&&!end&&timeLeft>=0){
            // solveing phase
            show();
            int endInterval=lookingTime+solvingTime;
            timeLeft=(endInterval-timer.millisElapsed())/1000;//in seconds
            int hours=timeLeft/3600;
            int minutes=(timeLeft%3600)/60;
            int seconds=(timeLeft%3600)%60;
            countDown.setValue("Count Down:\n"+hours+"H"+minutes+"M"+seconds+"S");
            solveElapsed++;
            if(submitButton.clicked()){
                checkCards();
            }
        }
        // new card

        if(Greenfoot.isKeyDown("C")&&(solving||!started)&&dialogue){
            JFrame frame=new JFrame();
            frame.setAlwaysOnTop(true);
            dialogue=false;
            code= JOptionPane.showInputDialog(frame,"Enter cheat code");
            if("Darcy Liu".equals(code))
                cheat();
            frame=null;
        }

        if((timer.millisElapsed()>lookingTime||manuallyStartSolvingPhase)&&started&&!solving&&!end){
            // start of solveing phase
            solving=true;
            CardSlot forImg=new CardSlot(null);
            removeObject(button1); 

            generate(leftBorder,difficulty,1);
            //submitButton.clickSec=false;
            Clicked=null;
            selectedCard=null;
            second.mark();
            b1.setValue("Click button to submit");
            addObject(submitButton, getWidth()/2, getHeight()-20);
        }
        boolean timesup=(timer.millisElapsed()>lookingTime+solvingTime&&started&&solving);

        if(end&&endButton.clicked()){
            if(correct){
                Intofile.NameToPlayer.get(Intofile.currentPlaying.name).score+=(timeLeft+difficulty*difficulty);
                Intofile.NameToPlayer.get(Intofile.currentPlaying.name).level++;
                Intofile.savePlayers();
            }
            Greenfoot.setWorld(new StartWorld());
        }
    }

    public void checkCards() {
        // end of the game
        removeCards();

        //removeObject(submitButton);
        removeObject(b1);
        removeCards();
        correct=checkTrue();
        if(correct){
            //isRight.setValue("True");
            player.setLocation(getWidth()/2,imageHeight*4+40);
            player.setValue("Congratulations "+Intofile.currentPlaying.name);

        }
        else isRight.setValue("Wrong cards!\n Hover mouse over the cards to see what you picked");
        //started=false;
        solving=false;
        end=true;
        removeObject(submitButton);

        addObject(endButton,getWidth()/2,getHeight()-20);

        /*
        List remove = getObjects( Card.class );
        if (remove != null) { removeObjects(remove); }
         */
    }
}
