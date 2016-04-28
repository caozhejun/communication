package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.dao.ParticipationDao;
import com.zhxjz.dao.permission.UserDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Participation;
import com.zhxjz.model.permission.User;
import com.zhxjz.service.ParticipationService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class ParticipationServiceImpl implements ParticipationService {

	@Autowired
	private ParticipationDao participationDao;

	@Autowired
	private UserDao userDao;

	@Override
	public void add(Participation participation) {
		participationDao.add(participation);
	}

	@Override
	public void delete(int id) {
		participationDao.delete(id);
	}

	@Override
	public Participation get(int id) {
		return participationDao.get(id);
	}

	@Override
	public List<Participation> page(Pager page) {
		return participationDao.page(page);
	}

	@Override
	public int count() {
		return participationDao.count();
	}

	@Override
	public List<Participation> listAll() {
		return participationDao.listAll();
	}

	@Override
	public void update(Participation participation) {
		participationDao.update(participation);
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	public Participation get(int activeId, String userAccount) {
		return participationDao.get(activeId, userAccount);
	}

	@Override
	public List<Participation> page(Pager page, int activeId) {
		return dealParticipation(participationDao.page(page, activeId));
	}

	private List<Participation> dealParticipation(List<Participation> list) {
		for (Participation p : list) {
			User user = userDao.get(p.getUserAccount());
			if (user == null) {
				continue;
			}
			p.setEmail(user.getEmail());
			p.setName(user.getName());
			p.setFloorNo(user.getFloorNo());
			p.setUnitNo(user.getUnitNo());
			p.setRoomNo(user.getRoomNo());
			p.setIdNo(user.getIdNo());
			p.setTelNo(user.getTelNo());
		}
		return list;
	}

	@Override
	public List<Participation> list(int activeId) {
		return dealParticipation(participationDao.list(activeId));
	}
}
