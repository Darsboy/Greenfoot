import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;
import java.util.*;
/**
 * The room where the top 10 players are shown used a recursive merge sort algorithem to sort the players inversly by their score
 * @author (Darcy Liu) 
 * @version (6/8/2019)
 */
public class RankingBoard extends World
{
    static Vector<Player>ranking=new Vector<Player>();
    // Label k=new Label("",40);
    FromRankingBoard Torb=new FromRankingBoard();
    RankingBoard(){
        super(Game.width,Game.height,1);
        ranking.clear();
        //String put="";

        for(String k:Intofile.NameToPlayer.keySet()){
            if(k.length()>0)ranking.add(Intofile.NameToPlayer.get(k));
        }

        mergeSort(ranking);

        addObject(Torb, getWidth()/2,50);
        drawBoard();
    }

    /**
     * THe method that takes the drawBoard 
     */
    private void drawBoard(){
        int startR=0;
        List remove = getObjects( Label.class );

        if (remove != null) { removeObjects(remove); }
        int x1=300,x2=x1+400;
        int rankingNum=1,oldScore=ranking.get(0).score;
        Label Username=new Label("Username",40);
        Label Score=new Label("Score",40);
        addObject(Username,x1,120);
        addObject(Score,x2,120);
        for(int ind=startR;ind<Math.min(startR+10,ranking.size());ind++){
            // place each username 
            Player y=ranking.get(ind);
            //put=put+y.toString()+"\n";
            Label rank=new Label("",40);
            Label name=new Label("",40);
            Label score=new Label("",40);
            String putOnName=y.name.substring(0,Math.min(y.name.length(),12));
            name.setValue(putOnName);
            score.setValue(y.score);
            if(y.score<oldScore){rankingNum++;oldScore=y.score;}
            rank.setValue(rankingNum);

            // character length is 22 pixels
            addObject(rank,x1-200,40*ind+120+40);
            addObject(name,x1,40*ind+120+40);
            addObject(score,x2,40*ind+120+40);
        }

    }

    public void act(){
        if(Torb.clicked()){
            Greenfoot.setWorld(new StartWorld());
        }
    }

    /**
     * The merge sort algorithem the recursive sort part of the programm 
     * which sorts a vector of players
     */
    static void mergeSort(Vector<Player> arr){
        Vector<Player>aux=new Vector<Player>();
        for(int i=0; i<arr.size(); i++){
            aux.add(arr.get(i));
        }
        mergeSort(arr,aux,0,arr.size()-1);
    }

    static void mergeSort(Vector<Player> arr,Vector<Player> aux, int left,int right){
        if(left>=right)return;
        int mid=(left+right)/2;

        mergeSort(arr,aux,mid+1 ,right);
        mergeSort(arr,aux,left,mid);

        merge(arr,aux,left,mid,right);

    }

    static void merge(Vector<Player>arr,Vector<Player>aux,int left,int mid,int right){
        for(int i=left; i<=right; i++){
            aux.set(i, arr.get(i));
        }
        int lo=left,hi=mid+1;
        for(int k=left; k<=right; k++){
            if(lo>mid){
                arr.set(k,aux.get(hi));
                hi++;
            }else if(hi>right){
                arr.set(k,aux.get(lo));
                lo++;
            }else if(Player.compair(aux.get(lo),aux.get(hi))){  
                arr.set(k,aux.get(lo));
                lo++;
            } else {  
                arr.set(k,aux.get(hi));
                hi++;
            }
        }
    }

}
