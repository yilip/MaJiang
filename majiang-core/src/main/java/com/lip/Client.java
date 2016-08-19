package com.lip;

import com.lip.model.MaTable;
import com.lip.model.Player;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class Client
{
    public static void main(String[] args) {
        MaTable maTable = new MaTable(true,4);
        Player player1=new Player("10001");
        Player player2=new Player("10002");
        Player player3=new Player("10003");
        Player player4=new Player("10004");
        maTable.initMaItems();//洗麻将
        maTable.initPlay(player1, player2, player3, player4);
        //起手牌
        maTable.initPlayerMaItems();
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
        System.out.println(player4);
        System.out.println("剩余麻将:"+maTable.getLeftMaItems().size()+"张");
        System.out.println(maTable.getLeftMaItems());
        System.out.println(UUID.randomUUID().toString());
    }
}
