package com.nhnacademy.midterm.util;

import org.jsoup.Jsoup;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class JavaFileUtil {
    private static final JavaFileUtil INSTANCE = new JavaFileUtil();

    public static JavaFileUtil getInstance(){
        return INSTANCE;
    }

    public List<String> read(String filePath) {
        List<String> lines = new ArrayList<>();
        try(InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ){
            String line="";
            while( (line = bufferedReader.readLine())!=null ){
               lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void createHtml(String htmlTemplatePath, String destHtmlPath, String body) {

        File file = new File(htmlTemplatePath);
        if(!file.exists()){
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        StringBuilder sb = new StringBuilder();
        try(InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(htmlTemplatePath));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ){
            String line="";
            while( (line = bufferedReader.readLine()) != null ){
                sb.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = sb.toString().replaceAll("###body###", body );
        result = Jsoup.parse(result).toString();
        try(PrintWriter printWriter = new PrintWriter(new FileOutputStream(destHtmlPath));){
           printWriter.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
