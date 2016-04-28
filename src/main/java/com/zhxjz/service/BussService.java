package com.zhxjz.service;

import java.util.List;
import java.util.Map;

import com.zhxjz.model.Buss;

public interface BussService {

	void add(Buss buss);

	void update(Buss buss);

	void delete(String name);

	Buss get(String name);

	List<Buss> listAll();

	Object execute(Map<String, String[]> params, String name);

	void batchDelete(List<String> nameList);
}
