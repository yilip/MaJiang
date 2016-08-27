package com.lip.helper;

import com.lip.model.*;

import java.util.List;

/**
 * Created by Lip on 2016/8/22 0022.
 */
public class GuiYangScore {
    private GuiYangMaTable table;
    public GuiYangScore(GuiYangMaTable _table)
    {
        this.table=_table;
    }

    /**
     * 翻鸡
     * @param item
     */
    public void handleTable(MaItem item)
    {
        for(Player player:table.players)
        {
            player.scoreMap.clear();
            if(player.isWaiting())//玩家已经听牌，可以算鸡
            {
                int num=player.getMaItemNum(new MaItem(MaType.TIAO, MaValue.ONE));
                if(player==table.fightingPlayer)//冲锋鸡
                {

                    if(num>0)
                    {
                        player.scoreMap.put("fighting",3);
                        player.scoreMap.put("ji",num-1);
                    }
                }else {
                    if(num>0)
                        player.scoreMap.put("ji",num);
                }

            }else {

            }
        }
    }

}
