package pl.calculator;


import pl.dao.Dao;

public class Calculator {
    public static double calculateValue (double value){
        double result = value * Dao.queryValue().getValue();
        return result;
    }
}
