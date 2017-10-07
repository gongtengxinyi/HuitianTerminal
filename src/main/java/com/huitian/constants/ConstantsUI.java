package com.huitian.constants;

import com.huitian.frame.MainWindow;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * UI相关的常量是333
 */
public class ConstantsUI {

    /**
     * 软件名称,版本
     */
    public final static String APP_NAME = "绘天科技-终端";
    /**
     * 测试版本号，现在内部测试 beta 1.0
     */
    public final static String APP_VERSION = "beta v_1.0";
    /**
     * 主窗口图标
     */
    public final static Image IMAGE_ICON = Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/icon/logo-md.png"));
    /**
     * 首页正在加工订单的table预设大小
     */
    public final static int PORTAL_TABLE_COL_WIDTH=120;

}
