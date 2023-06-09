package com.example.boot.model;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Energy {
    String res;

    // Получен сектор Energy
    public String getEnergy(Message message) {
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

        //Удаляем все кроме 2
        String energy = sector.replace("1", "")
                .replace("3", "")
                .replace("4", "")
                .replace("5", "")
                .replace("6", "")
                .replace("7", "")
                .replace("8", "")
                .replace("9", "");
        if (energy.equals("2")) {
            res = "У вас сектор энергия 2/пуст.\n Этот вариант означает дефицит энергии или времени. Вы не хотите лишнего общения " +
                    "и иногда просто не в силах ответить на звонок. Не проявляете сильных эмоций, откладываете дела на потом. " +
                    "Вы неэмоциональны, суетитесь, попадаете в состояние хаоса и спешки, ощущаете вялость, сколько бы ни отдыхали, " +
                    "выходные вас не наполняют.Я вас удивлю, если скажу, что мы все наделены одинаковым запасом сил. Однако коэффициент " +
                    "2/пуст показывает, как мы расходуем энергию и время. Чаще всего мы их тратим неверно, поэтому попадаем в состояние " +
                    "суеты и хронической усталости.";
            return res;
        }
        if (energy.equals("22") || energy.equals("222")) {
            res = "У вас сектор энергия 22/222.\n Идеальный запас энергии, когда есть силы для успеха, хороший импульс для развития и " +
                    "быстрая реакция на любые действия. Такой человек легок на подъем, ему присущи упорство, трудоспособность, желание долго " +
                    "и много общаться. Он способен на сильные эмоции (крик, смех), чувствует время и популярные течения, умеет " +
                    "концентрироваться на задачах.";
            return res;
        }
        if (energy.equals("2222") || energy.equals("22222") || energy.equals("222222") || energy.equals("2222222") || energy.equals("22222222") || energy.equals("222222222")) {
            res = "У вас сектор энергия 2222/и более. Это «профицит энергии», слишком большой запас времени и сил. Люди, имеющие в матрице такое количество двоек, " +
                    "похожи на титанов. Большие и громоздкие, они тяжело раскачиваются, однако обладают невероятной трудоспособностью и упорством, способны доводить " +
                    "до конца начатое. Они сильные доноры позитивной и негативной энергии." +
                    "Человек с «профицитом сил» так быстро справляется с задачей, что просто не знает, куда себя еще применить. Имея «вагон» времени, со стороны он" +
                    " кажется грузным, ленивым (не люблю этот термин). Такой человек больше напоминает гигантский склад, заваленный паллетами с энергоресурсами, " +
                    "которые просто портятся без дела и скорее отравляют жизнь, чем приносят пользу. Главным правилом в этом случае является эмоциональная и вербальная " +
                    "разгрузка. Чем больше человек общается, тем он сам легче и ему легче! Человек с сильным показателем (2222/22222/222222) способен «перекроить» " +
                    "свою матрицу и сделать любой слабый сектор мощным за счет упорства и физической выносливости.";
            return res;
        } else
            res = "У вас сектор энергия 2/пуст.\n Этот вариант означает дефицит энергии или времени. Вы не хотите лишнего общения " +
                    "и иногда просто не в силах ответить на звонок. Не проявляете сильных эмоций, откладываете дела на потом. " +
                    "Вы неэмоциональны, суетитесь, попадаете в состояние хаоса и спешки, ощущаете вялость, сколько бы ни отдыхали, " +
                    "выходные вас не наполняют.Я вас удивлю, если скажу, что мы все наделены одинаковым запасом сил. Однако коэффициент " +
                    "2/пуст показывает, как мы расходуем энергию и время. Чаще всего мы их тратим неверно, поэтому попадаем в состояние " +
                    "суеты и хронической усталости.";
        return res;
    }
}