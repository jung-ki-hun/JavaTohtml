package com.nhnacademy.midterm.visitor;

import com.nhnacademy.midterm.domain.AccessTag;
import com.nhnacademy.midterm.domain.CommandTag;
import com.nhnacademy.midterm.domain.DataTypeTag;
import com.nhnacademy.midterm.domain.ProcessingTag;

public interface Visitor {
    String visit(AccessTag accessTag); //접근 지정자
    String visit(CommandTag CommandTag);//주석
    String visit(DataTypeTag DataTypeTag);//자료형
    String visit(ProcessingTag ProcessingTag);//처리문(if,for..)

}
