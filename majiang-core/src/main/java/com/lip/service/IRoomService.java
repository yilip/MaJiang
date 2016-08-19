package com.lip.service;

import com.lip.model.Direction;
import com.lip.model.MaTable;

import java.util.Map;

/**
 * Created by Lip on 2016/8/17 0017.
 */
public interface IRoomService {
    public MaTable getRoom(String tableId);
    public MaTable createRoom(String userId,int count);
    public Direction joinRoom(String userId, String tableId);
    public boolean start(String userId, String tableId);
}
