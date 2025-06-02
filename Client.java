import java.util.Scanner;
import java.net.*;
import java.io.*;


public class Client 
{
    public static void main(String[] args) 
    {      
        try 
        {
            System.out.println("Connecting to localhost:8080");
            System.out.println("Please wait...");
            InetAddress address = InetAddress.getByName("localhost");

            Socket client = new Socket("localhost", 8080);
            System.out.print("Player ready.\n");
            System.out.println("Rules: ");
            System.out.println("Paper beates Rock" + "Rock beats Scissors" + "Scissors beats Paper");
     
            InputStreamReader reader = new InputStreamReader(client.getInputStream());
            BufferedReader inbox = new BufferedReader(reader);
            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
            BufferedWriter outbox = new BufferedWriter(writer);
            
            String name = userNameInput();

            outbox.write(name + "\n",0, name.length() +1);
        
            String inputStr = userInput();
            System.out.println(name+ " selected the letter : " + inputStr);
            
            outbox.write(inputStr + "\n", 0, inputStr.length() + 1);

            outbox.flush();

            String playerResult = inbox.readLine();
            System.out.println(playerResult);
        } 
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    private static String userInput()
    {
        Scanner in = new Scanner(System.in);
        String inputStr = null;       
    
        do
        {
            System.out.print("Enter 1 for Rock(R), 2 for Paper(P) or 3 for Scissor(S): ");
            inputStr = in.next();
        } 
        while (!("1".equals(inputStr)) && !("2".equals(inputStr)) && !("3".equals(inputStr)));
            return inputStr;
    }

    private static String userNameInput()
    {
        Scanner in = new Scanner(System.in);
        String name = null;
        do
        {
             System.out.print("Please type your name: \n");
             name = in.next();
        }
        while(name.isEmpty()); 
            return name;
    }
}
