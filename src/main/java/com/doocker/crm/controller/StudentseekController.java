package com.doocker.crm.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doocker.crm.controller.commen.EasyUIResult;
import com.doocker.crm.po.Studentseek;
import com.doocker.crm.service.StudentSeekService;
import com.github.pagehelper.PageInfo;
/**
<<<<<<< HEAD
 * 处理部门的控制器
 * @author Administrator  dulong two
=======

>>>>>>> branch 'master' of https://github.com/doockercom/crm.git
 */
@Controller
@RequestMapping("studentseek")
public class StudentseekController {
	
	@Autowired
	private StudentSeekService studentseekService;
	@RequestMapping("get")
	//作用是返回的studentseek对象转化为json
	@ResponseBody
	public Studentseek getStudentSeek(Integer id){
		return studentseekService.getStudentSeek(id);
	}
	
	
	@RequestMapping("list")
	@ResponseBody
	public EasyUIResult listStudentSeek(
			//@RequestParam(value="studentseekname" 指的是表单中的name 
			//             ,required=false  这个参数是否必须,
			//				defaultValue="1" 此参数的默认值)
			@RequestParam(value="studentseekname",required=false)String studentseekName,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="3")Integer rows
			){
		PageInfo<HashMap> list = new PageInfo<HashMap>();
		try{
			list = studentseekService.selectListByPage(studentseekName,page,rows);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(list.getTotal(),list.getList(),true,"success");
		
	}
	@RequestMapping("del")
	@ResponseBody
	public EasyUIResult deleteStudentSeek(
			@RequestParam(value="id",required=true)Integer id
			){
		Integer ids = 0;
		try{
			ids = studentseekService.deleteStudentSeek(id);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("add")
	@ResponseBody
	public EasyUIResult addStudentSeek(Studentseek studentseek){
		studentseek.setSeekid(null);
		Integer ids = 0;
		try{
			ids = studentseekService.insertStudentSeek(studentseek);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}
	@RequestMapping("update")
	@ResponseBody
	public EasyUIResult updateStudentSeek( Studentseek studentseek){ //直接根据StudentSeek进行修改，studentseek中包含了 deptID  id  studentseekName
		Integer ids = 0;
		try{
			ids = studentseekService.updateStudentSeek(studentseek);
		}catch(Exception e){
				return new EasyUIResult(0L,null,false,"server error");
		}
		return new EasyUIResult(0L,ids,true,"success");		
	}

}
