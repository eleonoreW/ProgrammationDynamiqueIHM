package com.example.eleonore.programmationdynamique;

public class Constante {

    public static int debitTotal;
    public static double elevAmont;
    public static double coeffPertes = 0.5 * Math.pow(10,-5);

    public static double hauteurChuteNette(int debit){
        return elevAmont - (0.003261 * debit + 137.4) - coeffPertes * debit * debit;
    }

}
