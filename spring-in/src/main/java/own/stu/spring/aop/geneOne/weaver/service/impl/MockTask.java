package own.stu.spring.aop.geneOne.weaver.service.impl;

import own.stu.spring.aop.geneOne.weaver.model.TaskExecutionContext;
import own.stu.spring.aop.geneOne.weaver.service.ITask;

/**
 * Created by dell on 2017/6/7.
 */
public class MockTask implements ITask {
    public void execute(TaskExecutionContext ctx) {
        System.out.println("task executed .");
    }
}
