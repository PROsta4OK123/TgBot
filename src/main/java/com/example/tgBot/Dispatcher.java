package com.example.tgBot;

import com.example.tgBot.handler.UserRequestHandler;
import com.example.tgBot.models.UserRequest;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Dispatcher {
    private final List<UserRequestHandler> userRequestHandlers;

    public Dispatcher(List<UserRequestHandler> handlers) {
        this.userRequestHandlers = handlers
                .stream()
                .sorted(Comparator
                        .comparing(UserRequestHandler::isGlobal)
                        .reversed())
                .collect(Collectors.toList());
    }
    public boolean dispatch(UserRequest request){
        for (UserRequestHandler handler : userRequestHandlers){
            if(handler.isApplicable(request)){
                handler.handle(request);
                return true;
            }
        }
        return false;
    }
}
