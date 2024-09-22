# GTP_fun_bot

![gpt](https://i.ibb.co/ZNmFxqk/12.jpg)


GTP_fun_bot is a multi-functional Telegram bot that uses OpenAI's ChatGPT for interactive conversations and content generation related to Tinder profiles, messages, and more. This bot can engage in dialogues, create profiles, send messages, and interact with users using the powerful OpenAI models.

## Key Features

- **OpenAI ChatGPT Integration**: The bot leverages the power of GPT-3 and GPT-4 models to generate meaningful and contextual responses to user queries.
- **Profile Creation**: The bot can ask a series of questions to help users create Tinder-like profiles.
- **Message Sending and Management**: Users can send and manage text and photo messages, including interactive inline buttons.
- **Multi-Mode Dialog**: The bot supports multiple dialogue modes, such as profile creation, opener generation, messaging, date simulations, and direct communication with ChatGPT.
- **Message History**: The bot remembers the message history to provide more meaningful and continuous dialogues.

## How It Works

1. **Bot Activation**: Users interact with the bot via Telegram using commands like `/start` to access the main menu.
2. **Interactive Responses via OpenAI**: User requests are processed using OpenAI's GPT, which enables the bot to generate intelligent and context-aware responses.
3. **Dialogue Modes**:
    - `GPT`: Chat directly with OpenAI's GPT to ask any question.
    - `PROFILE`: Create personalized profiles by answering a series of questions.
    - `OPENER`: Generate unique icebreaker messages for Tinder.
    - `MESSAGE`: Manage and send interactive messages with inline buttons.
    - `DATE`: Simulate dates with celebrities using AI-generated dialogue.

4. **Multimedia Support**: The bot can send both images and text messages with Markdown formatting, and update previously sent messages.

## Setup and Installation

To set up the project, follow these steps:

1. Clone the repository.
2. Ensure you have Java 17 installed.
3. Update the bot token and OpenAI API token in the source code:
   ```java
   public static final String TELEGRAM_BOT_TOKEN = "your-telegram-bot-token";
   public static final String OPEN_AI_TOKEN = "your-openai-api-token";


## Build and run the project:

  - mvn clean install
  - java -jar target/gtp-fun-bot.jar 
  

 
## Usage
After starting the bot, you can interact with it in Telegram using the following commands:

- /start - Display the main menu.
- /gpt - Ask a question directly to ChatGPT.
- /profile - Create or edit your Tinder-like profile.
- /opener - Generate icebreaker messages for conversations.
- /message - Manage and send messages.
- /date - Simulate dates with celebrities using AI-generated responses.


## Dependencies
 - Java: JDK 17+
 - TelegramBots API: Version 6.9.7.1 for interacting with the Telegram API.
 - OpenAI ChatGPT API: chatgpt library version 5.1.1 for integrating with OpenAI.
 
## OpenAI Integration
The core feature of GTP_fun_bot is its integration with OpenAI’s ChatGPT API, enabling sophisticated, intelligent conversations that feel natural and context-aware. This integration provides a flexible interface for using GPT-3 and GPT-4 models, which are state-of-the-art AI language models that can generate human-like text based on the input they receive.


## How OpenAI Integration Works
 1. User Prompts: When a user sends a message or request through Telegram, it is captured and prepared as a prompt for OpenAI’s GPT models. The bot formats the message, providing context when necessary, and sends it to OpenAI's API.

 2. Contextual Responses: Using the GPT models (GPT-3 or GPT-4), the bot generates a highly contextualized response. GPT’s ability to remember previous messages within the conversation allows for coherent, ongoing dialogue without losing track of the conversation's flow.

 3. Message History: Each session maintains a message history, which is critical for maintaining context during a long conversation. This ensures that the bot can give more coherent responses over time and handle continuous dialogues effectively.

## Flexible Usage of GPT

In GPT mode, the user can directly ask any question, and the bot will generate a response via OpenAI.
In PROFILE mode, the bot will use the GPT model to help generate detailed user profiles based on the input given by the user.
In DATE mode, the bot uses GPT to simulate dates with celebrities or fictional characters, generating humorous or engaging dialogue.
The bot can also manage complex interactions like creating opener messages or responding with pre-built templates enhanced by OpenAI responses.
Temperature and Model Tuning: OpenAI’s parameters, like temperature (which controls how creative or deterministic the output is), can be adjusted within the bot to suit different modes. For example, in the casual DATE mode, the bot can be configured to generate more creative or humorous responses, while in GPT mode, it can aim for more informative and direct answers.

## Advantages of Using OpenAI in GTP_fun_bot

- **Human-like Interaction**: The AI-generated responses make interactions feel more human-like and engaging. Whether simulating a conversation, generating creative openers, or answering questions, the responses are highly relevant.
- **Adaptive Dialogue**: By leveraging ChatGPT’s memory capabilities, the bot can provide more personalized and adaptive responses. This is especially useful when creating detailed Tinder-like profiles or engaging in long conversations.
- **Scalable AI**: The bot can easily switch between different dialogue modes while maintaining intelligent conversations using OpenAI’s API, making it scalable for more advanced use cases in the future.

## Project Structure:

- **ChatGPTService**: Manages interaction with the OpenAI API.
- **MultiSessionTelegramBot**: The main bot class that handles Telegram message updates and sessions.
- **DialogMode**: Enum to manage different dialogue modes within the bot.
- **UserInfo**: A data class used to store user profile information.
- **GTP_fun_bot**: The main application class that ties everything together.
- 
# GTP_fun_bot

GTP_fun_bot is a multi-functional Telegram bot that uses OpenAI's ChatGPT for interactive conversations and content generation related to Tinder profiles, messages, and more. This bot can engage in dialogues, create profiles, send messages, and interact with users using the powerful OpenAI models.

## Demo

Here are some screenshots demonstrating GTP_fun_bot in action:

### 1. Main Menu
![Main Menu Screenshot](https://i.ibb.co/nkGF23y/1.jpg)
![Main Menu Screenshot2](https://i.ibb.co/2nQ56qf/2.jpg)
![Main Menu Screenshot3](https://i.ibb.co/H4G8wdq/3.jpg)


### 2. `/gpt` - Ask a Question Directly to ChatGPT
![ChatGPT Interaction Screenshot](https://i.ibb.co/yW201Q9/13.jpg)

This screenshot shows the bot's interaction with OpenAI's ChatGPT, where the user asks a question and receives a contextual response generated by GPT.

---

### 3. `/profile` - Create or Edit Your Tinder-like Profile
![Profile Creation Screenshot](https://i.ibb.co/0GbWN5Z/4.jpg)

The bot guides users through creating or editing their profile by asking a series of questions. Users can input details like their name, age, hobbies, etc.

---

### 4. `/opener` - Generate Icebreaker Messages for Conversations
![Icebreaker Messages Screenshot](https://i.ibb.co/GQyqmtJ/5.jpg)

The bot generates creative icebreaker messages to help users start conversations, which can be useful in various social or dating situations.

---

### 5. `/message` - Manage and Send Messages
![Manage Messages Screenshot](https://i.ibb.co/ggL1Ldg/6.jpg)

In this screenshot, the bot allows the user to manage and send messages, including the use of inline buttons and multimedia support.

---

### 6. `/date` - Simulate Dates with Celebrities Using AI-generated Responses
![Date Simulation Screenshot](https://i.ibb.co/HpYRnv8/7.jpg)
![Date Simulation Screenshot1](https://i.ibb.co/McDSRfq/8.jpg)
![Date Simulation Screenshot2](https://i.ibb.co/VMnjd5P/9.jpg)


The date simulation feature allows users to engage in fun, AI-generated conversations with celebrities or fictional characters.


# Conclusion

GTP_fun_bot is more than just a chatbot—it's your personal AI companion for creative conversations, social interactions, and even playful date simulations with celebrities! Whether you're chatting with GPT, creating profiles, or generating quirky icebreakers, GTP_fun_bot brings fun and intelligence right to your Telegram. It's an exciting blend of AI-powered communication and entertainment, designed to keep users engaged and entertained.




 

