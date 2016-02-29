package com.kingnod.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kingnod.entity.Chat;
import com.kingnod.service.ChatService;

@Controller
@RequestMapping("chat")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	/**
	 * 进入聊天主页
	 * @param mv
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public ModelAndView tochat(ModelAndView mv,HttpServletRequest request,HttpServletResponse response){
		mv.addObject("userName",request.getRemoteHost());
		mv.addObject("sendId",(new Date()).getTime());
		mv.setViewName("view/chat/chatForm");
		return mv;
	}
	/**
	 * 保存聊天记录
	 * @param chat
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="create",method=RequestMethod.POST)
	@ResponseBody
	public String createchat(Chat chat,HttpServletRequest request,HttpServletResponse response){
		try {
			chatService.saveChat(chat);
			return "SUCCESS";
		} catch (Exception e) {
			return "FAIL";
		}
	}
	
}
