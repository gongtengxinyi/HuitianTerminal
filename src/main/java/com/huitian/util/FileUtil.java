package com.huitian.util;

import com.huitian.constants.CacheConstants;
import com.huitian.frame.Init;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zlyj on 2017/9/25.
 */
public class FileUtil {
    public static boolean changeDir() {
        boolean res = true;
        try {
            String filePath = "";
            StringBuilder sb = new StringBuilder();
            if (StringUtils.isBlank(Init.configer.getProps(CacheConstants.file_path))) {
                filePath = CacheConstants.file_path_dafault;
            } else {
                filePath = Init.configer.getProps(CacheConstants.file_path);
            }
            Init.configer.save();
            sb.append(filePath).append("/").append(CacheConstants.file_name).append(CacheConstants.file_suffix);
            File file = new File(sb.toString());
            File configDir = new File(filePath);
            if (!file.exists()) {
                try {
                    configDir.mkdirs();
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            res = false;
            e.printStackTrace();
        }
        return res;
    }
}
