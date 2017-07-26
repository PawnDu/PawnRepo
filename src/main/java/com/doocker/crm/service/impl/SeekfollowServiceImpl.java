package com.doocker.crm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doocker.crm.mapper.SeekfollowMapper;
import com.doocker.crm.po.Seekfollow;
import com.doocker.crm.service.SeekFollowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class SeekfollowServiceImpl implements SeekFollowService {
	
	
	@Autowired
	private SeekfollowMapper seekFollowMapper;
	
	@Override
	public Integer updateSeekFollow(Seekfollow seekFollow) {
		if(null == seekFollow.getFollowid()){
			return 0;
		}
		return seekFollowMapper.updateByPrimaryKey(seekFollow);
	}

	@Override
	public Integer deleteSeekFollow(Integer id) {
		return seekFollowMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer insertSeekFollow(Seekfollow seekFollow) {
		if( null != seekFollow.getFollowid()){
			return 0;
		}
		return seekFollowMapper.insert(seekFollow);
	}

	@Override
	public Seekfollow getSeekFollow(Integer id) {
	
		return seekFollowMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<HashMap> selectListByPage(String seekFollowName, Integer page, Integer rows) {
		//example通过SeekFollowExample来动态的增加修改查询条件
		//分页插件的使用
		PageHelper.startPage(page, rows);
		List<HashMap> selectByExample =null;
		if(null!=seekFollowName){
			 selectByExample = seekFollowMapper.followSelect("%"+seekFollowName+"%");

		}else{
			 selectByExample = seekFollowMapper.followSelect(null);

		}
		PageInfo<HashMap> info = new PageInfo(selectByExample);	
		return info;
		
	}

}
