package com.doocker.crm.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doocker.crm.controller.commen.EasyUIResult;
import com.doocker.crm.po.Staff;
import com.doocker.crm.service.StaffService;
import com.github.pagehelper.PageInfo;
/**
<<<<<<< HEAD
 * 处理部门的控制器
 * @author Administrator  dulong two
=======

>>>>>>> branch 'master' of https://github.com/doockercom/crm.git
 */
@Controller
@RequestMapping("staff")
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	@RequestMapping("get")
	//作用是返回的staff对象转化为json
	@ResponseBody
	public Staff getStaff(Integer id){
		return staffService.getStaff(id);
	}
	
	
	@RequestMapping("list")
	@ResponseBody
	public EasyUIResult listStaff(
			//@RequestParam(value="staffname" 指的是表单中的name 
			//             ,required=false  这个参数是否必须,
			//				defaultValue="1" 此参数的默认值)
			@RequestParam(value="staffname",required=false)String staffName,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="3")Integer rows
			){
		PageInfo<HashMap> list = new PageInfo<HashMap>();
		try{
			list = staffService.selectListByPage(staffName,page,rows);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(list.getTotal(),list.getList(),true,"success");
		
	}
	@RequestMapping("del")
	@ResponseBody
	public EasyUIResult deleteStaff(
			@RequestParam(value="id",required=true)Integer id
			){
		Integer ids = 0;
		try{
			ids = staffService.deleteStaff(id);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("add")
	@ResponseBody
	public EasyUIResult addStaff(Staff staff){
		staff.setId(null);
		Integer ids = 0;
		try{
			ids = staffService.insertStaff(staff);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("update")
	@ResponseBody
	public EasyUIResult updateStaff( Staff staff){ //直接根据Staff进行修改，staff中包含了 deptID  id  staffName
		Integer ids = 0;
		try{
			ids = staffService.updateStaff(staff);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}

}
