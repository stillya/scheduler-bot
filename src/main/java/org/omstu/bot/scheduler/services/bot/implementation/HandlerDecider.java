package org.omstu.bot.scheduler.services.bot.implementation;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.omstu.bot.scheduler.services.bot.intefaces.UpdateHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@AllArgsConstructor
public class HandlerDecider {

    private final List<UpdateHandler> handlers;

    public Optional<UpdateHandler> decide(Update update) {
        if (update.hasCallbackQuery()) {
            return handlers.stream()
                    .filter(handler -> handler.getHandlerType().equals(HandlerType.CALLBACK_HANDLER))
                    .findFirst();
        }
        else {
            return handlers.stream()
                    .filter(handler -> handler.getHandlerType().equals(HandlerType.MESSAGE_HANDLER))
                    .findFirst();
        }
    }
}
