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
    private List<List<String>> afterParseing = new ArrayList<>();
    void setAfterParsering(){

        String [] temp;
        for(int i=0;i<SIZE;i++) {
            temp = beforeParseing.get(i).split("`"); //한줄 단위
            for (int j = 0; j < temp.length; j++) {
                afterParseing.get(i).add(String.valueOf(temp));
            }
            afterParseing.get(i).add("\n");

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
            for (int j = 0; j < afterParseing.get(i).size(); j++) {
               String  temp = afterParseing.get(i).get(j);
               // System.out.println(temp);
               if(temp.contains("public")
                   ||temp.contains("private")
                   ||temp.contains("protected")
                   ||temp.contains("packeage")
               ){
                   afterParseing.get(i).set(j, at.accpetTag(new VistorListener(temp)));
               }//todo fix 주석 하는 문장 전부 색칠하도록 수정하기..
                if(temp.contains("//")
                    ||temp.contains("/*")
                    ||temp.contains("*/")){
                    afterParseing.get(i).set(j, ct.accpetTag(new VistorListener(temp)));
                }
                if(temp.contains("int")
                    ||temp.contains("double")
                    ||temp.contains("float")
                    ||temp.contains("class")
                    ||temp.contains("implements")
                    ||temp.contains("boolean")
                    ||temp.contains("interface")){

                    afterParseing.get(i).set(j, dt.accpetTag(new VistorListener(temp)));
                }
                if(temp.contains("for")
                    ||temp.contains("new")
                    ||temp.contains("if")
                    ||temp.contains("import")
                    ||temp.contains("synchronized")){
                    afterParseing.get(i).set(j, pt.accpetTag(new VistorListener(temp)));
                }//변환
            }
           // System.out.println(afterParseing.get(i));
            saveDb(afterParseing.get(i));
        }
    }
    //#todo #4 db에 업로드하기
    JavaDataBaseSingleton javaDataBase = JavaDataBaseSingleton.getInstance();
    private void saveDb(List <String> sql){
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sql.size(); i++) {
           // System.out.println(sql[i]);
            temp.append(sql.get(i));
        }
        //temp.append(String.valueOf(sql).repeat(sql.length));
       // temp.append("\n");
        javaDataBase.updateDataBase(temp.toString());
    }
}
