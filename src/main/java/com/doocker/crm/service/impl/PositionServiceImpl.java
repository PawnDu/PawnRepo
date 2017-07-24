package com.doocker.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doocker.crm.mapper.DeptMapper;
import com.doocker.crm.mapper.PositionMapper;
import com.doocker.crm.po.Dept;
import com.doocker.crm.po.Position;
import com.doocker.crm.po.PositionExample;
import com.doocker.crm.po.PositionExample.Criteria;
import com.doocker.crm.service.PositionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class PositionServiceImpl implements PositionService {
	
	
	@Autowired
	private PositionMapper positionMapper;
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public Integer updatePosition(Position position) {		
		if(null == position.getId()){
			return 0;
		}
		/**
		 * 网页传递参数只传递部门id，不会传递部门名称再修改
		 */
		Integer deptId = 	position.getDeptId();
		Dept dept = deptMapper.selectByPrimaryKey(deptId);
		position.setDeptName(dept.getDeptName());
		return positionMapper.updateByPrimaryKey(position);
	}

	@Override
	public Integer deletePosition(Integer id) {
		return positionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer insertPosition(Position position) {
		if( null != position.getId()){
			return 0;
		}
		/**
		 * 网页传递参数只传递部门id，不会传递部门名称再插入
		 */
		Integer deptId = 	position.getDeptId();
		Dept dept = deptMapper.selectByPrimaryKey(deptId);
		position.setDeptName(dept.getDeptName());
		return positionMapper.insert(position);
	}

	@Override
	public Position getPosition(Integer id) {
		return positionMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Position> selectListByPage(String positionName, Integer page, Integer rows) {
		//example通过PositionExample来动态的增加修改查询条件
		PositionExample example = new PositionExample();
		if(null != positionName){
			Criteria createCriteria = example.createCriteria();
			createCriteria.andPositionNameLike("%"+positionName+"%");
		}
		//分页插件的使用
		PageHelper.startPage(page, rows);
		
		List<Position> selectByExample = positionMapper.selectByExample(example);
		
		PageInfo<Position> info = new PageInfo(selectByExample);
		return info;
	}

	@Override
	public List<Position> getPositionByDeptId(Integer deptId) {
		PositionExample example = new PositionExample();
		Criteria createCriteria = example.createCriteria(); 
		createCriteria.andDeptIdEqualTo(deptId);
		List<Position> selectByExample = positionMapper.selectByExample(example);
		return selectByExample;
	}




}
