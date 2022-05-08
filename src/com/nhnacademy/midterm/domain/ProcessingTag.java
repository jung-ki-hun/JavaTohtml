package com.nhnacademy.midterm.domain;

import com.nhnacademy.midterm.visitor.Visitor;
import java.util.HashMap;
import java.util.Map;

public class ProcessingTag implements Tag{
    Map<String,String> accessTagMap = new HashMap<>();
    {
        accessTagMap.put("for","<span style='color:blue'>for</span>");//public <span style='color:blue'>public</span>
        accessTagMap.put("new","<span style='color:bule'>new</span>");
        accessTagMap.put("if","<span style='color:bule'>if</span>");
        accessTagMap.put("import","<span style='color:bule'>import</span>");
        accessTagMap.put("synchronized","<span style='color:bule'>synchronized</span>");
        accessTagMap.put("while","<span style='color:bule'>while</span>");
        accessTagMap.put("this","<span style='color:bule'>this</span>");
        accessTagMap.put("return","<span style='color:bule'>return</span>");
    }
    private String tagValue;

    public ProcessingTag(String tagValue) {
        this.tagValue = this.accessTagMap.get(tagValue);
    }
    public ProcessingTag() {}
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
