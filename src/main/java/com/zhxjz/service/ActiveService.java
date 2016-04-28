package  com.zhxjz.service;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Active;

/**
 *
 * 
 * @author caozj
 * 
 */
public interface ActiveService {

	void add(Active active);

	void update(Active active);

	void delete(int id);

	List<Active> listAll();

	int count();
	
	List<Active> page(Pager page);

	Active get(int id);
	
	void batchDelete(List<Integer> idList);

}
