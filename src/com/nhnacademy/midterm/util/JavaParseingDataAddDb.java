package com.nhnacademy.midterm.util;

import com.nhnacademy.midterm.domain.AccessTag;
import com.nhnacademy.midterm.domain.CommandTag;
import com.nhnacademy.midterm.domain.DataTypeTag;
import com.nhnacademy.midterm.domain.ProcessingTag;
import com.nhnacademy.midterm.visitor.VistorListener;
import java.util.ArrayList;
import java.util.List;

public class JavaParseingDataAddDb {

    //#todo #1 대상 코드 로드
    private List<String> beforeParseing;
    private final int SIZE;
    public JavaParseingDataAddDb(List<String> beforeParseing) {
        this.beforeParseing = beforeParseing;
        SIZE = beforeParseing.size();
        setAfterParsering();
    }

    //#todo #2 파싱진행
    private List<String[]> afterParseing = new ArrayList<>();
    void setAfterParsering(){
        int i =0;
        String [] temp;
        for(i=0;i<SIZE;i++) {
            temp = beforeParseing.get(i).split("`");
//            for (String str:temp){
//                System.out.println("ss : "+str);
//            }
            afterParseing.add(temp);
        }
        findTag();
    }
    //#todo #3 알맞은 tag 바탕으로 Visitor들고옴
    private VistorListener vistorListener;
    void findTag(){
        AccessTag at = new AccessTag();
        ProcessingTag pt = new ProcessingTag();
        CommandTag ct = new CommandTag();
        DataTypeTag dt = new DataTypeTag();
        for(int i= 0; i<SIZE;i++){
            for (int j = 0; j < afterParseing.get(i).length; j++) {
               String temp = afterParseing.get(i)[j];
               if(temp.contains("public")
                   ||temp.contains("private")
                   ||temp.contains("protected")
                   ||temp.contains("packeage")
                   ||temp.contains("static")
               ){
                   afterParseing.get(i)[j]=at.accpetTag(new VistorListener(temp));
               }//todo fix 주석 하는 문장 전부 색칠하도록 수정하기..
                if(temp.contains("//")
                    ||temp.contains("/*")
                    ||temp.contains("*/")){
                    afterParseing.get(i)[j] = ct.accpetTag(new VistorListener(temp));
                }
                if(temp.contains("int")
                    ||temp.contains("double")
                    ||temp.contains("float")
                    ||temp.contains("class")
                    ||temp.contains("implements")
                    ||temp.contains("boolean")
                    ||temp.contains("interface")
                    ||temp.contains("null")
                    ||temp.contains("false")
                    ||temp.contains("true")){

                    afterParseing.get(i)[j]= dt.accpetTag(new VistorListener(temp+" "));
                }
                if(temp.contains("for")
                    ||temp.contains("new")
                    ||temp.contains("if")
                    ||temp.contains("import")
                    ||temp.contains("synchronized")
                    ||temp.contains("while")
                    ||temp.contains("this")
                    ||temp.contains("return")){
                    afterParseing.get(i)[j]= pt.accpetTag(new VistorListener(temp));
                }//변환
            }
            System.out.println("dsfds : "+afterParseing.get(i));
            saveDb(afterParseing.get(i));
        }
    }
    //#todo #4 db에 업로드하기
    JavaDataBaseSingleton javaDataBase = JavaDataBaseSingleton.getInstance();
    private void saveDb(String [] sql){
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sql.length; i++) {
            temp.append(sql[i]);
        }temp.append("<br>");
       // temp.append(String.valueOf(sql).repeat(sql.length));
        javaDataBase.updateDataBase(temp.toString());
    }
}
