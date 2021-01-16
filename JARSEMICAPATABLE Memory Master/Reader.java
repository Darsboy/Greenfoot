import java.util.*;
import java.io.InputStream;
/**
 * A reader for files that you want to use within a Greenfoot project.
 * Author(Darcy)
 * 4/11/2019
 */
public class Reader 
{
    /**
     * Opens a text file inside the package folder and returns a scanner to
     * read it. This works for text files inside jar files.
     * 
     * @param name The name of the text file
     * @return A Scanner object that is used to read the contents of the text   
     *  file.
     */
    public Scanner getScanner(String filename){
        InputStream myFile = getClass().getResourceAsStream(filename);
        if(myFile != null){
            return new Scanner(myFile);
        }
        return null;
    }

    /* 
     * Example use of the Reader in the constructor for MyWorld.
     * This will read all words in the nouns.txt file into an arraylist
     */
    public HashMap<String,Player> getTextInput(String fileName)
    {
        HashMap<String,Player> usernametoPlayer=new HashMap<String,Player>();

        Scanner input = getScanner(fileName);
        ArrayList<String> nounsList = new ArrayList<String>();

        while(input!=null&&input.hasNext())
        {String []str=new String[4];
            String curLine=input.nextLine();
            int start=0,k=0;
            for(int i=0;i<curLine.length(); i++){
                if(curLine.charAt(i)==' '){
                    
                    str[k]=curLine.substring(start,i);
                    k++;
                    start=i+1;
                }
            }
            str[k]=curLine.substring(start,curLine.length());
            if(str[0]!=null&&str[1]!=null&&str[2]!=null)
            usernametoPlayer.put(str[0],new Player(str[0],str[1],Integer.parseInt(str[2]),Integer.parseInt(str[3])));
        }
        return usernametoPlayer;
    }

}
