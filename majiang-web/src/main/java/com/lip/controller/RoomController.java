package com.lip.controller;

import com.lip.helper.Constants;
import com.lip.helper.WebsocketEndPoint;
import com.lip.model.*;
import com.lip.service.IRoomService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lip on 2016/8/16 0016.
 */
@Controller
@RequestMapping("/room")
public class RoomController {
    @Resource
    private WebsocketEndPoint websocket;
    @Resource
    private IRoomService roomService;
    private final static String SUCCESS = "success";
    private final static String MSG = "msg";
    private  ExecutorService service;
    @PostConstruct
    private void  init()
    {
        service= Executors.newFixedThreadPool(4);
        service.execute(new Runnable() {
            public void run() {

            }
        });
    }
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> hello() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("hello", "world");
        return result;
    }

    /**
     * 打麻将开房
     *
     * @param userId
     * @param count
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> createRoom(String userId, Integer count, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            MaTable table = roomService.createRoom(userId, count);
            result.put(SUCCESS, true);
            result.put("direction", table.getDealerPlayer().getDirection());
            request.getSession().setAttribute(Constants.SESSION_USERNAME, userId);
            request.getSession().setAttribute(Constants.SESSION_TABLEID, table.getTableId());
            result.put("roomId", table.getTableId());
            result.put(MSG, "创建成功");
        } catch (Exception e) {
            result.put(SUCCESS, false);
            result.put(MSG, "创建失败");
        }
        return result;
    }

    /**
     * 加入到一个房间
     *
     * @param userId
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> joinRoom(String userId, String roomId, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            request.getSession().setAttribute(Constants.SESSION_USERNAME, userId);
            Direction direction = roomService.joinRoom(userId, roomId);
            if (direction != null) {
                result.put(SUCCESS, true);
                request.getSession().setAttribute(Constants.SESSION_TABLEID, roomId);
                result.put("direction", direction);
                result.put(MSG, "加入成功");
            } else {
                result.put(SUCCESS, false);
                result.put(MSG, "房间不存在");
            }
        } catch (Exception e) {
            result.put(SUCCESS, false);
            result.put(MSG, "加入失败");
        }
        return result;
    }

    /**
     * 开始游戏
     *
     * @return
     */
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> startGame(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String userId = request.getSession().getAttribute(Constants.SESSION_USERNAME).toString();
            String roomId = request.getSession().getAttribute(Constants.SESSION_TABLEID).toString();
            boolean success = roomService.start(userId, roomId);
            if (success) {
                result.put(SUCCESS, true);
                result.put(MSG, "开始成功");
            } else {
                result.put(SUCCESS, false);
                result.put(MSG, "开始失败");
            }
            startGame(roomId);
        } catch (Exception e) {
            result.put(SUCCESS, false);
            result.put(MSG, "开始失败");
        }
        return result;
    }


    /**
     * 玩家拿一张牌
     *
     * @return
     */
    @RequestMapping(value = "/getIn", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> getIn(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String userId = request.getSession().getAttribute(Constants.SESSION_USERNAME).toString();
            String roomId = request.getSession().getAttribute(Constants.SESSION_TABLEID).toString();
            MaTable table = roomService.getRoom(roomId);
            Player player=table.getPlayer(userId);
            result.put(SUCCESS, true);
            while (!table.isPlayerConfirm())
            {
                Thread.sleep(2*1000);
            }
            table.setPlayernextConfirm();//下一次确定
            result.put("actions", table.getIn(userId));//玩家拿一张牌
            result.put("newMa",player.getNewMaItem());
            result.put(MSG, "成功");
        } catch (Exception e) {
            result.put(SUCCESS, false);
            result.put(MSG, "失败");
        }
        return result;
    }
    /**
     * 玩家打出一张牌
     *
     * @return
     */
    @RequestMapping(value = "/playOut", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> playOut(HttpServletRequest request,Integer type,Integer value) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String userId = request.getSession().getAttribute(Constants.SESSION_USERNAME).toString();
            String roomId = request.getSession().getAttribute(Constants.SESSION_TABLEID).toString();
            MaItem item=new MaItem(MaType.getMaType(type),MaValue.getMaValue(value));
            MaTable table = roomService.getRoom(roomId);
            Player player=table.getPlayer(userId);
            table.play(player,item,Action.PLAYING);
            //通知其他玩家
            sendActionToUser(roomId,userId,item);
            result.put(SUCCESS, true);
            result.put(MSG, "成功");
        } catch (Exception e) {
            result.put(SUCCESS, false);
            result.put(MSG, "失败");
        }
        return result;
    }
    /**
     * 向客户端推送消息
     */
    private void startGame(String roomId) {
        MaTable table = roomService.getRoom(roomId);
        for (Player player : table.players) {
            websocket.sendMessageToUser(player.getPid(), new TextMessage(player.getHandMaItems().toString()));
        }
    }

    /**
     * 当玩家打出一张牌后其他玩家的行为
     * @param roomId
     * @param userId
     * @param item
     */
    private void sendActionToUser(String roomId,String userId,MaItem item)
    {
        MaTable table = roomService.getRoom(roomId);
        for (Player player : table.players) {
            if(!player.getPid().equals(userId))
                websocket.sendMessageToUser(player.getPid(), new TextMessage(player.getActionsWhenOut(item).toString()));
        }
    }
}
