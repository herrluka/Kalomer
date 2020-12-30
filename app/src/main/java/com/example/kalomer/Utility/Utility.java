package com.example.kalomer.Utility;

public class Utility {
    public static double calculateFormula(int age, String gendre, int weight, int height){
        if(gendre == "Muški"){
            return (66.47 + ( 13.75 * weight) + (5.003 * height) - (6.755 *age));
        } else {
            return  655.1 + ( 9.563 * weight) + ( 1.85 * height) - ( 4.676 *age);
        }
    }

    public static double calculateFat(double kcal) {
        return kcal / 30;
    }

    public static double calculateProteins(int weight) {
        return 1.2 * weight;
    }

    public static double calculateCarbs(double kcal) {
        return 0.6 * kcal;
    }
}
