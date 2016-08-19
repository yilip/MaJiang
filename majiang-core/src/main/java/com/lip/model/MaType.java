package com.lip.model;

/**
 * Created by Lip on 2016/8/15 0015.
 */
public enum MaType {
    TIAO(1,"条"), TONG(2,"筒"), WAN(3,"万"), DONG(4,"东"), NAN(5,"南"), XI(6,"西"), BEI(7,"北"), ZHONG(8,"中"), FA(9,"發"), BAI(10,"白");
    public final static int TYPE=9*3+7;
    private String value;
    private int index;

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    private MaType(int _index,String _value)
    {
        this.value=_value;
        this.index=_index;
    }
    public static  MaType getMaType(int i)
    {
        switch (i)
        {
            case 1:return MaType.TIAO;
            case 2:return MaType.TONG;
            case 3:return MaType.WAN;
            case 4:return MaType.DONG;
            case 5:return MaType.NAN;
            case 6:return MaType.XI;
            case 7:return MaType.BEI;
            case 8:return MaType.ZHONG;
            case 9:return MaType.FA;
            case 10:return MaType.BAI;
            default:return null;
        }
    }
    @Override
    public String toString()
    {
        return getValue();
    }
}
