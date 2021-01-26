package by.victor.jwd.entity;

import java.io.Serializable;

public class RequestObject implements Serializable {

    private static final long serialVersionUID = 573156045465151084L;
    private int taskId;
    private String taskParam = "";

    public RequestObject (int taskId){
        this.taskId = taskId;
    }

    public RequestObject (int taskId, String taskParam){
        this.taskId = taskId;
        this.taskParam = taskParam;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskParam() {
        return taskParam;
    }

    public void setTaskParam(String taskParam) {
        this.taskParam = taskParam;
    }
}
