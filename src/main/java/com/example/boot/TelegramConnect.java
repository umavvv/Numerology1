package com.example.boot;

import com.example.boot.model.*;
import lombok.EqualsAndHashCode;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;

@EqualsAndHashCode
@Component
public class TelegramConnect extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Numerology002_bot";
    }

    @Override
    public String getBotToken() {
        return "6033958805:AAEzLUoCaGr8FVOUckPdywgG70_oEeH346c";
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
        // передаем все полученные классы в этот класс
        Personality personality = new Personality();
        Logic logic = new Logic();
        Interest interest = new Interest();
        Health health = new Health();
        Debt debt = new Debt();
        Energy energy = new Energy();
        Memory memory = new Memory();
        Luck luck = new Luck();
        Labour labour = new Labour();
        Message message = update.getMessage();
        // Сравниваем полученное сообщение с /start и отправляем текст с инструкцией
        if (message.getText().equals("/start")) {
            sendMsg(message, "Привет! \n Для того чтобы узнать свою матрицу введи \n год-месяц-рождение например: " +
                    "1998-01-05. \n Если нет ответа значит вы ввели неправильно дату попробуйте еще раз");
        }
        // сравниваем если сообщение имеет текст то выдаем готовые сектора тоесть методы из определленого класса
        if (message != null && message.hasText()) {
            sendMsg(message, personality.getPersonality(message));
            sendMsg(message, logic.getLogic(message));
            sendMsg(message, interest.getInterest(message));
            sendMsg(message, health.getHealth(message));
            sendMsg(message, debt.getDebt(message));
            sendMsg(message, energy.getEnergy(message));
            sendMsg(message, memory.getMemory(message));
            sendMsg(message, luck.getLuck(message));
            sendMsg(message, labour.getLabour(message));

            // При возникновении ошибки будет ничего так как на данный момент не получилось сделать проверку
            // является ли сообщение форматом  LocalDate
        } else System.out.println("");
    }}