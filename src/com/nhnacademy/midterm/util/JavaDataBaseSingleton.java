package com.nhnacademy.midterm.util;

public class JavaDataBaseSingleton {
    private static JavaDataBaseSingleton javaDataBaseSingleton;
    private JavaDataBaseSingleton(){}

    public static JavaDataBaseSingleton getInstance() {
        if(javaDataBaseSingleton == null) { // 1번 : 쓰레드가 동시 접근시 문제
            javaDataBaseSingleton = new JavaDataBaseSingleton(); // 2번 : 쓰레드가 동시 접근시 인스턴스 여러번 생성
             }
             return javaDataBaseSingleton;

    }

    public String selectDataBase() {
        return dataBase.toString();
    }

    public void updateDataBase(String sqlData) {
        this.dataBase.append(sqlData);
    }

    private StringBuilder dataBase;
}
