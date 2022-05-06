package com.nhnacademy.midterm;

import com.nhnacademy.midterm.util.JavaDataBaseSingleton;
import com.nhnacademy.midterm.util.JavaFileUtil;

import com.nhnacademy.midterm.util.JavaParseingDataAddDb;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String targetJavaFileName="ErrorManager";
        //application 실행되는 경로
        String basePath = System.getProperty("user.dir");
        String filePath1 = basePath + File.separator + "data" + File.separator + targetJavaFileName + ".java";
        List<String> lines = JavaFileUtil.getInstance().read(filePath1);
        List<String> linesToNbsp = new ArrayList<>();
        int n =0;
        for(String line : lines){
            //System.out.println("line:" + line);
           linesToNbsp.add(line.replace(" ","&nbsp;`"));
//            System.out.println("line2:"+linesToNbsp.get(n));
//            n++;
        }
        JavaParseingDataAddDb jpdab = new JavaParseingDataAddDb(linesToNbsp);
        //jpdab.setAfterParsering();
        StringBuilder sb  = new StringBuilder();
        /**Sample**********************************************/
        sb.append("<span style='color:blue'>public</span>");
        sb.append("&nbsp;<span style='color:blue'>class</span>");
        sb.append("&nbsp;Sample {<br> &nbsp;&nbsp;&nbsp;&nbsp; ");
        sb.append("<span style='color:blue'>int</span>");
        sb.append("i = 5; <br>");
        sb.append("}<br/>");
        /**Sample**********************************************/
        System.out.println(JavaDataBaseSingleton.getInstance().selectDataBase());
        String templateHtmlPath = basePath + File.separator +"data"+ File.separator+"template.html";
        String destHtmlPath = basePath + File.separator +"html"+ File.separator + targetJavaFileName + ".html";
        JavaFileUtil.getInstance().createHtml(templateHtmlPath,destHtmlPath, JavaDataBaseSingleton.getInstance()
            .selectDataBase());
       // JavaFileUtil.getInstance().createHtml(templateHtmlPath,destHtmlPath,sb.toString());

    }

}
