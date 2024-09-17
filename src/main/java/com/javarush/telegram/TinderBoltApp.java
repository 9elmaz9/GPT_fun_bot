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

public class TinderBoltApp extends MultiSessionTelegramBot {
    public static final String TELEGRAM_BOT_NAME = "myy_roulette_bot"; // ім'я бота в лапках
    public static final String TELEGRAM_BOT_TOKEN = "7209559625:AAHkPlpVaPUTBpCN37YDIYi9or6P8V5MaCM"; // токен бота в лапках
    public static final String OPEN_AI_TOKEN = "here should be yout token "; // токен ChatGPT у лапках
    //connect with dialogue mode
    public DialogMode mode = DialogMode.MAIN;
    //connect with gpt service
    public ChatGPTService gptService = new ChatGPTService(OPEN_AI_TOKEN);

    public TinderBoltApp() {
        super(TELEGRAM_BOT_NAME, TELEGRAM_BOT_TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        //TODO: основний функціонал бота будемо писати тут
        //catch message
        String message = getMessageText();

        if (message.equals("/start")) {
            mode = DialogMode.MAIN;
            showMainMenu(
                    "bot 's main menu", "/start",
                    "generate a Tinder profile 😎", " / profile ",
                    "message for starting a conversation 🥰", "/opener",
                    "messaging on your behalf 😈😈", "/message",
                    "chat with celebrities 🔥", "/date",
                    "ask a question to the GPT chat 🧠", "/gpt"
            );


            String menu = loadMessage("main");
            sendTextMessage(menu);

            //sendTextMessage("Hello");
            //sendTextMessage("*Hello*");
            //pictures
            sendPhotoMessage("main");

            return;
        }

        //gpt
        if (message.equals("/gpt")) {
            mode = DialogMode.GPT;
            String gptMessage = loadMessage("gpt");
            sendTextMessage(gptMessage);
            sendPhotoMessage("gpt");

            return;
        }

        if (mode == DialogMode.GPT) {
            String prompt = loadPrompt("gpt");
            //prompt-question
            //String answer =   gptService.sendMessage("Give me answer to my question : ", message); -> with prompt
            String answer = gptService.sendMessage(prompt, message);
            sendTextMessage(answer); // literally answer
            return;
        }


        /**
         test buttons
         String text = getMessageText();
         //sendTextMessage("_" + text + "_");   // _ kursiv   : * bigone
         sendTextMessage("_" + message + "_");

         //buttons
         sendTextButtonsMessage("Button message",
         "START", "start",
         "STOP", "stop"); */
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TinderBoltApp());
    }
}
