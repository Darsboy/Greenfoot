import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The template for all buttons that is going to be created where all buttons must:
 * -have two images when mouse hovers over it shows one image while anouther while the pointer is not over
 * -make a beep sound when mouse hovers over
 * -The images are constructors to the class so it's subclasses are other button that will lead to other rooms
 * All subclasses are made by the same author only differing by images
 * which serve as access-points to different rooms
 * @author (Darcy Liu) 
 * @version (6/8/2019)
 */
public class ButtonTemp extends Actor
{
    /**
     * To shrink the image
     */
    GreenfootSound beep=new GreenfootSound("beep_high.wav");
    GreenfootImage image1 = new GreenfootImage("StartButton.PNG");
    GreenfootImage image2 = new GreenfootImage("StartButtonInverted.png");
    SimpleTimer timer=new SimpleTimer();
    boolean clickSec;
    ButtonTemp(GreenfootImage img1,GreenfootImage img2,double sn) 
    {
        //GreenfootImage image = getImage();
        beep.setVolume(85);
        image1=img1;
        image1.scale((int)(image1.getWidth()*sn), (int)(image1.getHeight() *sn));
        image2=img2;
        image2.scale((int)(image2.getWidth()*sn), (int)(image2.getHeight() *sn));
        setImage(image1);
        //image.scale(image.getWidth()/2, image.getHeight() /2);
        //setImage(image);
        clickSec=false;
        timer.mark();
    }
    boolean needPlay=true;
    //MouseInfo mouse = Greenfoot.getMouseInfo();
    public boolean mouseOver(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null){
            int leftEdge=getX()+getImage().getWidth()/2;
            int rightEdge=getX()+getImage().getWidth()+getImage().getWidth()/2;
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
        if((this.getWorld()!=null&&mouseOver()&&mouseDownSec())){
            return  true;

        }
        return false;
    }

    public boolean mouseDownSec(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse.getButton()==1&&clickSec){
            clickSec=false;
            return true;
        }
        return false;
    }

    /*
    public boolean clicked(){
    return this.getWorld()!=null&&Greenfoot.mouseClicked(this);
    }
     */
    public void actOld(){
        // changes sprite when the curser is over it
        if (Greenfoot.mouseMoved(this)){
            if(needPlay){beep.play();needPlay=false;}
            setImage(image1);
        }
        if (Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
        {
            needPlay=true;
            setImage(image2);
        }
    }

    public void act(){
        // changes sprite when the curser is over it
        if (mouseOver()){
            if(needPlay){beep.play();needPlay=false;}
            setImage(image1);
        }
        else 
        {
            clickSec=true;
            needPlay=true;
            setImage(image2);
        }
        /*
        if(clickSec==false&&timer.millisElapsed()>1000){
        timer.mark();
        clickSec=true;
        }
         */
    }
}
