package com.project.datamodel;

public class Validation {
    private String field;
    private Object expected;
    private String operator;

    public Validation() {
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getExpected() {
        return expected;
    }

    public void setExpected(Object expected) {
        this.expected = expected;
    }

    public String getOperator(){
        return operator;
    }

    public void setOperator(String operator){
        this.operator = operator;
    }

}
