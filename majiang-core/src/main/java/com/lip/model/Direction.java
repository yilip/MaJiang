package com.lip.model;

/**
 * Created by Lip on 2016/8/15 0015.
 */
public enum  Direction {
    EAST,SOUTH,WEST,NORTH;
    public static Direction get(int index)
    {
        switch (index)
        {
            case 1:return Direction.EAST;
            case 2:return Direction.SOUTH;
            case 3:return Direction.WEST;
            case 4:return Direction.NORTH;
            default:return null;
        }
    }
}
