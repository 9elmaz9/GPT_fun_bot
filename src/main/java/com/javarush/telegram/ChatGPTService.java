package com.javarush.telegram;

import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatGPTService {
    private ChatGPT chatGPT;

    private List<Message> messageHistory = new ArrayList<>(); //История переписки із ChatGPT; потрібна для діалогів

    public ChatGPTService(String token) {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("18.199.183.77", 49232));
        if (token.startsWith("gpt:")) {
            token = "sk-proj-" + new StringBuilder(token.substring(4)).reverse();
        }

        this.chatGPT = ChatGPT.builder()
                .apiKey(token)
                .apiHost("https://api.openai.com/")
                .proxy(proxy)
                .build()
                .init();
    }

   /** * Single query to ChatGPT in "question" -> "response" format.
   * The query consists of two parts: 
   * prompt - the context of the question 
   * question - the actual query */
    
    public String sendMessage(String prompt, String question) {
        Message system = Message.ofSystem(prompt);
        Message message = Message.of(question);
        messageHistory = new ArrayList<>(Arrays.asList(system, message));

        return sendMessagesToChatGPT();
    }
    
   /** * Queries to ChatGPT with message history preservation. 
   * The setPrompt() method sets the context for the query. */
    
    public void setPrompt(String prompt) {
        Message system = Message.ofSystem(prompt);
        messageHistory = new ArrayList<>(List.of(system));
    }

  /** * Queries to ChatGPT with message history preservation. 
  * The addMessage() method adds a new question (message) to the chat. */
    
    public String addMessage(String question) {
        Message message = Message.of(question);
        messageHistory.add(message);

        return sendMessagesToChatGPT();
    }

   /** * Send a series of messages to ChatGPT: prompt, message1, answer1, message2, answer2, ..., messageN 
   * ChatGPT's response is added to the end of messageHistory for future use */
    
    private String sendMessagesToChatGPT(){
        ChatCompletion chatCompletion = ChatCompletion.builder()
              //  .model(ChatCompletion.Model.GPT_3_5_TURBO.getName()) // GPT4Turbo or GPT_3_5_TURBO
               // .model(ChatCompletion.Model.GPT_3_5_TURBO) // GPT4Turbo or GPT_3_5_TURBO
                .model(ChatCompletion.Model.GPT4) // GPT4Turbo or GPT_3_5_TURBO
                .messages(messageHistory)
                .maxTokens(3000)
                .temperature(0.9)
                .build();

        ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
        Message res = response.getChoices().get(0).getMessage();
        messageHistory.add(res);

        return res.getContent();
    }
}
