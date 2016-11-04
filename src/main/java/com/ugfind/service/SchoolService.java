package com.ugfind.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ugfind.model.School;
import com.ugfind.model.Schoolvisits;

@Service
public interface SchoolService {

	public abstract List<School> getSchoolList(String keyWord, String city);

	public abstract void addSchoolVisits(Integer schoolId);

	public abstract Schoolvisits selectSchoolVisitsBySchoolId(Integer schoolId);

}