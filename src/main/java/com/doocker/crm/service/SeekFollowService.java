package com.doocker.crm.service;

import java.util.HashMap;

import com.doocker.crm.po.Seekfollow;
import com.github.pagehelper.PageInfo;

public interface SeekFollowService {
	Integer updateSeekFollow(Seekfollow seekFollow);
	Integer deleteSeekFollow(Integer id);
	Integer insertSeekFollow(Seekfollow seekFollow);
	Seekfollow getSeekFollow(Integer id);
	PageInfo<HashMap> selectListByPage(String seekFollowName, Integer page, Integer rows);
}
