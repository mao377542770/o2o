package com.ugfind.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ugfind.model.Schooldept;
import com.ugfind.model.Subscribe;

@Service
public interface DeptService {

	public abstract List<Schooldept> getDeptBySchoolId(Integer schoolId);

	public abstract boolean saveSubscribe(Subscribe subscribe);

}