package own.stu.spring.ioc.applicationContext.eventPublish.event;

import java.util.EventObject;

/**
 * 自定义事件类型
 * Created by dell on 2017/6/5.
 */
public class MethodExecutionEvent extends EventObject {

    private static final long serialVersionUID = -71960369269303337L;

    private String methodName;

    public MethodExecutionEvent(Object source) {
        super(source);
    }

    public MethodExecutionEvent(Object source, String methodName) {
        super(source);
        this.methodName = methodName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
