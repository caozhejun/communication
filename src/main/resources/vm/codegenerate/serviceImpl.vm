package ${packageName}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ${packageName}.dao.${className}Dao;
import ${packageName}.framework.util.jdbc.Pager;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

	@Autowired
	private ${className}Dao ${firstLower}Dao;

	@Override
	public void add(${className} ${firstLower}) {
		${firstLower}Dao.add(${firstLower});
	}

	@Override
	public void delete(int id) {
		${firstLower}Dao.delete(id);
	}

	@Override
	public ${className} get(int id) {
		return ${firstLower}Dao.get(id);
	}

	@Override
	public List<${className}> page(Pager page) {
		return ${firstLower}Dao.page(page);
	}

	@Override
	public int count() {
		return ${firstLower}Dao.count();
	}

	@Override
	public List<${className}> listAll() {
		return ${firstLower}Dao.listAll();
	}

	@Override
	public void update(${className} ${firstLower}) {
		${firstLower}Dao.update(${firstLower});
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
}
