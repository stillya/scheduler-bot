package org.omstu.bot.scheduler.services.bot.handlers.messsagehandlers;

import org.omstu.bot.scheduler.services.bot.implementation.HandlerEventType;
import org.omstu.bot.scheduler.services.bot.intefaces.MessageHandler;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class HelpMessageHandler implements MessageHandler {

    @Override
    public SendMessage handle(Message message) {
        String helpMenu = "-----------HELP-----------" + '\n' +
                "This bot will send you a notification about lecture 20 minutes before start" + '\n' +
                "--------HOW TO USE IT?-------" + '\n' +
                "1. SUBSCRIBE(Any register): subscribe , <Number of Group>/<Subgroup> " + '\n' +
                "2. UNSUBSCRIBE(Any register): unsubscribe" + '\n' + "3. HELP(Any register): help" + '\n' +
                "4. ISSUBSCRIBE(Any register): check if you are a subscriber" + '\n' + "--------EXAMPLES--------" +
                '\n' + "> subscribe,pin-201/2" + '\n' + "> unsubscribe " + '\n' + "> help" + '\n' + "> issubscribe" +
                '\n';

        return MessageBuilder.buildMessage(message.getChatId(), helpMenu);
    }

    @Override
    public HandlerEventType getType() {
        return HandlerEventType.HELP;
    }
}
