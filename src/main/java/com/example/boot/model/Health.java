package com.example.boot.model;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Health {
    String res;
    //Получен сектор Health
    public String getHealth(Message message) {
        //Сюда передаем дату рождения от пользователя
        LocalDate localDate = LocalDate.parse(message.getText());
        //Избавляемя от лишнего символа
        String replace = String.valueOf(localDate).replace("-", "");
        int num1 = Integer.parseInt(replace);

        //1 этап Суммируем все числа даты рождения. Для примера возьмем 1998-01-05: 1+9+9+8+0+1+0+5=33
        int sum;
        for (sum = 0; num1 > 0; num1 /= 10) {
            sum += num1 % 10;
        }
        //2 этап Еще раз суммируем числа с ответа первого этапа 3+3=6
        int num2 = sum;
        int sum2;
        for (sum2 = 0; num2 > 0; num2 /= 10) {
            sum2 += num2 % 10;
        }
       /*Получаем день рождение 05 и избавляемся от 0 так как в дальнейшем при умножении
        может дать 0 а это нам не нужно
       */
        String replace2 = String.valueOf(localDate.getDayOfMonth()).replace("0", "");
        /*3 этап Здесь берем число под индексом 0 тоесть 5 так как мы избавились от 0 даже если будет
         двух значное число мы всегда берем первое число
         */
        String replaceResult = String.valueOf(replace2.charAt(0));
        /*Этап 4 Вычитаем  число из первого этапа числа:
        33-(2*на первую цифру даты рождения тоесть 5 взятую из 3-го этапа)Например
        33-(2*5)=23
         */
        int re = Integer.parseInt(replaceResult);
        int sum3 = sum - 2 * re;
        int num = sum3;

        //Этап 5 суммируем числа между собой из4-го этапа 2+3=5
        int sum4;
        for (sum4 = 0; num > 0; num /= 10) {
            sum4 += num % 10;

        }
        // Этап 6 добавляем все полученное в лист
        List<Integer> list = new ArrayList();
        list.add(Integer.valueOf(replace));
        list.add(sum);
        list.add(sum2);
        list.add(sum3);
        list.add(sum4);
        //Этап 7 Убираем все лишнее что бы остались только числа но кроме 0
        String sector = list.toString().replace(",", "")
                .replace(" ", "").replace("[", "")
                .replace("]", "").replace("0", "");
        // В итоге получаем 199815336235, это числа для дальнейшего разделения на сектора

        //Удаляем все кроме 4
        String health = sector.replace("1", "")
                .replace("2", "")
                .replace("3", "")
                .replace("5", "")
                .replace("6", "")
                .replace("7", "")
                .replace("8", "")
                .replace("9", "");

        if (health.equals("4")) {
            res = "У вас сектор здоровья 4.\nИдеальный коэффициент сектора: хороший уровень здоровья, который позволяет быстро восстанавливаться после травм и болезней. " +
                    "У человека наблюдается умеренный уровень агрессии и врожденная способность к защите.";
            return res;
        } if (health.equals("44") || health.equals("444") || health.equals("4444") || health.equals("44444") || health.equals("444444")|| health.equals("4444444")|| health.equals("44444444")|| health.equals("444444444")) {
                res = "У вас сектор здоровья 44/444 и более.\n Обладатели большого количества четверок могут похвастаться красивым телом и лицом, хорошей кожей, физическим здоровьем и сильным голосом. " +
                        "Они выносливые и сильные, им на долгие годы удается сохранить молодость и стройность. Среди таких людей часто встречаются профессиональные " +
                        "спортсмены и долгожители (за счет уникального строения тела и способности организма к быстрому восстановлению)." +
                        "Однако помимо физического здоровья, внешней привлекательности и силы, наличие четверок говорит об агрессии." +
                        "Если обратиться к физиологии, то мы увидим точную взаимосвязь: сильный коэффициент – идеальная база для профессионального спорта. " +
                        "Ведь для результатов нужен тестостерон – основа агрессии человека. Часто при наличии сильного сектора «Здоровье» (44/444 и более) есть " +
                        "гормональные нарушения и, как следствие, проблемы с кожей, акне." +
                        "Все же я не хочу, чтобы вы воспринимали агрессию негативно. Такая энергия нам тоже нужна. Просто у неё есть своя миссия, которая может использоваться не по назначению. " +
                        "В этом случае она доставляет дискомфорт самому человеку и всем, кто его окружает." +
                        "При скоплении агрессии вы чувствуете жжение в груди и ощущения, схожие с симптомами отравления. " +
                        "На самом деле так и есть: если не найти возможность выпустить эту энергию, то будете страдать и вы, и окружающие." +
                        "Более того, при нереализованном секторе повышается риск развития серьезных психосоматических заболеваний (например, диабета, проблем с ЖКТ и сердцем, " +
                        "неврозов, психозов, тиков, припадков, онкологии).";
                return res;
            } else
                res = "У вас сектор здоровья пуст.\nОтсутствие четверок в «цифровом» портрете говорит об ослабленном здоровье и неспособности быстро восстанавливаться после травм и болезней. " +
                        "При пустом секторе бывают хронические недуги, врожденные заболевания (если при этом нет достаточно энергии), проблемы с внешностью " +
                        "(лишний вес, плохая кожа или волосы, слабое зрение или зубы). Именно поэтому человек должен постоянно контролировать состояние здоровья." +
                        "Особенность этого типа – отсутствие агрессии.";
            return res;
    }}