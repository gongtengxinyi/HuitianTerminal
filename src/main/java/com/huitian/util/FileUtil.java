package com.huitian.util;

import com.huitian.constants.CacheConstants;

import java.io.File;
import java.io.IOException;

/**
 * Created by Zlyj on 2017/9/25.
 */
public class FileUtil {
    public static  boolean changeDir(){
     boolean res =true;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(CacheConstants.file_path).append(CacheConstants.file_name).append(CacheConstants.file_suffix);
            File file = new File(sb.toString());
            File configDir = new File(CacheConstants.file_path);
            if (!file.exists()) {
                try {
                    configDir.mkdirs();
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            res=false;
            e.printStackTrace();
        }
        return res;
    }
}
