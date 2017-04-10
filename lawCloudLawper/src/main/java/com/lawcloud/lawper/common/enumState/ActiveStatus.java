package com.lawcloud.lawper.common.enumState;

/**
 * Created by dell on 2016/10/31.
 * 有效状态
 */
public enum ActiveStatus {
    //无效
    UnActive(0, "app.activeStatus.unActive", "无效"),
    //有效
    Active(1, "app.activeStatus.active", "有效");

    private int state;
    private String desc;
    private String code;

    private ActiveStatus(int state, String code, String desc) {
        this.state = state;
        this.desc = desc;
        this.code = code;
    }

    public static String getDesc(int state){
        for(ActiveStatus data : ActiveStatus.values()){
            if(data.getState() == state)
                return data.getDesc();
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
