package com.doocker.crm.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doocker.crm.controller.commen.EasyUIResult;
import com.doocker.crm.po.Seekfollow;
import com.doocker.crm.service.SeekFollowService;
import com.github.pagehelper.PageInfo;
/**
<<<<<<< HEAD
 * 处理部门的控制器
 * @author Administrator  dulong two
=======

>>>>>>> branch 'master' of https://github.com/doockercom/crm.git
 */
@Controller
@RequestMapping("seekfollow")
public class SeekfollowController {
	
	@Autowired
	private SeekFollowService seekfollowService;
	@RequestMapping("get")
	//作用是返回的seekfollow对象转化为json
	@ResponseBody
	public Seekfollow getSeekFollow(Integer id){
		return seekfollowService.getSeekFollow(id);
	}
	
	
	@RequestMapping("list")
	@ResponseBody
	public EasyUIResult listSeekFollow(
			//@RequestParam(value="seekfollowname" 指的是表单中的name 
			//             ,required=false  这个参数是否必须,
			//				defaultValue="1" 此参数的默认值)
			@RequestParam(value="seekfollowname",required=false)String seekfollowName,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="3")Integer rows
			){
		PageInfo<HashMap> list = new PageInfo<HashMap>();
		try{
			list = seekfollowService.selectListByPage(seekfollowName,page,rows);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(list.getTotal(),list.getList(),true,"success");
		
	}
	@RequestMapping("del")
	@ResponseBody
	public EasyUIResult deleteSeekFollow(
			@RequestParam(value="id",required=true)Integer id
			){
		Integer ids = 0;
		try{
			ids = seekfollowService.deleteSeekFollow(id);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("add")
	@ResponseBody
	public EasyUIResult addSeekFollow(Seekfollow seekfollow){
		seekfollow.setFollowid(null);
		Integer ids = 0;
		try{
			ids = seekfollowService.insertSeekFollow(seekfollow);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("update")
	@ResponseBody
	public EasyUIResult updateSeekFollow( Seekfollow seekfollow){ //直接根据SeekFollow进行修改，seekfollow中包含了 deptID  id  seekfollowName
		Integer ids = 0;
		try{
			ids = seekfollowService.updateSeekFollow(seekfollow);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}

}
