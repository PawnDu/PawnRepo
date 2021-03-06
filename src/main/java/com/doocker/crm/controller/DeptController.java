package com.doocker.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doocker.crm.controller.commen.EasyUIResult;
import com.doocker.crm.po.Dept;
import com.doocker.crm.service.DeptService;
import com.github.pagehelper.PageInfo;
/**
<<<<<<< HEAD
 * 处理部门的控制器
 * @author Administrator  dulong two
=======

>>>>>>> branch 'master' of https://github.com/doockercom/crm.git
 */
@Controller
@RequestMapping("dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("get")
	//作用是返回的dept对象转化为json
	@ResponseBody
	public Dept getDept(Integer id){
		return deptService.getDept(id);
	}
	
	
	@RequestMapping("list")
	@ResponseBody
	public EasyUIResult listDept(
			//@RequestParam(value="deptname" 指的是表单中的name 
			//             ,required=false  这个参数是否必须,
			//				defaultValue="1" 此参数的默认值)
			@RequestParam(value="deptname",required=false)String deptName,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="3")Integer rows
			){
		PageInfo<Dept> list = new PageInfo<Dept>();
		try{
			list = deptService.selectListByPage(deptName,page,rows);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(list.getTotal(),list.getList(),true,"success");
		
	}
	@RequestMapping("del")
	@ResponseBody
	public EasyUIResult deleteDept(
			@RequestParam(value="id",required=true)Integer id
			){
		Integer ids = 0;
		try{
			ids = deptService.deleteDept(id);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("add")
	@ResponseBody
	public EasyUIResult addDept(Dept dept){
		dept.setId(null);
		Integer ids = 0;
		try{
			ids = deptService.insertDept(dept);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("update")
	@ResponseBody
	public EasyUIResult updateDept(
			@RequestParam(value="deptname",required=true)String deptName,
			@RequestParam(value="id",required=true)Integer id
			){
		Integer ids = 0;
		try{
			Dept dept = new Dept();
			dept.setId(id);
			dept.setDeptName(deptName);
			ids = deptService.updateDept(dept);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}

}
