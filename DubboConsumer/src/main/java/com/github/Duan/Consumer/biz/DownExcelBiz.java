package com.github.Duan.Consumer.biz;

import com.github.Duankan.po.Task;
import com.github.Duankan.service.ITask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author duankang
 * @func 下载wxcel
 * @desc 模拟下载excel
 */
@Service
public class DownExcelBiz {
    private Logger logger = LoggerFactory.getLogger(DownExcelBiz.class);
    @Autowired
    ITask iTask;

    /**
     * @func 开始下载
     * @desc
     */
    @Async
    public void startTask(String taskId) {
        try {
            logger.info("DownExcelBiz-->开始异步下载!");
            Thread.sleep(1000 * 8);
            logger.info("DownExcelBiz-->异步下载完毕!");
            //修改任务的进度信息
            Task task = iTask.getTaskByTaskId(taskId);
            task.setProgress("100%");
            iTask.updataTask(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
