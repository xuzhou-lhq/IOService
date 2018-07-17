package com.xz.Controller;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;

import com.google.gson.Gson;
import com.xz.dao.IOserverClient;
import com.xz.service.getTagNameServic;
import com.xz.websocket.SocketHandler;

import net.sf.json.JSONArray;

/**
 * @desp Socket控制器
 * @author liulichao@ruc.edu.cn
 * @date 2016-5-6
 *
 */
@Controller
public class SocketController{
  
  private static final Logger logger = LoggerFactory.getLogger(SocketController.class);
  
  @Autowired
  private SocketHandler socketHandler;
  @Autowired
  private getTagNameServic getTagNameService;
  
  @RequestMapping(value="/login")
  public String login(HttpSession session){
    logger.info("用户登录了建立连接啦");
    
    session.setAttribute("user", "liulichao");
    
    return "home";
  }

  @RequestMapping(value = "/message", method = RequestMethod.GET)
  public String sendMessage(){
    
    socketHandler.sendMessageToUser("liulichao", new TextMessage("这是一条测试的消息"));
    
    return "message";
  }
  
  @Scheduled(cron= "*/10 * * * * *")
  public void deleteAllTempClob(){
	  ArrayList<Vector> TagsName = new ArrayList<Vector>();
	  String jsonArray="null";
	  if(IOserverClient.funcIsConnect()==0)
	  {
		  TagsName=getTagNameService.getTagName();
		  //jsonArray= TagsName.toString(); 
		  //jsonArray = JSONArray.fromObject(TagsName).toString();
		  jsonArray = new Gson().toJson(TagsName);
	  }
	  else
		  IOserverClient.funcConnect("127.0.0.1", "12380");
	  //System.out.println(jsonArray);
	  socketHandler.sendMessageToUsers( new TextMessage(jsonArray));
  }

}