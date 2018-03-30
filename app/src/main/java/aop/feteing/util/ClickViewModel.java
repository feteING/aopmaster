package aop.feteing.util;

import java.io.Serializable;

/**
 * Created by haier on 2018/3/30.
 */

public class ClickViewModel implements Serializable {

    Long lastClickTime;
    int viewId;
    int curIndex;

    public ClickViewModel() {
    }

    public ClickViewModel(Long lastClickTime, int viewId) {
        this.lastClickTime = lastClickTime;
        this.viewId = viewId;
    }

    public int getCurIndex() {
        return curIndex;
    }

    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    public Long getLastClickTime() {
        return lastClickTime;
    }

    public void setLastClickTime(Long lastClickTime) {
        this.lastClickTime = lastClickTime;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }

    @Override
    public String toString() {
        return "ClickViewModel{" +
                "lastClickTime=" + lastClickTime +
                ", viewId=" + viewId +
                ", curIndex=" + curIndex +
                '}';
    }
}
