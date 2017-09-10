package com.dou361.baseutils.utils;

import android.content.Context;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * ========================================
 * <p>
 * 版 权：dou361.com 版权所有 （C） 2015
 * <p>
 * 作 者：chenguanming
 * <p>
 * 个人网站：http://www.dou361.com
 * <p>
 * 版 本：1.0
 * <p>
 * 创建日期：2017/4/19 23:22
 * <p>
 * 描 述：
 * <p>
 * <p>
 * 修订历史：
 * <p>
 * ========================================
 */
public class StringUtils {

    /**
     * 私有造函数
     */
    private StringUtils() {
    }

    /**
     * 格式化银行卡
     *
     * @param cardNo
     * @return
     */
    public static String formatBankCard(String cardNo) {
        String card = "";
        card = cardNo.substring(0, 4) + " **** **** ";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 银行卡后四位
     *
     * @param cardNo
     * @return
     */
    public static String formatBankCardEndFour(String cardNo) {
        String card = "";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 字符串转换为int整形
     */
    public static int convertToInt(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 字符串转换为double
     */
    public static double convertToDouble(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        try {
            return Double.valueOf(str).doubleValue();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static String getString(String value) {
        if (value == null) {
            return "";
        }
        if ("null".equals(value)) {
            return "";
        } else {
            return value;
        }
    }

    /**
     * 格式化double数据，并保留两位小数，整数则不补全0.00
     */
    public static String formatExclueIntNumber(double value) {
        double value1 = Math.round(value * 100) * 0.01;
        String bb = "" + value1;
        String result = bb;
        String[] aar = bb.split("\\.");
        if (aar.length == 1) {
            result = aar[0] + "";
        } else if (aar.length > 1) {
            String str1 = aar[1];
            if (str1.length() == 1) {
                result = aar[0] + "." + str1 + "0";
            } else if (str1.length() == 2) {
                result = aar[0] + "." + str1;
            } else {
                result = aar[0] + "." + str1.substring(0, 3);
            }
        }
        return result + "";
    }

    /**
     * 格式化double数据，并保留两位小数，整数也补全0.00
     */
    public static String formatInclueIntNumber(double value) {
        double value1 = Math.round(value * 100) * 0.01;
        String bb = "" + value1;
        String result = bb;
        String[] aar = bb.split("\\.");
        if (aar.length == 1) {
            result = aar[0] + ".00";
        } else if (aar.length > 1) {
            String str1 = aar[1];
            if (str1.length() == 1) {
                result = aar[0] + "." + str1 + "0";
            } else if (str1.length() == 2) {
                result = aar[0] + "." + str1;
            } else {
                result = aar[0] + "." + str1.substring(0, 3);
            }
        }
        return result + "";
    }

    /**
     * 格式化double数据，并保留两位小数，整数则不补全0.00
     */
    public static String formatExclueIntNumber1(double value) {
        double value1 = Math.round(value * 100) * 0.01;
        String bb = "" + value1;
        String result = bb;
        String[] aar = bb.split("\\.");
        if (aar.length == 1) {
            result = aar[0] + "";
        } else if (aar.length > 1) {
            String str1 = aar[1];
            if (str1.length() == 1) {
                result = aar[0] + "." + str1;
            } else {
                result = aar[0] + "." + str1.substring(0, 2);
            }
        }
        return result + "";
    }

    /**
     * 格式化double数据，并保留两位小数，整数也补全0.00
     */
    public static String formatInclueIntNumber1(double value) {
        double value1 = Math.round(value * 100) * 0.01;
        String bb = "" + value1;
        String result = bb;
        String[] aar = bb.split("\\.");
        if (aar.length == 1) {
            result = aar[0] + ".00";
        } else if (aar.length > 1) {
            String str1 = aar[1];
            if (str1.length() == 1) {
                result = aar[0] + "." + str1;
            } else {
                result = aar[0] + "." + str1.substring(0, 2);
            }
        }
        return result + "";
    }

    /**
     * 获取随机数
     */
    public static int getRandomNumber(int num) {
        int result;
        Random rd = new Random();
        int tem = (int) Math.pow(10, num);
        result = tem + rd.nextInt(tem);
        return result;
    }

    /**
     * 获得UUID的小写字符串
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();
    }

    /**
     * 二进制转十六进制字符串
     */
    public static String byteTohex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 判断多个字符串是否相等，如果其中有一个为空字符串或者null，则返回false，只有全相等才返回true
     */
    public static boolean isEquals(String... agrs) {
        String last = null;
        for (int i = 0; i < agrs.length; i++) {
            String str = agrs[i];
            if (isEmpty(str)) {
                return false;
            }
            if (last != null && !str.equalsIgnoreCase(last)) {
                return false;
            }
            last = str;
        }
        return true;
    }

    /**
     * 返回一个高亮spannable
     *
     * @param content 文本内容
     * @param color   高亮颜色
     * @param start   起始位置
     * @param end     结束位置
     * @return 高亮spannable
     */
    public static CharSequence getHighLightText(String content, int color,
                                                int start, int end) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        start = start >= 0 ? start : 0;
        end = end <= content.length() ? end : content.length();
        SpannableString spannable = new SpannableString(content);
        CharacterStyle span = new ForegroundColorSpan(color);
        spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    /**
     * 获取链接样式的字符串，即字符串下面有下划线
     *
     * @param resId 文字资源
     * @return 返回链接样式的字符串
     */
    public static Spanned getHtmlStyleString(int resId) {
        StringBuilder sb = new StringBuilder();
        sb.append("<a href=\"\"><u><b>").append(UIUtils.getString(resId))
                .append(" </b></u></a>");
        return Html.fromHtml(sb.toString());
    }

    /**
     * 格式化文件大小，不保留末尾的0
     */
    public static String formatFileSize(long size) {
        return formatFileSize(size, false);
    }

    /**
     * 格式化文件大小，保留末尾的0，达到长度一致
     */
    public static String formatFileSize(long size, boolean keepZero) {
        String showSize = "";
        if (size >= 0 && size < 1024) {
            showSize = size + "B";
        } else if (size >= 1024 && size < (1024 * 1024)) {
            showSize = Long.toString(size / 1024) + "KB";
        } else if (size >= (1024 * 1024) && size < (1024 * 1024 * 1024)) {
            showSize = Long.toString(size / (1024 * 1024)) + "MB";
        } else if (size >= (1024 * 1024 * 1024)) {
            showSize = Long.toString(size / (1024 * 1024 * 1024)) + "GB";
        }
        return showSize;
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * 判断是否相等
     *
     * @param actual
     * @param expected
     * @return
     */
    public static boolean isEquals(String actual, String expected) {
        return actual == null ? false : actual.equals(expected);
    }

    /**
     * 将首字母大写
     * <p/>
     * capitalizeFirstLetter(null) = null;
     * capitalizeFirstLetter("") = "";
     * capitalizeFirstLetter("2ab") = "2ab"
     * capitalizeFirstLetter("a") = "A"
     * capitalizeFirstLetter("ab") = "Ab"
     * capitalizeFirstLetter("Abc") = "Abc"
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
                : new StringBuilder(str.length())
                .append(Character.toUpperCase(c))
                .append(str.substring(1)).toString();
    }

    /**
     * UTF-8编码
     * <p/>
     * <pre>
     * utf8Encode(null) = null
     * utf8Encode("") = "";
     * utf8Encode("aa") = "aa";
     * utf8Encode("啊啊啊啊") = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     *
     * @param str
     * @return
     */
    public static String utf8Encode(String str) {

        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(
                        "UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * UTF-8编码 如果异常则返回默认�?
     * <p/>
     * <pre>
     * utf8Encode(null) = null
     * utf8Encode("") = "";
     * utf8Encode("aa") = "aa";
     * utf8Encode("啊啊啊啊") = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     * </pre>
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * 全宽字符变换半宽字符
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     *
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 半宽字符变换全宽字符
     * <p/>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     *
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 手机号格式验�?
     *
     * @param str 指定的手机号码字符串
     * @return 是否为手机号码格�?是为true，否则false
     */
    public static Boolean isMobileNo(String str) {
        Boolean isMobileNo = false;
        try {
            Pattern p = Pattern
                    .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(str);
            isMobileNo = m.matches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isMobileNo;
    }

    /**
     * 是否只是字母和数�?
     *
     * @param str 指定的字符串
     * @return 是否只是字母和数�?是为true，否则false
     */
    public static Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String expr = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * 是否只是数字.
     *
     * @param str 指定的字符串
     * @return 是否只是数字:是为true，否则false
     */
    public static Boolean isNumber(String str) {
        Boolean isNumber = false;
        String expr = "^[0-9]+$";
        if (str.matches(expr)) {
            isNumber = true;
        }
        return isNumber;
    }

    /**
     * 是否是邮�?
     *
     * @param email 指定的字符串
     * @return 是否是邮�?是为true，否则false
     */
    public static Boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{3})$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 是否是中�?
     *
     * @param str 指定的字符串
     * @return 是否是中�?是为true，否则false
     */
    public static Boolean isChinese(String str) {
        Boolean isChinese = true;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度�?，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取�?��字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字�?
                if (temp.matches(chinese)) {
                } else {
                    isChinese = false;
                }
            }
        }
        return isChinese;
    }

    /**
     * 是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度�?，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取�?��字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字�?
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * 标准化日期时间类型的数据，不足两位的�?.
     *
     * @param dateTime 预格式的时间字符串，�?2012-3-2 12:2:20
     * @return String 格式化好的时间字符串，如:2012-03-20 12:02:20
     */
    public static String dateTimeFormat(String dateTime) {
        StringBuilder sb = new StringBuilder();
        try {
            if (isEmpty(dateTime)) {
                return null;
            }
            String[] dateAndTime = dateTime.split(" ");
            if (dateAndTime.length > 0) {
                for (String str : dateAndTime) {
                    if (str.indexOf("-") != -1) {
                        String[] date = str.split("-");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append("-");
                            }
                        }
                    } else if (str.indexOf(":") != -1) {
                        sb.append(" ");
                        String[] date = str.split(":");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append(":");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    /**
     * 不足2个字符的在前面补�?�?
     *
     * @param str 指定的字符串
     * @return 至少2个字符的字符�?
     */
    public static String strFormat2(String str) {
        try {
            if (str.length() <= 1) {
                str = "0" + str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String intFormat2(int num) {
        String str = "";
        try {
            if (num < 10) {
                str = "0" + num;
            } else {
                str = "" + num;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取某个字符串包含另�?��字符串的个数
     *
     * @param src
     * @param find
     * @return
     */
    public static int getContainCount(String src, String find) {
        int count = 0;
        while (src.indexOf(find) >= 0) {
            count++;
            src = src.replaceFirst(find, "");
        }
        return count;
    }

    /**
     * 从输入流中获取文�?
     *
     * @param context 文本输入�?
     * @return
     */
    public static String readTextFile(Context context, String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().getAssets().open(fileName);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String readedStr = "";
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String tmp;
            while ((tmp = br.readLine()) != null) {
                readedStr += tmp;
            }
            br.close();
            inputStream.close();
        } catch (UnsupportedEncodingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

        return readedStr;
    }

    public static boolean isIDCardValidate(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4",
                "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            Log.d("identity_errormsg", errorInfo);
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            Log.d("identity_errormsg", errorInfo);
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            Log.d("identity_errormsg", errorInfo);
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                    strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                Log.d("identity_errormsg", errorInfo);
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            Log.d("identity_errormsg", errorInfo);
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            Log.d("identity_errormsg", errorInfo);
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            Log.d("identity_errormsg", errorInfo);
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi
                    + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                    * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                Log.d("identity_errormsg", errorInfo);
                return false;
            }
        } else {
            return true;
        }
        // =====================(end)=====================
        return true;
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    @SuppressWarnings("unchecked")
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 将一个get请求的URL地址的所有参数进行签名，并返还加入签名后的整个URL地址
     */
    public static String urlToSignString(String url) {
        if (url == null) {
            return "";
        }
        Map<String, String> map = new HashMap<String, String>();
        String[] strParam = url.split("[?]");
        if (strParam != null && strParam.length > 1) {
            String[] strParams = strParam[1].split("&");
            for (int i = 0; i < strParams.length; i++) {
                String[] params = strParams[i].split("=");
                if (params.length > 1) {
                    map.put(params[0], params[1]);
                } else {
                    map.put(params[0], "");
                }
            }
            String sign = RopUtils.sign(map, "dou361-secret");
            return url + "&sign=" + sign;
        } else {
            return url;
        }
    }

    /**
     * 将一个get请求的URL地址的所有参数进行签名，返回签名参数的值
     */
    public static String urlToSignParams(String url) {
        if (url == null) {
            return "";
        }
        Map<String, String> map = new HashMap<String, String>();
        String[] strParam = url.split("[?]");
        if (strParam != null && strParam.length > 1) {
            String[] strParams = strParam[1].split("&");
            for (int i = 0; i < strParams.length; i++) {
                String[] params = strParams[i].split("=");
                if (params.length > 1) {
                    map.put(params[0], params[1]);
                } else {
                    map.put(params[0], "");
                }
            }
        }
        return RopUtils.sign(map);
        // return RopUtils.sign(map, "dou361-secret");
    }

    /**
     * 将一个URL地址的分割，去掉所有参数，返回？前的地址
     */
    public static String urlSplitParams(String url) {
        if (url == null) {
            return "";
        }
        String[] strParam = url.split("[?]");
        if (strParam != null && strParam.length > 1) {
            return strParam[0];
        }
        return url;
    }

    /**
     * 将一个URL地址的分割，去掉所有参数，返回？前的地址
     */
    public static String getNubmerByLength(int length) {
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }

}
