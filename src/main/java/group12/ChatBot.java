package group12;

//816033924 Kwassi Cardines
public class ChatBot
{
    private String chatBotName;
    private int numResponsesGenerated;
    private static int messageLimit = 10;
    private static int messageNumber = 0;
    
    
    public ChatBot(){
        this.chatBotName = "ChatGPT-3.5";
        this.numResponsesGenerated = 0;
    }
    
    public ChatBot(int LLMCode){
        ChatBotGenerator chatBotGen = new ChatBotGenerator();
        this.chatBotName = ChatBotGenerator.generateChatBotLLM(LLMCode);
        this.numResponsesGenerated = 0;
    }
    
    
    public String getChatBotName( ){
        return chatBotName;
    }
    
    public int getNumResponsesGenerated( ){
        return numResponsesGenerated;
    }
    
    public static int getTotalNumResponsesGenerated( ){
        return messageNumber;
    }
    
    public static int getTotalNumMessagesRemaining( ){
        int remaining;
        remaining = messageLimit - messageNumber;
        return remaining;
    }
    
    
    
    public static boolean limitReached( ){
        if(messageNumber >= messageLimit){
            return true;
        }
        else{
            return false;
        }

    }
    
    public String generateResponse( ){
        numResponsesGenerated++;
        messageNumber++;
        
        return "(Mesage # " + messageNumber + ") Response from " + chatBotName + "\t>>generatedTextHere:";
    }
    
    public String prompt(String requestMessage){
        if(limitReached( )== false){
            return generateResponse();
        }
        else{
            return "Daily Limit Reached. Wait 24 hours to resume chatbot usage";
        }
    }
    
    public String toString( ){
        return "ChatBot Name: " + chatBotName + " Number of Messages Used: " + numResponsesGenerated;
    }
}
