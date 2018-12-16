package net.myspring.wangzm.util.time;

import net.myspring.wangzm.util.text.StringUtils;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * Created by wangzm on 2017/3/31.
 */
public class YearMonthUtils {

    public static final String FORMATTER_FULL = "yyyy-MM";
    public static final String FORMATTER_SINGLE = "yyyy.M";

    public static String format(YearMonth yearMonth) {
        return format(yearMonth,FORMATTER_FULL);
    }

    public static String format(YearMonth yearMonth,String formatter) {
        String result = "";
        if(yearMonth != null) {
            result = yearMonth.format(DateTimeFormatter.ofPattern(formatter));
        }
        return result;
    }

    public static YearMonth parse(String localDateStr) {
        if(StringUtils.isBlank(localDateStr)){
            return null;
        }
        return parse(localDateStr,FORMATTER_FULL);
    }

    public static YearMonth parse(String localDateStr,String formatter) {
        YearMonth yearMonth = null;
        if(StringUtils.isNotBlank(localDateStr)) {
            yearMonth =YearMonth.parse(localDateStr,DateTimeFormatter.ofPattern(formatter));
        }
        return yearMonth;
    }
}
