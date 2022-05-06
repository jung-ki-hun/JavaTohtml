package com.nhnacademy.midterm.domain;

import com.nhnacademy.midterm.visitor.Visitor;
import java.util.HashMap;
import java.util.Map;

public class DataTypeTag {
    Map<String, String> accessTagMap = new HashMap<>();

    {
        accessTagMap.put("int", "<span style='color:bule'>int</span>");
        accessTagMap.put("double", "<span style='color:bule'>double</span>");
        accessTagMap.put("float", "<span style='color:bule'>float</span>");
        accessTagMap.put("class", "<span style='color:bule'>class</span>");
        accessTagMap.put("implements", "<span style='color:bule'>implements</span>");
        accessTagMap.put("boolean", "<span style='color:bule'>boolean</span>");
        accessTagMap.put("interface", "<span style='color:bule'>class</span>");
        accessTagMap.put("null","<span style='color:bule'>null</span>");
        accessTagMap.put("false", "<span style='color:bule'>false</span>");
        accessTagMap.put("true","<span style='color:bule'>true</span>");

    }

    private String tagValue;

    public DataTypeTag(String tagValue) {
        this.tagValue = this.accessTagMap.get(tagValue);
    }

    public DataTypeTag() {

    }

    public void setTagValue(String tagValue) {
        this.tagValue = this.accessTagMap.get(tagValue);
    }

    public String getTagValue() {
        return tagValue;
    }

    public String accpetTag(Visitor visit) {
        return visit.visit(this);
    }

}
