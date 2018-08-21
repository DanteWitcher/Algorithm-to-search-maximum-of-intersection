package com.application;

import java.io.*;
import java.lang.*;
import java.util.*;

class Structure
{
    int time;
    String behavior;

    public int getTime() {
        return time;
    }

    public String getBehavior() {
        return behavior;
    }

    Structure(int time, String behavior) {
        this.time = time;
        this.behavior = behavior;
    }
}

class SortedByBehavior implements Comparator<Structure> {

    public int compare(Structure obj1, Structure obj2) {

        return obj1.getBehavior().compareTo(obj2.getBehavior());
    }
}

class SortedByTime implements Comparator<Structure> {

    public int compare(Structure obj1, Structure obj2) {

        int time1 = obj1.getTime();
        int time2 = obj2.getTime();

        if(time1 > time2) {
            return 1;
        }
        else if(time1 < time2) {
            return -1;
        }
        else {
            return 0;
        }
    }
}

public class Application {

    static String path = "src/main/java/com/application/input.txt";

    public static int calcMin(String hour, String min) {
        int hourN = Integer.parseInt(hour);
        int minN = Integer.parseInt(min);
        return (hourN * 60 + minN);
    }

    public static int findAmount(ArrayList<Structure> obj) {
        int amount1 = 0;
        int amount2 = 0;
        int begin = 0;
        int end = 0;

        for(Structure item : obj) {
            amount2 = item.getBehavior() == "in" ? amount2 + 1 : amount2 - 1;

            if(amount2 > amount1) {
                begin = item.getTime();
                end = -1;
                amount1 = amount2;
            }
            if((amount1 < amount2) && (end == -1)) {
                end = item.getTime();
            }
        }
        return amount1;
    }

    public static int solution(String path) {

        int answer = 0;

        try{
            FileInputStream fstream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            String[] divideTimeArr;

            ArrayList<Structure> box = new ArrayList<>();

            while ((strLine = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(strLine, " ");
                String[] divideLineArr = new String[stk.countTokens()];

                for (int i = 0; i < divideLineArr.length; i++) {

                    divideLineArr[i] = stk.nextToken();
                    StringTokenizer stkT = new StringTokenizer(divideLineArr[i], ":");

                    divideTimeArr = new String[stkT.countTokens()];

                    for (int j = 0; j < divideTimeArr.length; j++) {
                        divideTimeArr[j] = stkT.nextToken();
                    }

                    // преобразовать в int и перевести время в минуты
                    int time = calcMin(divideTimeArr[0], divideTimeArr[1]);

                    // обозначить приход(уход) и выполнить пушинг значений
                    if (i == 0) {
                        box.add(new Structure(time, "in"));
                        //System.out.println(obj.time + ", " + obj.behavior);
                    }

                    if (i == 1) {
                        box.add(new Structure(time, "out"));

                    }
                }
            }
            // Отсортируем по времени, потом по приходу(уходу)
            Collections.sort(box, new SortedByTime().thenComparing(new SortedByBehavior()));
            //Show sorted arrayList to make sure in right
//            for(Structure e : box) {
//                System.out.println("Behavior: " + e.behavior + ", Time: " + e.time);
//            }

            // algorithm
            answer = findAmount(box);

            System.out.println("Answer is(amount of people, who were at th same time) : " + answer);
            br.close();


        }
        catch (IOException e){
            System.out.println("Ошибка " + e.getMessage());
        }
        return answer;
    }

    public static void main(String[] args) {
        solution(path);
    }
}
