package com.doocker.crm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doocker.crm.mapper.StudentseekMapper;
import com.doocker.crm.po.Studentseek;
import com.doocker.crm.service.StudentSeekService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class StudentseekServiceImpl implements StudentSeekService {
	
	
	@Autowired
	private StudentseekMapper studentSeekMapper;
	
	@Override
	public Integer updateStudentSeek(Studentseek studentSeek) {
		if(null == studentSeek.getSeekid()){
			return 0;
		}
		return studentSeekMapper.updateByPrimaryKey(studentSeek);
	}

	@Override
	public Integer deleteStudentSeek(Integer id) {
		return studentSeekMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer insertStudentSeek(Studentseek studentSeek) {
		if( null != studentSeek.getSeekid()){
			return 0;
		}
		return studentSeekMapper.insert(studentSeek);
	}

	@Override
	public Studentseek getStudentSeek(Integer id) {
	
		return studentSeekMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<HashMap> selectListByPage(String studentSeekName, Integer page, Integer rows) {
		//example通过StudentSeekExample来动态的增加修改查询条件
		//分页插件的使用
		PageHelper.startPage(page, rows);
		List<HashMap> selectByExample =null;
		if(null!=studentSeekName){
			 selectByExample = studentSeekMapper.seekSelect("%"+studentSeekName+"%");

		}else{
			 selectByExample = studentSeekMapper.seekSelect(null);

		}
		PageInfo<HashMap> info = new PageInfo(selectByExample);	
		return info;
		
	}

}
