package com.lip.model;

/**
 * Created by Lip on 2016/8/15 0015.
 */
public enum  Action {
    IN("拿"),TING("听"),HU("胡"),ANGANG("暗杠"),HUITOUGANG("自杠"),GANG("杠"),PENG("碰"),PASS("过"),EAT("吃"),PLAYING("打");
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private Action(String _value)
    {
        this.value=_value;
    }
    public static void main(String[] args) {
    }
}
