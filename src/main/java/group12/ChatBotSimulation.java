package group12;

//816033924 Kwassi Cardines
import java.util.Random;
public class ChatBotSimulation
{
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        ChatBotPlatform platform = new ChatBotPlatform( );
        
        int i = 0;
        while( i <= 6 ){
            platform.addChatBot(i);
            i++;
        }
        
        System.out.println("--------------------");
        System.out.println(" Your ChatBots");
        System.out.println(platform.getChatBotList( ));
        System.out.println("--------------------");
        
        Random rand = new Random();
        
        int j = 0;
        while(j < 15){
            int num = rand.nextInt(10);
            System.out.println(platform.interactWithBot(num, "Hello "));
            j++;
        }
        
        System.out.println("-----------------");
        System.out.println(" Your ChatBots" );
        System.out.println(platform.getChatBotList( ));
        System.out.println("------------------");
        
        
    }
}
