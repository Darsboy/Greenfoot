import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

import java.util.*;
import java.io.*;
public class Intofile  
{
    static HashMap<String,Player> NameToPlayer=new HashMap<String,Player>();
    static Player currentPlaying=null;

    public static void loadPlayers(){
        Reader read=new Reader();
        NameToPlayer=read.getTextInput("file.txt");
    }

    /**
     * the method that updates the file with the map
     */
    public static void savePlayers(){

        // where you save:

        // 1) setup:
        // I don't know for sure if the file specified here has to already exist, you may need to research on that.
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt",false));

            // 2) Create data to store

            // 3) Actually store the data.
            // You can do this by doing this:
            for(String k: NameToPlayer.keySet()){
                bw.write(k+" "+NameToPlayer.get(k).password+" "+NameToPlayer.get(k).score+" "+NameToPlayer.get(k).level+"\n");
                bw.newLine();
                //bw.write(NameToPlayer.get(k).password+"\n");
                //bw.newLine();

                //bw.newLine();
            }
            /*
            bw.write("=======\n");
            bw.newLine();
            bw.write("=======\n");
            bw.newLine();
            bw.write("=======\n");
             */
            // for every line to save. Replace <string to save> with a String to save in the file.

            // 4) Close it:
            bw.close();
        } catch (Exception e) {
            System.err.println("Error: "+e.getMessage());
        }

    }

    /**
     * the method that uses J option pane to get the player once someone loade
     */
    public static void getPlayer() {
        JFrame frame=new JFrame();
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Username", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);
        frame.setAlwaysOnTop(true);
        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username1 = new JTextField();
        controls.add(username1);
        JPasswordField password1 = new JPasswordField();
        controls.add(password1);
        panel.add(controls, BorderLayout.CENTER);

        //JOptionPane.showMessageDialog(frame, panel, "login", JOptionPane.QUESTION_MESSAGE);

        int k=JOptionPane.showConfirmDialog(frame, panel, "login", JOptionPane.OK_CANCEL_OPTION);

        String username=username1.getText(),password=new String(password1.getPassword());
        if((username.length()==0||password.length()==0)||k==JOptionPane.CANCEL_OPTION)return;
        if(NameToPlayer.containsKey(username)&& NameToPlayer.get(username).password.equals(password)){
            currentPlaying=NameToPlayer.get(username);
        }else{
            JOptionPane.showMessageDialog(frame, "Invalid Username Or Password");
        }
        frame=null;
        //System.out.println(username+" "+password);
    }

    /**
     * Make a new player with J option pane input
     */
    public static void newPlayer() {
        JFrame frame=new JFrame();
        frame.setAlwaysOnTop(true); 
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
        label.add(new JLabel("Username", SwingConstants.RIGHT));
        label.add(new JLabel("Password", SwingConstants.RIGHT));
        panel.add(label, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField username1 = new JTextField();
        controls.add(username1);
        JPasswordField password1 = new JPasswordField();
        controls.add(password1);
        panel.add(controls, BorderLayout.CENTER);

        //JOptionPane.showMessageDialog(frame, panel, "login", JOptionPane.QUESTION_MESSAGE);

        int k=JOptionPane.showConfirmDialog(frame, panel, "New Player", JOptionPane.OK_CANCEL_OPTION);
        String username=username1.getText(),password=new String(password1.getPassword());
        if(k==JOptionPane.CANCEL_OPTION||username.length()==0)return;

        if(!NameToPlayer.containsKey(username)){
            NameToPlayer.put(username,new Player(username,password,0,1));
            currentPlaying=NameToPlayer.get(username);
            savePlayers();
        }else if(NameToPlayer.containsKey(username)){
            JOptionPane.showMessageDialog(frame, "Username taken","Error",JOptionPane.ERROR_MESSAGE);
        }
        frame=null;
    }
}