package com.nhnacademy.midterm.domain;

import com.nhnacademy.midterm.visitor.Visitor;
import java.util.HashMap;
import java.util.Map;

public class CommandTag implements Tag {
    Map<String,String> accessTagMap = new HashMap<>();
    {
        accessTagMap.put("//","<span style='color:green'>");
        accessTagMap.put("/*","<span style='color:green'>");
        accessTagMap.put("*/","</span>");
    }
    private String tagValue;
    public CommandTag(String tagValue){this.tagValue=this.accessTagMap.get(tagValue);}

    public CommandTag() {

    }

    public String getTagValue() {
        return tagValue;
    }
    public void setTagValue(String tagValue) {
        this.tagValue = this.accessTagMap.get(tagValue);
    }
    @Override
    public String accpetTag(Visitor visit) {
        return visit.visit(this);
    }
}
