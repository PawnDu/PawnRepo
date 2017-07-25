package com.doocker.crm.service;

import java.util.HashMap;

import com.doocker.crm.po.Studentseek;
import com.github.pagehelper.PageInfo;

public interface StudentSeekService {
	Integer updateStudentSeek(Studentseek studentSeek);
	Integer deleteStudentSeek(Integer id);
	Integer insertStudentSeek(Studentseek studentSeek);
	Studentseek getStudentSeek(Integer id);
	PageInfo<HashMap> selectListByPage(String studentSeekName, Integer page, Integer rows);
}
