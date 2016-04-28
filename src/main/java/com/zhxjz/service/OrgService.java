package  com.zhxjz.service;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Org;

/**
 *
 * 
 * @author caozj
 * 
 */
public interface OrgService {

	void add(Org org);

	void update(Org org);

	void delete(int id);

	List<Org> listAll();

	int count();
	
	List<Org> page(Pager page);

	Org get(int id);
	
	void batchDelete(List<Integer> idList);

}
