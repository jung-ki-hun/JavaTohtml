package com.nhnacademy.midterm.domain;

import com.nhnacademy.midterm.visitor.Visitor;
import java.util.HashMap;
import java.util.Map;

public class AccessTag implements Tag{
    //todo #1 접근지정자 종류 선택하기
    Map<String,String> accessTagMap = new HashMap<>();
    {
        accessTagMap.put("public","<span style='color:blue'>public</span>");//public <span style='color:blue'>public</span>
        accessTagMap.put("private","<span style='color:bule'>private</span>");
        accessTagMap.put("protected","<span style='color:bule'>protected</span>");
        accessTagMap.put("package","<span style='color:bule'>package</span>");

    }
    private String tagValue;
    //todo #2 원하는 테그 값을 리턴 받아옴
    public AccessTag(String tag) {
       this.tagValue = this.accessTagMap.get(tag);
    }

    public AccessTag() {

    }

    public String getTagValue() {
        return tagValue;
    }
    public void setTagValue(String tagValue) {
        this.tagValue = this.accessTagMap.get(tagValue);
    }
    //todo #3 방문자가 처리함
    @Override
    public String accpetTag(Visitor visit) {
        return visit.visit(this);
    }
}
