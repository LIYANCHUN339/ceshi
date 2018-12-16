package net.myspring.wangzm.util.text;

import com.google.common.collect.Lists;
import net.myspring.wangzm.common.constant.CharConstant;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "^\\d{15}$|^\\d{17}[0-9Xx]$";

    public static String getSheetName(String name) {
        if (isBlank(name)) {
            return null;
        } else {
            return name.trim().replace("/", "").replace("\\", "").replace("?", "").replace("[", "").replace("]", "").replace("*", "");
        }
    }

    public static <E> List<E> getSplitList(String str, String splitter) {
        List<E> list = Lists.newArrayList();
        if (isNotBlank(str)) {
            String[] arr = str.split(splitter);
            for (String item : arr) {
                if (isNotBlank(item) && !list.contains(item.trim())) {
                    E value = (E) item.trim();
                    list.add(value);
                }
            }
        }
        return list;
    }

    public static String getMergeList(List<String> list, String splitter) {
        StringBuffer sb = new StringBuffer("");
        if (list != null && list.size() > 0) {
            for (String item : list) {
                sb.append(item).append(splitter);
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }

    public static String appendString(String first, String sec, String separator) {
        if (isBlank(first) && isBlank(sec)) {
            return "";
        } else if (isBlank(first)) {
            return sec;
        } else if (isBlank(sec)) {
            return first;
        } else {
            return first + separator + sec;
        }
    }

    public static String reverse(String str) {
        return str == null ? null : (new StringBuilder(str)).reverse().toString();
    }

    public static String getEncryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(8);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, 1024);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, 1024);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

    public static String toString(Object object) {
        if (object == null) {
            return "";
        } else {
            return String.valueOf(object);
        }
    }

    public static String getFirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }


    public static String getNumberStr(String str) {
        if (isBlank(str)) {
            return "";
        }
        String trimmedStr = str.trim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < trimmedStr.length(); i++) {
            if (trimmedStr.charAt(i) >= 48 && trimmedStr.charAt(i) <= 57) {
                sb.append(trimmedStr.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static String division(Integer totalQty, Integer qty) {
        if (qty == 0 || totalQty == 0) {
            return "0.00";
        }
        BigDecimal percent = new BigDecimal(qty).multiply(new BigDecimal(100)).divide(new BigDecimal(totalQty), 2, BigDecimal.ROUND_HALF_UP);
        return percent.toString();
    }

    public static Boolean checkIdcard(String idcard){
        return Pattern.matches(REGEX_ID_CARD, idcard);
    }

    public static Boolean checkMobilePhone(String mobilePhone){
        return Pattern.matches(REGEX_MOBILE, mobilePhone);
    }

    public static String getChineseMoney(BigDecimal money) {
        if (money != null) {
            String s = new DecimalFormat("#.00").format(money.abs());
            s = s.replaceAll("\\.", "");// 将字符串中的"."去掉
            char d[] = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
            String unit = "仟佰拾兆仟佰拾亿仟佰拾万仟佰拾元角分";
            int c = unit.length();
            StringBuffer sb = new StringBuffer(unit);
            for (int i = s.length() - 1; i >= 0; i--) {
                sb.insert(c - s.length() + i, d[s.charAt(i) - 0x30]);
            }
            s = sb.substring(c - s.length(), c + s.length());
            s = s.replaceAll("零[仟佰拾]", "零").replaceAll("零{2,}", "零").replaceAll("零([兆万元Ԫ])", "$1").replaceAll("零[角分]", "");
            if (BigDecimal.ZERO.compareTo(money) == 1) {
                return "负" + s + "整";
            }
            if (BigDecimal.ZERO.compareTo(money) == 0) {
                return "零元整";
            }
            return s + "整";
        }
        return "";
    }

    public static List<String> getFilterList(String filterValue) {
        List<String> filterList = Lists.newArrayList();
        if (isNotBlank(filterValue)) {
            filterValue = getReplaced(filterValue);
            filterList = getSplitList(filterValue, CharConstant.COMMA);
        }
        return filterList;
    }

    public static String getReplaced(String str) {
        List<String> beforeList = Lists.newArrayList();
        beforeList.add(CharConstant.COMMA_FULL);
        beforeList.add(CharConstant.SPACE);
        beforeList.add(CharConstant.SPACE_FULL);
        beforeList.add(CharConstant.ENTER);
        beforeList.add(CharConstant.TAB);
        return getReplaced(str, beforeList, CharConstant.COMMA);
    }

    public static String getReplaced(String str, List<String> beforeList, String after) {
        String result = trim(str);
        if (StringUtils.isNotBlank(result)) {
            for (String before : beforeList) {
                result = StringUtils.replace(result, before, after);
            }
        }
        return result;
    }


    public static String getFormatId(String id, String prefix) {
        return getFormatId(id, prefix, "000000000000");
    }

    public static String getFormatId(String id, String prefix, String format) {
        if (StringUtils.isBlank(id)) {
            return "";
        } else {
            DecimalFormat decimalFormat = new DecimalFormat(format);
            return prefix + decimalFormat.format(Long.valueOf(id));
        }
    }

    /**
     * 将字节数组转换为16进制形式
     *
     * @param input
     * @return
     */
    public static String toHex(byte[] input) {
        if (input == null)
            return null;
        StringBuilder output = new StringBuilder(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            //小于16的需要补充一位(共2位)
            if (current < 16) {
                output.append('0');
            }

            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }
}
