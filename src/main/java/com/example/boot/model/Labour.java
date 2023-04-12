package com.example.boot.model;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Labour {
    String res;

    // Получен сектор Labour
    public String getLabour(Message message) {
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

        //Удаляем все кроме 6
        String labour = sector.replace("1", "")
                .replace("2", "")
                .replace("3", "")
                .replace("4", "")
                .replace("5", "")
                .replace("7", "")
                .replace("8", "")
                .replace("9", "");

        if (labour.equals("6") || labour.equals("66")) {
            res = "У вас сектор труд 6 или 66.\n Наличие даже одной цифры в секторе дает большие возможности в различных областях. " +
                    "Самая сильная из них – привлечение внимания или известность.";
            return res;
        }
        if (labour.equals("666") || labour.equals("6666") || labour.equals("66666") || labour.equals("666666")|| labour.equals("6666666")|| labour.equals("66666666")|| labour.equals("666666666")) {
            res = "У вас сектор труд 666 или более.\n Этот коэффициент лишь усиливает все вышеперечисленные качества. Люди с сильным сектором" +
                    " «Труд» способны оказывать влияние на массы и на человека в целом, склонны к мошенничеству или оккультизму.";
            return res;
        } else
            res = "У вас сектор труд пуст.\n Отсутствие шестерок в матрице не несет никакой нагрузки, не создает негативных или позитивных проявлений. " +
                    "В этом случае профессиональная реализация человека связана не с ремесленным, а интеллектуальным трудом.";
        return res;
    }
}