package own.stu.spring.aop.geneOne.weaver.service;

import own.stu.spring.aop.geneOne.weaver.model.TaskExecutionContext;

/**
 * Created by dell on 2017/6/7.
 */
public interface ITask {
    void execute(TaskExecutionContext ctx);
}
