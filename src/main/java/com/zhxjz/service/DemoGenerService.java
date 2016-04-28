package  com.zhxjz.service;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.DemoGener;

/**
 *
 * 
 * @author caozj
 * 
 */
public interface DemoGenerService {

	void add(DemoGener demoGener);

	void update(DemoGener demoGener);

	void delete(int id);

	List<DemoGener> listAll();

	int count();
	
	List<DemoGener> page(Pager page);

	DemoGener get(int id);
	
	void batchDelete(List<Integer> idList);

}
