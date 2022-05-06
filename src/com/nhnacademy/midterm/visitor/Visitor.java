package com.nhnacademy.midterm.visitor;

import com.nhnacademy.midterm.domain.AccessTag;

public interface Visitor {
    String visit(AccessTag accessTag);
}
