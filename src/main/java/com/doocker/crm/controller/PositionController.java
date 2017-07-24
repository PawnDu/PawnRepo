package com.doocker.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doocker.crm.controller.commen.EasyUIResult;
import com.doocker.crm.po.Position;
import com.doocker.crm.service.PositionService;
import com.github.pagehelper.PageInfo;
/**
<<<<<<< HEAD
 * 处理部门的控制器
 * @author Administrator  dulong two
=======

>>>>>>> branch 'master' of https://github.com/doockercom/crm.git
 */
@Controller
@RequestMapping("position")
public class PositionController {
	
	@Autowired
	private PositionService positionService;
	
	@RequestMapping("get")
	//作用是返回的position对象转化为json
	@ResponseBody
	public Position getPosition(Integer id){
		return positionService.getPosition(id);
	}
	
	
	@RequestMapping("list")
	@ResponseBody
	public EasyUIResult listPosition(
			//@RequestParam(value="positionname" 指的是表单中的name 
			//             ,required=false  这个参数是否必须,
			//				defaultValue="1" 此参数的默认值)
			@RequestParam(value="positionname",required=false)String positionName,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="3")Integer rows
			){
		PageInfo<Position> list = new PageInfo<Position>();
		try{
			list = positionService.selectListByPage(positionName,page,rows);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(list.getTotal(),list.getList(),true,"success");
		
	}
	@RequestMapping("del")
	@ResponseBody
	public EasyUIResult deletePosition(
			@RequestParam(value="id",required=true)Integer id
			){
		Integer ids = 0;
		try{
			ids = positionService.deletePosition(id);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("add")
	@ResponseBody
	public EasyUIResult addPosition(Position position){
		position.setId(null);
		Integer ids = 0;
		try{
			ids = positionService.insertPosition(position);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("update")
	@ResponseBody
	public EasyUIResult updatePosition( Position position){ //直接根据Position进行修改，position中包含了 deptID  id  positionName
		Integer ids = 0;
		try{
			ids = positionService.updatePosition(position);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	//根据部门id 获取职位
	@RequestMapping("getPositionByDept")
	@ResponseBody
	public EasyUIResult getPositionByDept(@RequestParam(value="id",required=true)Integer deptId){
		List<Position> list = null;
		try{
			list = positionService.getPositionByDeptId(deptId);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,list,true,"success");		
	}
}
