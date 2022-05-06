package com.nhnacademy.midterm.visitor;

import com.nhnacademy.midterm.domain.AccessTag;
import com.nhnacademy.midterm.domain.CommandTag;
import com.nhnacademy.midterm.domain.DataTypeTag;
import com.nhnacademy.midterm.domain.ProcessingTag;

public class VistorListener implements Visitor{
    String doDefiningTag;

    public void setDoDefiningTag(String doDefiningTag) {
        this.doDefiningTag = doDefiningTag;
    }

    @Override
    public String visit(AccessTag accessTag) {
       accessTag.setTagValue(doDefiningTag);
        return accessTag.getTagValue();
    }

    @Override
    public String visit(CommandTag commandTag) {
        commandTag.setTagValue(doDefiningTag);
        return commandTag.getTagValue();
    }

    @Override
    public String visit(DataTypeTag dataTypeTag) {
        dataTypeTag.setTagValue(doDefiningTag);
        return dataTypeTag.getTagValue();
    }

    @Override
    public String visit(ProcessingTag processingTag) {
        processingTag.setTagValue(doDefiningTag);
        return processingTag.getTagValue();
    }
}
