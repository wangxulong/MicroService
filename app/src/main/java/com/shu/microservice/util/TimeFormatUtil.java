package com.shu.microservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wxl on 2016/2/24.
 */
public class TimeFormatUtil {
    private static SimpleDateFormat slf = null;

    public static  String getFormatStr(String format,Date date){
        if(null==format){
            format = "yyyy-mm-dd HH:ss";
        }
        slf = new SimpleDateFormat(format);
        return slf.format(date);
    }
}
