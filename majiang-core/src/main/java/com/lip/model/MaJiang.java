package com.lip.model;

import java.util.*;

/**
 * Created by Lip on 2016/8/15 0015.
 */
public class MaJiang {
    private Dice[] dices = {new Dice(), new Dice()};
    private List<MaItem> maItems=new ArrayList<MaItem>();//麻将集合
    private Map<MaType, ArrayList<MaItem>> maMap = new LinkedHashMap<MaType, ArrayList<MaItem>>();
    private boolean hasWind = true;

    public MaJiang() {
        init();
    }

    public MaJiang(boolean _wind) {
        this.hasWind = _wind;
        init();
    }

    /**
     * 初始化麻将
     */
    private void init() {
        initNoraml();
        if (hasWind) {
            initWind();
        }
        initMaItems();
    }

    /**
     * 初始化整幅麻将
     */
    private void initMaItems()
    {
        for(Map.Entry<MaType,ArrayList<MaItem>>entry:maMap.entrySet())
        {
            ArrayList<MaItem>items=entry.getValue();
            for(MaItem item:items)
            {
                maItems.add(item);
            }
        }
    }
    /**
     * 得到整幅麻将
     */
    public  List<MaItem>getMaItems()
    {
        return maItems;
    }

    /**
     * 初始化条筒万
     */
    private void initNoraml() {
        initNoraml(MaType.TIAO);//初始化条
        initNoraml(MaType.TONG);//初始化筒
        initNoraml(MaType.WAN);//初始化万
    }

    /**
     * 初始化风
     */
    private void initWind() {
        initWind(MaType.DONG);//东
        initWind(MaType.NAN);//南
        initWind(MaType.XI);//西
        initWind(MaType.BEI);//北
        initWind(MaType.ZHONG);//中
        initWind(MaType.FA);//发
        initWind(MaType.BAI);//白
    }

    /**
     * 初始化条筒万中的一种
     *
     * @param type
     */
    private void initNoraml(MaType type) {
        ArrayList<MaItem> list = new ArrayList<MaItem>();
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                MaItem item = new MaItem(type, MaValue.getMaValue(i));
                list.add(item);
            }
        }
        maMap.put(type, list);
    }

    /**
     * 初始化风
     *
     * @param type
     */
    private void initWind(MaType type) {
        ArrayList<MaItem> list = new ArrayList<MaItem>();
        for (int j = 0; j < 4; j++) {
            MaItem item = new MaItem(type);
            list.add(item);
        }
        maMap.put(type, list);
    }

    /**
     * 摇骰子
     *
     * @return
     */
    public String dice() {
        dices[0].doDice();
        dices[1].doDice();
        return dices[0].getValue() + " " + dices[1].getValue();
    }

    public static void main(String[] args) {
        MaJiang maJiang = new MaJiang();
        List<MaItem>maItems=maJiang.getMaItems();
        for(MaItem item:maItems)
        {
            for(int i=0;i<4;i++) {
                System.out.print(item);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
