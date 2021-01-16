
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CardSlot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardSlot extends Actor
{
    /**
     * Act - do whatever the CardSlot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Card occupie=null;
    Card needed=null;
    GreenfootImage image = new GreenfootImage("CardSlot.png");
    CardSlot(Card n)
    {
        needed=n;
        
        image.scale(Card.length,Card.height);
        setImage(image);
    }

    boolean isRight(){
        if(needed==null||occupie==null)return false;
        return needed.equals(occupie);
    }
    public boolean mouseOver(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        int deg=4;
        if(mouse!=null){
            int leftEdge=getX()+(deg-1)*getImage().getWidth();
            int rightEdge=getX()+(deg)*getImage().getWidth();
            int topEdge=getY();
            int bottomEdge=getY()+getImage().getHeight();
            int mx=mouse.getX(),my=mouse.getY();
            boolean inX= leftEdge<=mx&&mx<=rightEdge;
            boolean inY= topEdge<=my&&my<=bottomEdge;
            return inX&&inY;
        }
        return false;

    }

    public boolean clicked(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if((this.getWorld()!=null&&mouseOver()&&mouse.getButton()==1)){
            return  true;

        }
        return false;
    }
    public void act() 
    {
        /*
        Card touching= (Card)getOneIntersectingObject(Card.class);
        if(touching!=null&&touching.isDragged()==false){
        occupie=touching;
        touching.setLocation(getX(),getY());
        }
         */
        Game world=(Game)getWorld();
        /*
        if (Greenfoot.mouseClicked(this)&&world!=null) {
            world.Clicked=this;
        }
        */
       if(clicked())world.Clicked=this;
        if(occupie!=null){
            GreenfootImage newImg=occupie.getImage();
            //GreenfootImage blank=new GreenfootImage("Card place.png");

            // newImg.scale(blank.getWidth(),blank.getHeight());

            setImage(newImg);

        }else setImage(image);
    }
}
