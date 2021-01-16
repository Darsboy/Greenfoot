import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CardCorrection here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CardCorrection extends ButtonTemp
{
    /**
     * Act - do whatever the CardCorrection wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    CardCorrection(Card occupie, Card needed)
    {
        super(Card.cardsImages[occupie.rank][occupie.suit],Card.cardsImages[needed.rank][needed.suit],1);
    }    
        CardCorrection(GreenfootImage occupie, Card needed)
    {
        super(occupie,Card.cardsImages[needed.rank][needed.suit],1);
    }    
}
