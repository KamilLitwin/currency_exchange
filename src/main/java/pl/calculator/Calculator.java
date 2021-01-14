package pl.calculator;


import pl.dao.Dao;

public class Calculator {
    public static double calculateValue (double value){
        return value * Dao.queryValue();
    }
}
