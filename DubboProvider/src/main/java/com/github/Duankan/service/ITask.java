package com.github.Duankan.service;

import com.github.Duankan.po.Task;

import java.util.List;

/**
 * @author duankang
 * @func 异步任务接口
 * @desc
 */
public interface ITask {
    public void insertTask(Task tsk);//增加一条任务到数据库

    public Task getTaskByTaskId(String taskId);

    public void deleteTaskByTaskId(String taskId);

    public List<Task> getTaskList();

    public void updataTask(Task tsk);
}
