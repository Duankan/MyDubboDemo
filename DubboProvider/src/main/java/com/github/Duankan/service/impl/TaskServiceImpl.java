//package com.github.Duankan.service.impl;
//
//import com.github.Duankan.dao.TaskMapper;
//import com.github.Duankan.po.Task;
//import com.github.Duankan.service.ITask;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * @author duankang
// * @func task接口实现类
// * @desc
// */
//public class TaskServiceImpl implements ITask {
//    @Resource
//    TaskMapper taskMapper;
//
//    @Override
//    public void insertTask(Task tsk) {
//        taskMapper.insert(tsk);
//    }
//
//    @Override
//    public Task getTaskByTaskId(String taskId) {
//        return taskMapper.selectByPrimaryKey(taskId);
//    }
//
//    @Override
//    public void deleteTaskByTaskId(String taskId) {
//        taskMapper.deleteByPrimaryKey(taskId);
//    }
//
//    @Override
//    public List<Task> getTaskList() {
//        return taskMapper.getTaskList();
//    }
//
//    @Override
//    public void updataTask(Task tsk) {
//        taskMapper.updateByPrimaryKey(tsk);
//    }
//
//}
