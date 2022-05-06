package com.nhnacademy.midterm.util;

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
    }
    //#todo #3 알맞은 tag 바탕으로 Visitor들고옴
    private VistorListener vistorListener;
    void findTag(){
        for(int i= 0; i<SIZE;i++){

        }
    }
    //#todo #4 db에 업로드하기
    {

    }

}
