package own.stu.spring.ioc.applicationContext.eventPublish.eventPublisher;

import own.stu.spring.ioc.applicationContext.eventPublish.MethodExecutionStatus;
import own.stu.spring.ioc.applicationContext.eventPublish.event.MethodExecutionEvent;
import own.stu.spring.ioc.applicationContext.eventPublish.listener.MethodExecutionEventListener;
import own.stu.spring.ioc.applicationContext.eventPublish.listener.impl.SimpleMethodExecutionEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/6/5.
 */
public class MethodExecutionEventPublisher {
    private List<MethodExecutionEventListener> listeners = new ArrayList<MethodExecutionEventListener>();

    public void methodToMonitor(){
        MethodExecutionEvent event2Publish = new MethodExecutionEvent(this, "methodToMonitor");

        publishEvent(MethodExecutionStatus.BEGIN, event2Publish);
        // 执行实际的方法逻辑
        // ...
        publishEvent(MethodExecutionStatus.END, event2Publish);
    }

    protected void publishEvent(MethodExecutionStatus status, MethodExecutionEvent methodExecutionEvent) {
        List<MethodExecutionEventListener> copyListeners = new ArrayList<MethodExecutionEventListener>(listeners);

        for(MethodExecutionEventListener listener : copyListeners){
            if(MethodExecutionStatus.BEGIN.equals(status)){
                listener.onMethodBegin(methodExecutionEvent);
            }else
                listener.onMethodEnd(methodExecutionEvent);
        }
    }

    public void addMethodExecutionEventListener(MethodExecutionEventListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(MethodExecutionEventListener listener) {
        if (this.listeners.contains(listener))
            this.listeners.remove(listener);
    }

    public void removeAllListeners() {
        this.listeners.clear();
    }

    public static void main(String[] args) {
        MethodExecutionEventPublisher eventPublisher = new MethodExecutionEventPublisher();
        eventPublisher.addMethodExecutionEventListener(new SimpleMethodExecutionEventListener());
        eventPublisher.methodToMonitor();
    }
}
