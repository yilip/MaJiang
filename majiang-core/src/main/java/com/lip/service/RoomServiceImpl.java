package com.lip.service;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lip.model.Direction;
import com.lip.model.MaItem;
import com.lip.model.MaTable;
import com.lip.model.Player;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lip on 2016/8/17 0017.
 */
@Service
public class RoomServiceImpl implements IRoomService {

    private LoadingCache<String, MaTable> cache= com.google.common.cache.CacheBuilder
            .newBuilder().expireAfterAccess(3, TimeUnit.HOURS).weakValues()
            .build(new CacheLoader<String, MaTable>() {
                @Override
                public MaTable load(String key) throws Exception {
                    //TODO get table from db
                    return null;
                }
            });

    public MaTable getRoom(String tableId) {
        try {
            return cache.get(tableId);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 打麻将开房
     * @param userId
     * @return
     */
    public MaTable createRoom(String userId,int count) {
        MaTable table=new MaTable(false,count);
        //庄家
        Player player=new Player(userId);
        player.setDirection( Direction.EAST);
        player.setPlayingOut(true);
        player.isDealer=true;
        table.players.add(player);
        table.setDealerPlayer(player);
        cache.put(table.getTableId(),table);
        return table;
    }

    public synchronized Direction joinRoom(String userId, String tableId) {
        try {
            MaTable table=cache.get(tableId);
            if(table!=null)
            {
                Player player=new Player(userId);
                table.players.add(player);
                return Direction.get(table.players.size());
            }
        } catch (ExecutionException e) {
        }
        return null;
    }

    public boolean start(String userId, String tableId) {
        try {
            MaTable table=cache.get(tableId);
            //庄家开始游戏
            if(table.getDealerPlayer().getPid().equals(userId))
            {
                table.initMaItems();//洗牌
                table.initPlayerMaItems();//拿牌
                return true;
            }
        }catch (Exception e)
        {

        }
        return false;
    }
}
