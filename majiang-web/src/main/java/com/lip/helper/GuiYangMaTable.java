package com.lip.helper;

import com.lip.model.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by Lip on 2016/8/22 0022.
 */
public class GuiYangMaTable extends MaTable{
    protected Player fightingPlayer;
    public GuiYangMaTable(int count) {
        super(count);
    }

    public GuiYangMaTable(boolean _wind,int count) {
        super(_wind,count);
    }
    @Override
    /**
     * 玩家打牌
     * @param player
     * @param item
     * @param action
     */
    public List<Action> play(Player player, MaItem item, Action action)
    {
        if(fightingPlayer==null&&item.equals(new MaItem(MaType.TIAO,MaValue.ONE)))
            fightingPlayer=player;
        return super.play(player, item, action);
    }
}
