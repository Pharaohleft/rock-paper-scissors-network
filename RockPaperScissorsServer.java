import java.net.*;
import java.io.*;

public class RockPaperScissorsServer 
{
    public static void main(String[] args) throws Exception
    {
        runSocket(8080);
    }
       
    public static void runSocket(int port)
    {         
        try 
        {            
            ServerSocket Services = new ServerSocket(port);
            
            Services.setSoTimeout(30000);
            System.out.println("Wating for players...");
            System.out.println("1 is Rock, 2 is Paper, 3 is Scissors");
            
            while(true)
            {                
                Socket connectionSocket1 = Services.accept();
                Socket connectionSocket2 = Services.accept();
                            
                InputStreamReader input1 = new InputStreamReader(connectionSocket1.getInputStream());
                OutputStreamWriter output1 = new OutputStreamWriter(connectionSocket1.getOutputStream());
                BufferedReader player1 = new BufferedReader(input1);
                BufferedWriter Client1Output = new BufferedWriter(output1);
                              
                String name1 = player1.readLine();
                 
                String player1Input = player1.readLine();
                System.out.println("Player 1 is : "+name1);
                System.out.println(name1+"'s choice is : "+player1Input);
                                
                int playerMove1 = Integer.parseInt(player1Input);
                String Result = null;
                
                InputStreamReader input2 = new InputStreamReader(connectionSocket2.getInputStream());
                OutputStreamWriter output2 = new OutputStreamWriter(connectionSocket2.getOutputStream());
                BufferedReader player2 = new BufferedReader(input2);
                BufferedWriter Client2Output = new BufferedWriter(output2);
                                 
                String name2 = player2.readLine();
                               
                String player2Input = player2.readLine();
                System.out.println("Player 2 is : "+name2);
                System.out.println(name2+"'s choice is : "+player2Input);
                              
                int playerMove2 = Integer.parseInt(player2Input);
                String Result2 = null;
                
                if (playerMove1 == playerMove2) 
                {    
                    Result = "DRAW";
                    Result2 = "DRAW";
                } 
                
                else if (playerMove1 == 1 && playerMove2 == 2) 
                {
                    Result = "YOU LOSE :(";
                    Result2 = "YOU WIN!";
                } 
                
                else if (playerMove1 == 1 && playerMove2 == 3) 
                {
                    Result = "YOU WIN!";
                    Result2 = "YOU LOSE :(";
                } 
                
                else if (playerMove1 == 2 && playerMove2 == 1) 
                {
                    Result = "YOU WIN!";
                    Result2 = "YOU LOSE :(";
                } 
                
                else if (playerMove1 == 2 && playerMove2 == 3) 
                {
                    Result = "YOU LOSE";
                    Result2 = "YOU WIN!";
                } 
                
                else if (playerMove1 == 3 && playerMove2 == 1) 
                {
                    Result = "YOU LOSE :(";
                    Result2 = "YOU WIN!";
                } 
                
                else if (playerMove1 == 3 && playerMove2 == 2) 
                {
                    Result = "YOU WIN!";
                    Result2 = "YOU LOSE :(";
                }
                                
                Client1Output.write(Result, 0, Result.length());
                Client2Output.write(Result2, 0, Result2.length());
                Client1Output.flush();
                Client2Output.flush();
                System.out.println(name1+ " : " + Result);
                System.out.println(name2+ " : " + Result2);
                               
                connectionSocket1.close();
                connectionSocket2.close();                          
            }             
        } 
        catch (IOException e) 
        {
             System.out.println("Error:");
             System.out.println(e);
        }        
    }    
}
