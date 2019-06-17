package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.dao.TypeOneMapper;
import com.springcloud.dao.TypeTwoMapper;
import com.springcloud.entity.TypeOne;
import com.springcloud.entity.TypeTwo;
import com.springcloud.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeOneMapper typeOneMapper;
	
	@Autowired
	private TypeTwoMapper typeTwoMapper;
	
	@Override
	public List<TypeOne> selectAll() {
		return this.typeOneMapper.selectAll();
	}

	@Override
	public List<TypeTwo> selectByTypeOneId(Integer typeOneId) {
		return this.typeTwoMapper.selectByTypeOneId(typeOneId);
	}

}

