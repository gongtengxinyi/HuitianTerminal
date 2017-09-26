package com.huitian.frame;

import com.huitian.constants.CacheConstants;
import com.xiaoleilu.hutool.setting.dialect.Props;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * dingjianlei
 * 配置管理
 */
public class Config {

    private File file;

    private Props props;

    private static Config ourInstance = new Config();

    private String theme;

    private String font;

    private int fontSize;

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
        file = new File("config/config.properties");
        File configDir = new File("config/");
        if (!file.exists()) {
            try {
                configDir.mkdirs();
                file.createNewFile();
                originInit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        props = new Props(file);
    }

    public void setProps(String key, String value) {
        props.setProperty(key, value);
    }

    public String getProps(String key) {
        return props.getProperty(key);
    }

    /**
     * 存盘
     */
    public void save() {
        try {
            props.store(new FileOutputStream(file), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化原始数据
     */
    private void originInit() {
        props = new Props(file);
        props.setProperty("setting.appearance.theme", "Darcula(推荐)");
        props.setProperty("setting.appearance.font", "Microsoft YaHei UI");
        props.setProperty("setting.appearance.fontSize", "18");
        props.setProperty(CacheConstants.file_path,"D://huitian/");
        save();
    }

    public String getTheme() {
        return props.getProperty("setting.appearance.theme");
    }

    public void setTheme(String theme) {
        props.setProperty("setting.appearance.theme", theme);
    }

    public String getFont() {
        return props.getProperty("setting.appearance.font");
    }

    public void setFont(String font) {
        props.setProperty("setting.appearance.font", font);
    }

    public int getFontSize() {
        return props.getInt("setting.appearance.fontSize");
    }

    public void setFontSize(int fontSize) {
        props.setProperty("setting.appearance.fontSize", fontSize);
    }
}
