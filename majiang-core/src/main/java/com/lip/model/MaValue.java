package com.lip.model;

/**
 * Created by Lip on 2016/8/15 0015.
 */
public enum  MaValue {
    ONE(1,"一"),TWO(2,"二"),THREE(3,"三"),FOUR(4,"四"),FIVE(5,"五"),SIX(6,"六"),SEVEN(7,"七"),EIGHT(8,"八"),NINE(9,"九");
    private MaValue(int _index,String _value)
    {
        this.value=_value;
        this.index=_index;
    }
    private String value;
    private int index;

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public static MaValue getMaValue(int i)
    {
        switch (i)
        {
            case 1:
                return MaValue.ONE;
            case 2:
                return MaValue.TWO;
            case 3:
                return MaValue.THREE;
            case 4:
                return MaValue.FOUR;
            case 5:
                return MaValue.FIVE;
            case 6:
                return MaValue.SIX;
            case 7:
                return MaValue.SEVEN;
            case 8:
                return MaValue.EIGHT;
            case 9:
                return MaValue.NINE;
            default:
                return null;
        }
    }
    @Override
    public String toString()
    {
        return getValue();
    }

    public static void main(String[] args) {

    }
}
