package org.omstu.bot.scheduler.services.bot.handlers;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerEventType;
import org.omstu.bot.scheduler.services.bot.implementation.HandlerType;
import org.omstu.bot.scheduler.services.bot.intefaces.MessageHandler;
import org.omstu.bot.scheduler.services.bot.intefaces.UpdateHandler;
import org.omstu.bot.scheduler.utils.MessageBuilder;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class MessageUpdateHandler implements UpdateHandler {

    private final List<MessageHandler> handlers;

    @Override
    public HandlerType getHandlerType() {
        return HandlerType.MESSAGE_HANDLER;
    }

    @Override
    public SendMessage handle(Update update) {
        try {
            // TODO: 08.09.2020 Make without checking "/start" string
            if (!update.hasMessage()) {
                return MessageBuilder.buildMessage(update.getEditedMessage().getFrom().getId().longValue(),
                        "Message is Empty!");
            }

            Message message = update.getMessage();
            String[] data = message.getText().split(",");

            if (!message.getText().equals("/start")) {
                HandlerEventType usersQueryType = HandlerEventType.valueOf(data[0].toUpperCase().trim());
                Optional<MessageHandler> messageHandler = handlers.stream().
                        filter(handler -> handler.getType().equals(usersQueryType)).findFirst();

                return messageHandler.map(handler -> handler.handle(message)).
                        orElse(MessageBuilder.buildMessage(message.getChatId(),
                                "I cannot process your request. Command isn't right. Please, read helper!"));
            } else {
                Optional<MessageHandler> queryHandler = handlers.stream().
                        filter(handler -> handler.getType().equals(HandlerEventType.HELP)).findFirst();
                return queryHandler.get().handle(message);
            }
        } catch (Exception e) {
            return MessageBuilder.buildMessage(update.getMessage().getChatId(), "I can't handle your message. Please, read helper!");
        }
    }
}
