package com.zhxjz.dao;

import java.util.List;

import com.zhxjz.model.Buss;

public interface BussDao {

	void add(Buss buss);

	void update(Buss buss);

	void delele(String name);

	Buss get(String name);

	List<Buss> listAll();
}
