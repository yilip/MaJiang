package com.lip.model;

import java.util.Map;

/**
 * Created by Lip on 2016/8/15 0015.
 */
public class MaItem {
    private MaType type;
    private MaValue value;

    public MaItem(MaType _type) {
        this.type = _type;
    }

    public MaItem(MaType _type, MaValue _value) {
        if (_type == null) {
            throw new NullPointerException("麻将类型不能为空");
        }
        if (_value == null) {
            if (_type == MaType.TIAO || _type == MaType.TONG || _type == MaType.WAN) {
                throw new NullPointerException("'" + _type.getValue() + "'值不能为空");
            } else {
                this.type = _type;
            }
        } else {
            if (_type == MaType.TIAO || _type == MaType.TONG || _type == MaType.WAN) {
                this.type = _type;
                this.value = _value;
            } else {
                throw new IllegalArgumentException("'" + _type.getValue() + "'不存在值");
            }
        }
    }

    public MaType getType() {
        return type;
    }



    public MaValue getValue() {
        return value;
    }

    public int getPos()
    {
        if (this.value != null) {
            return type.getIndex()*10+value.getIndex();
        } else {
            return type.getIndex()*10;
        }
    }
    @Override
    public String toString() {
        if (this.value != null) {
            return value.getValue() + type.getValue();
        } else {
            return type.getValue();
        }
    }
    @Override
    public boolean equals(Object item)
    {
        if(!(item instanceof MaItem))
            return false;
        item=(MaItem)item;
        if(((MaItem) item).getType()==this.type&&((MaItem) item).getValue()==this.getValue())
            return true;
        return false;
    }

    public static void main(String[] args) {
        MaItem item1=new MaItem(MaType.WAN,MaValue.EIGHT);
        System.out.println(item1);
        MaItem item2=new MaItem(MaType.WAN,MaValue.EIGHT);
        System.out.println(item2);
        System.out.println(item1.equals(item2));
    }
}
