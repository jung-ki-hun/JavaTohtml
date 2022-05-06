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
    private List<String> beforeParseing = new ArrayList<>();
    private final int SIZE;
    public JavaParseingDataAddDb(List<String> afterParseing) {
        this.beforeParseing = beforeParseing;
        SIZE = beforeParseing.size();
        setAfterParseing();
    }

    //#todo #2 파싱진행
    private List<String[]> afterParseing = new ArrayList<>();
    void setAfterParseing(){
        int i =0;

        String [] temp;
        for(i=0;i<SIZE;i++) {
            temp = beforeParseing.get(i).split("`");
            afterParseing.add(temp);
        }
        findTag();
    }
    //#todo #3 알맞은 tag 바탕으로 Visitor들고옴
    private VistorListener vistorListener;
    void findTag(){
        for(int i= 0; i<SIZE;i++){
            for (int j = 0; j < afterParseing.get(i).length; j++) {
               String temp = afterParseing.get(i)[j];
               if(temp.equals("public")
                   ||temp.equals("private")
                   ||temp.equals("protected")
                   ||temp.equals("packeage")                                                                            ){
                   afterParseing.get(i)[j] = vistorListener.visit(new AccessTag(temp));
               }//todo fix 주석 하는 문장 전부 색칠하도록 수정하기..
                if(temp.equals("//")
                    ||temp.equals("/*")
                    ||temp.equals("*/")){
                    afterParseing.get(i)[j] = vistorListener.visit(new CommandTag(temp));
                }
                if(temp.equals("int")
                    ||temp.equals("double")
                    ||temp.equals("float")
                    ||temp.equals("class")
                    ||temp.equals("implements")
                    ||temp.equals("boolean")
                    ||temp.equals("interface")){
                    afterParseing.get(i)[j] = vistorListener.visit(new DataTypeTag(temp));
                }
                if(temp.equals("for")
                    ||temp.equals("new")
                    ||temp.equals("if")
                    ||temp.equals("import")
                    ||temp.equals("synchronized")){
                    afterParseing.get(i)[j] = vistorListener.visit(new ProcessingTag(temp));
                }//변환
            }
            saveDb(afterParseing.get(i));
        }
    }
    //#todo #4 db에 업로드하기
    JavaDataBaseSingleton javaDataBase = JavaDataBaseSingleton.getInstance();
    private void saveDb(String [] sql){
        StringBuilder temp = new StringBuilder();
//        for (int i = 0; i < sql.length; i++) {
//            temp.append(sql);
//        }
        temp.append(String.valueOf(sql).repeat(sql.length));
        javaDataBase.updateDataBase(temp.toString());
    }
}
