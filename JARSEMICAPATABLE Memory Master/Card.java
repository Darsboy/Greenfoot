import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card extends ButtonTemp
{
    /**
     * Act - do whatever the Card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int rank,suit;
    static GreenfootImage[][] cardsImages=new GreenfootImage[13][4];//rank and suit
    static int length=55,height=(int)(1.6*length);
    Card(int rank,int suit){

       super(cardsImages[rank][suit],cardsImages[rank][suit],1);
        this.rank=rank;
        this.suit=suit;
        /*
        if(rank>=0&&suit>=0)
        setImage(cardsImages[rank][suit]);
         */
    }
    boolean drag = false;
    boolean isDragged(){
        return drag;
    }

    boolean equals(Card p){
        return p.rank==this.rank&&p.suit==this.suit;
    }

    /*
    public void act() 
    {
    MouseInfo mouse= Greenfoot.getMouseInfo();
    if(Greenfoot.mousePressed(this)) drag = true;
    if(Greenfoot.mouseClicked(null)) drag = false;

    if(drag) {
    setLocation(mouse.getX(),mouse.getY());
    }
    }    
     */
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
    public void act(){
        Game world=(Game)getWorld();
        /*
        if (Greenfoot.mouseClicked(this)&&world!=null) {
        world.selectedCard=this;
        }
         */
        if(clicked())world.selectedCard=this;
    }
}
