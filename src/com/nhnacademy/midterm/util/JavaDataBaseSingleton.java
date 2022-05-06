package com.nhnacademy.midterm.util;

public class JavaDataBaseSingleton {
    private static JavaDataBaseSingleton javaDataBaseSingleton;
    private JavaDataBaseSingleton(){}

    public static JavaDataBaseSingleton getInstance() {
        if(javaDataBaseSingleton == null) {
            javaDataBaseSingleton = new JavaDataBaseSingleton();
             }
             return javaDataBaseSingleton;

    }
    private StringBuilder dataBase =new StringBuilder();
    public String selectDataBase() {
        return dataBase.toString();
    }

    public void updateDataBase(String sqlData) {
        this.dataBase.append(sqlData);
    }


}
