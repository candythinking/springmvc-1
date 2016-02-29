package com.kingnod.service;

import com.kingnod.entity.Task;

public interface TaskService {
	/**
	 * 保存任务实体
	 * @param task
	 * @return
	 */
	 public Task save(Task task);
}
