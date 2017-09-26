package com.huitian.constants;

/**
 * Created by Zlyj on 2017/9/26.
 * 加工筛选条件
 */
public class Filter {
    private String tabType;//类型  未加工 完成
    private String buttonType;//按钮类型

    public Filter(String tabType, String buttonType) {
        this.tabType = tabType;
        this.buttonType = buttonType;
    }

    public static Filter eq(String tabType, String buttonType) {
        return new Filter(tabType, buttonType);
    }

}
