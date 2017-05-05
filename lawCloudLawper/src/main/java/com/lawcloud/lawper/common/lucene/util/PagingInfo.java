package com.lawcloud.lawper.common.lucene.util;

public class PagingInfo {

    private int tot_item = 0;
    private int curr_tot_page = 0;
    private int curr_rs = 10;//当前页数量
    private int tot_page = 1;//总页数
    private int max_t = 1;
    private int min_t = 1;


    public int getCurr_rs() {
        return curr_rs;
    }

    public int getMax_t() {
        return max_t;
    }

    public void setMax_t(int max_t) {
        this.max_t = max_t;
    }

    public int getMin_t() {
        return min_t;
    }

    public void setMin_t(int min_t) {
        this.min_t = min_t;
    }

    public int getCurr_tot_page() {
        return curr_tot_page;
    }

    public int getTot_item() {
        return tot_item;
    }

    public int getTot_page() {
        return tot_page;
    }

    /**
     *PagingInfo( 构造函数
     * @param tot_item  总条数
     * @param curr_tot_page 当前页数
     * @param curr_rs 当前起始位
     */
    public PagingInfo(int tot_item,
                      int curr_tot_page,
                      int curr_rs) {
        this.curr_rs = curr_rs;
        this.curr_tot_page = curr_tot_page;
        this.tot_item = tot_item;
        //总页数
        this.tot_page = tot_item / curr_tot_page;

        if (tot_item % curr_tot_page > 0) {
            this.tot_page = this.tot_page + 1;
        }
        if(curr_rs==0){
            this.min_t=0;
        }
        else{
            this.min_t=curr_tot_page*(curr_rs-1);

        }

        this.max_t=curr_tot_page*(curr_rs-1)+curr_tot_page;
    }

    public boolean next_page() {
        if(this.min_t<tot_item){
            return true;
        }
        return false;
    }

    public boolean previous_page() {
        if(this.getMin_t()<1){
            return false;
        }
        return false;
    }
}
