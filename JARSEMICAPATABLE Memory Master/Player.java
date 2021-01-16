/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player  
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Player
     */
    String name,password;int score,level;
    public Player(String name, String password,int score,int lvl)
    {
        this.name=name;
        this.password=password;
        this.score=score;
        this.level=lvl;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String toString()
    {
        // put your code here
        return "Username:"+name + " Score:"+score;
    }

    static boolean compair(Player a,Player b){
        if(a.score==b.score)return a.name.hashCode()<b.name.hashCode();
        return a.score>b.score;
    }
}
