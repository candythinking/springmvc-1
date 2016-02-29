package com.kingnod.tool;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.kingnod.entity.CommenEntity;
import com.kingnod.entity.User;

public interface SqlDao extends Repository<CommenEntity,Long>{
	/**
	 * 查询原生sql查询
	 * @param sql
	 * @return
	 */
	public List<Object[]> findAll(String sql);
	/**
	 * 根据主键查询
	 * @param Id
	 * @return
	 */
	public User findOne(Long Id);
}
