package ${packageName}.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ${packageName}.dao.${className}Dao;
import ${packageName}.framework.util.jdbc.Jdbc;
import ${packageName}.framework.util.jdbc.Pager;
import ${packageName}.model.${className};

/**
 * 
 * 
 * @author caozj
 *  
 */
@Repository
public class ${className}DaoImpl implements ${className}Dao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "${firstLower}";

	@Override
	public void add(${className} ${firstLower}) {
		String sql = "insert into " + table + "(${insertProperties}) values(${propertiesCount})";
		jdbc.updateForBoolean(sql, ${params});
	}

	@Override
	public void update(${className} ${firstLower}) {
		String sql = "update " + table + " set ${updateProperties} where id = ?  ";
		jdbc.updateForBoolean(sql,${params}, ${firstLower}.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<${className}> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, ${className}.class, page);
	}

	@Override
	public List<${className}> listAll(){
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, ${className}.class);
	}

	@Override
	public int count(){
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql); 
	}

	@Override
	public ${className} get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, ${className}.class, id);
	}

}
