package group12;

//816033924 Kwassi Cardines
import java.util.ArrayList;

public class ChatBotPlatform
{
    private ArrayList<ChatBot> bots;
    
    public ChatBotPlatform( ){
        bots =  new ArrayList<>( );
    }
    
    public boolean addChatBot(int LLMcode){
        if(ChatBot.limitReached( ) == false){
            bots.add(new ChatBot(LLMcode)); 
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getChatBotList( ){
        String list = "";
        
        int i = 0;
        while(i < bots.size( )){
            list += "Bot Number : " + i + " " + bots.get(i).toString() + "\n";
            i++;
        }
        
        list += "Total Messages Used: " + ChatBot.getTotalNumResponsesGenerated( ) + "\n Total Messages Remaining:  " + ChatBot.getTotalNumMessagesRemaining( );
        return list;
    }
    
    public String interactWithBot(int botNumber, String message ){
        if((botNumber < 0) || ( botNumber >= bots.size( ))){
            return "Incorrect Bot Number (" + botNumber + ") Selected. Try again";
        }
        
        return bots.get(botNumber).prompt(message);
    }
    
    public int getBotSize( ){
        return bots.size( );
    }
 
}
