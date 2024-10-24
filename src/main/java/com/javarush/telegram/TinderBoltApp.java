package com.javarush.telegram;

import com.javarush.telegram.ChatGPTService;
import com.javarush.telegram.DialogMode;
import com.javarush.telegram.MultiSessionTelegramBot;
import com.javarush.telegram.UserInfo;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

public class TinderBoltApp extends MultiSessionTelegramBot {
    public static final String TELEGRAM_BOT_NAME = "myy_roulette_bot"; // ім'я бота в лапках
    public static final String TELEGRAM_BOT_TOKEN = ""; // токен бота в лапках
    //here should be your token
    public static final String OPEN_AI_TOKEN = ""; // токен ChatGPT у лапках


    //connect with dialogue mode
    public DialogMode mode = DialogMode.MAIN;

    //place for keeping message
    private List<String> chat;


    //for question in profile
    private UserInfo myInfo;
    //for opener
    private UserInfo personInfo;


    //count  question
    private int questionNumber;


    //connect with gpt service
    public ChatGPTService gptService = new ChatGPTService(OPEN_AI_TOKEN);

    public TinderBoltApp() {
        super(TELEGRAM_BOT_NAME, TELEGRAM_BOT_TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {

        //TODO: main bot functionality will be written here
        //catch message
        String message = getMessageText();

        switch (message) {
            case "/start" -> {
                mode = DialogMode.MAIN;

                showMainMenu(
                        "bot 's main menu", "/start",
                        "generate a Tinder profile 😎", " / profile ",
                        "message for starting a conversation 🥰", "/opener",
                        "messaging on your behalf 😈😈", "/message",
                        "chat with celebrities 🔥", "/date",
                        "ask a question to the GPT chat 🧠", "/gpt"
                );

                sendPhotoMessage("main");
                String menu = loadMessage("main");
                sendTextMessage(menu);

                //sendTextMessage("Hello");
                //sendTextMessage("*Hello*");
                //pictures
                return;
            }

            //gpt
            case "/gpt" -> {
                mode = DialogMode.GPT;

                sendPhotoMessage("gpt");
                String gptMessage = loadMessage("gpt");
                sendTextMessage(gptMessage);
                return;
            }

            //date
            case "/date" -> {
                mode = DialogMode.DATE;

                sendPhotoMessage("date");
                String dateMessage = loadMessage("date");
                sendTextButtonsMessage(dateMessage,
                        "Ariana Grande 🔥", "date_grande",
                        "Margot Robbie 🔥🔥 ", "date_robbie",
                        "Zendaya 🔥🔥🔥", "date_zendaya",
                        "Ryan Gosling 😎", "date_gosling",
                        "Tom Hardy 😎😎", "date_hardy");

                return;
            }

            //message
            case "/message" -> {
                mode = DialogMode.MESSAGE;

                sendPhotoMessage("message");
                String gptMessageHelper = loadMessage("message");
                //  sendTextMessage(gptMessageHelper);

                sendTextButtonsMessage(gptMessageHelper,
                        "next message", "message_next",
                        "ask for a date", "message_date");

                chat = new ArrayList<>(); // stores all сhat
                return;
            }

            //profile
            case "/profile" -> {
                mode = DialogMode.PROFILE;

                sendPhotoMessage("profile");
                String profileMessage = loadMessage("profile");
                sendTextMessage(profileMessage);

                myInfo = new UserInfo();
                questionNumber = 1;
                sendTextMessage("Enter your name"); // enter your name

                return;
            }
            //profile
            case "/opener" -> {
                mode = DialogMode.OPENER;

                sendPhotoMessage("opener");
                String profileMessage = loadMessage("opener");
                sendTextMessage(profileMessage);

                personInfo = new UserInfo();
                questionNumber = 1;
                sendTextMessage("Enter your name"); // enter your name

                return;
            }
        }


        switch (mode) {

            case GPT -> {
                //   (mode == DialogMode.GPT) {
                String prompt = loadPrompt("gpt");
                Message msg = sendTextMessage("ChatGPT print ... ");

                //prompt-question
                //String answer =   gptService.sendMessage("Give me answer to my question : ", message); -> with prompt
                String answer = gptService.sendMessage(prompt, message);
                updateTextMessage(msg, answer);
                //sendTextMessage(answer); // literally answer
            }


            case DATE -> {
                //  if (mode == DialogMode.DATE) {

                String query = getCallbackQueryButtonKey();

                if (query.startsWith("date_")) {
                    sendPhotoMessage(query);
                    String prompt = loadPrompt(query);
                    gptService.setPrompt(prompt);

                    return;

                }
                Message msg = sendTextMessage("ChatGPT print ... ");

                String answer = gptService.addMessage(message); // notopen new dialog , rememer history - just add extra info
                updateTextMessage(msg, answer);
                //sendTextMessage(answer); // literally answer
            }


            case MESSAGE -> {
                //if (mode == DialogMode.MESSAGE) {

                String query = getCallbackQueryButtonKey();

                if (query.startsWith("message_")) {
                    String prompt = loadPrompt(query);
                    String history = String.join("/n/n", chat);

                    Message msg = sendTextMessage("ChatGPT print ... ");

                    String answer = gptService.sendMessage(prompt, history);
                    updateTextMessage(msg, answer);
                    //sendTextMessage(answer); when update -doesnt need anymore

                }

                chat.add(message); // help store message
            }
            case PROFILE -> {
                if (questionNumber <= 6) {
                    askQuestion(message, myInfo, "profile");
                }

            }

            case OPENER -> {
                if (questionNumber <= 6) {
                    askQuestion(message, personInfo, "opener");
                }
            }
        }
    }

    //metod ASKQUESTION
    private void askQuestion(String message, UserInfo user, String profileName) {
        switch (questionNumber) {
            case 1 -> {
                //myInfo.name = message; ->  beter change all  - for  user
                user.name = message;
                questionNumber = 2;
                sendTextMessage("Please enter your age ");

                return;
            }
            case 2 -> {
                user.age = message;
                questionNumber = 3;
                sendTextMessage("Enter what city  you are from ");

                return;
            }
            case 3 -> {
                user.city = message;
                questionNumber = 4;
                sendTextMessage("Enter your occupation ");

                return;
            }
            case 4 -> {
                user.occupation = message;
                questionNumber = 5;
                sendTextMessage("Enter your hobby?");

                return;
            }
            case 5 -> {
                user.hobby = message;
                questionNumber = 6;
                sendTextMessage("Enter your goals for dating?");

                return;
            }
            case 6 -> {
                user.goals = message;

                String prompt = loadPrompt(profileName);
                Message msg = sendTextMessage("ChatGPT print ... ");

                String answer = gptService.sendMessage(prompt, user.toString());
                //sendTextMessage(answer);
                updateTextMessage(msg, answer);

                return;
            }
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TinderBoltApp());
    }
}


/**
 * test buttons
 * String text = getMessageText();
 * //sendTextMessage("_" + text + "_");   // _ kursiv   : * bigone
 * sendTextMessage("_" + message + "_");
 * <p>
 * //buttons
 * sendTextButtonsMessage("Button message",
 * "START", "start",
 * "STOP", "stop");
 */
