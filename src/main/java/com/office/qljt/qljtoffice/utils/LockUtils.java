package com.office.qljt.qljtoffice.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
public class LockUtils {

    public static boolean verify(String value, String url, String ran, String rul, String time) {
        try {
            if (TextUtils.isEmpty(time)) return false;
            long timeRequest = Long.parseLong(time);
            long currentTime = new Date().getTime();
            System.out.println("timeRequest" + timeRequest);
            System.out.println("currentTime" + currentTime);
            //判断是否超时
            if (currentTime - timeRequest > 30000) {
                return false;
            }
            String[] random = ran.split("\\.");
            String[] rule = rul.split("\\.");
            if (random.length != 0 && rule != null) {
                Date date = new Date();
                int dateServer = Integer.parseInt(rule[rule.length - 1]);
                System.out.println("time" + time);
                System.out.println("currentTime" + currentTime);
                System.out.println("dataServer" + dateServer);
                System.out.println("dataServer" + (date.getDate() % 3));
                //校验日期
                if (Math.abs((date.getDate() % 3) - dateServer) < 2) {
                    int interval = solve(random, rule);
                    long stamp = timeRequest - interval;
                    LinkedHashMap<String, Object> obj = new LinkedHashMap<>();
                    obj.put("url", url);
                    obj.put("stamp", stamp);
                    LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                    map.put("obj", obj);
                    String data = new Gson().toJson(map);
                    String decodeStr = URLEncoder.encode(data, "UTF-8");
                    String[] list = decodeStr.split("%");
                    String str = "";
                    for (int i = 0; i < list.length; i++) {
                        String s = list[i];
                        int len = s.length();
                        if (len > 2) {
                            str += (s.substring(0, len / 3) + 'l' + s.substring(len / 3, 2 * len / 3) + 'i' + s.substring(2 * len / 3) + 'b');
                        } else if (len == 0) {
                            str += "h";
                        } else {
                            str += (s.substring(0, 1) + 'h' + s.substring(1));
                        }
                        if (i < list.length - 1) {
                            str += "^";
                        }
                    }
                    String md5 = md5(str);
                    System.out.println("md5" + md5);
                    System.out.println("value" + value);
                    if (md5.equals(value)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

//    public static void main(String[] args) throws Exception {
//        String str = "59-46*12-9*10";
//
//        ScriptEngineManager manager = new ScriptEngineManager();
//        ScriptEngine engine = manager.getEngineByName("JavaScript");
//
//        System.out.println(engine.eval(str));
////        long begintime = System.nanoTime() / 1000;
////        verify("875946675f03b3e4443bdd4feafc264b", "https://sss", "95.17.13.1", "0.0.1", "1653995044761");
////        long endtime = System.nanoTime() / 1000;
////        System.out.println(endtime - begintime);
////        int[] random = {59, 46, 12,9,10};
////        int[] rule = {1,2,1,2};
////        str = str.replaceAll("\\+", "#0#")
////                .replaceAll("-", "#1#")
////                .replaceAll("\\*", "#2#");
////        String[] split = str.split("#");
////        int[] nums = new int[split.length / 2 + 1];
////        int[] cals = new int[split.length];
////        for (int i = 0; i < split.length; i++) {
////            if (i % 2 == 0) {
////                nums[i / 2] = Integer.parseInt(split[i]);
////            } else {
////                cals[i / 2] = Integer.parseInt(split[i]);
////            }
////        }
//        String[] nums = {"59", "46", "12", "9", "10"};
//        String[] cals = {"1", "2", "1", "2"};
//        System.out.println(solve(nums, cals));
//    }

    public static int solve(String[] random, String[] rule) {
        for (int i = rule.length - 1; i > -1; i--) {
            if (rule[i].equals("2")) {
                random[i] = (Integer.parseInt(random[i]) * Integer.parseInt(random[i + 1])) + "";
                random[i + 1] = "1";
            }
        }
        int res = Integer.parseInt(random[0]);
        for (int i = 1; i < random.length; i++) {
            res = calc(Integer.parseInt(rule[i - 1]), res, Integer.parseInt(random[i]));
        }

        return res;
    }

    public static int calc(int index, int pre, int curr) {
        switch (index) {
            case 0:
                return pre + curr;
            case 1:
                return pre - curr;
            case 2:
                return pre * curr;
        }
        return pre + curr;
    }


    public static String md5(String dateString) throws Exception {
        byte[] digest = MessageDigest.getInstance("md5").digest(dateString.getBytes("utf-8"));
        String md5code = new BigInteger(1, digest).toString(16);
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }


    public static RequestVerify unLock(String data) {
        RequestVerify map = null;
        try {
            String decodeStr = URLDecoder.decode(data, "UTF-8");
            List<String> decode = Arrays.asList(decodeStr.split("\\^"));
            String result = "";
            for (int i = 0; i < decode.size(); i++) {
                String item = decode.get(i);
                String subResult = "";
                int len = item.length();
                if (len > 3) {
                    subResult = item.substring(0, len / 3 - 1) + item.substring(len / 3, 2 * len / 3 - 1) + item.substring(2 * len / 3, len - 1);
                } else if (len > 2) {
                    subResult = item.substring(0, 1) + item.substring(2);
                }

                if (i != decode.size() - 1) {
                    subResult += "%";
                }
                result += subResult;
            }
            String resultData = URLDecoder.decode(result, "UTF-8");
            log.info(resultData);
            map = new Gson().fromJson(resultData, RequestVerify.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * lib加密
     *
     * @param token
     * @return
     */
    public static String libLock(String token) {
        int length = token.length();
        int index1 = length / 4;
        int index2 = length * 2 / 4;
        int index3 = length * 3 / 4;
        token = token.substring(0, index1) + "l" + token.substring(index1, index2) + "i" + token.substring(index2, index3) + "b" + token.substring(index3);
        return token;
    }

    /**
     * lib解密
     *
     * @param token
     * @return
     */
    public static String libUnlock(String token) {
        int length = token.length() - 3;
        int index1 = length / 4;
        int index2 = length * 2 / 4;
        int index3 = length * 3 / 4;
        token = token.substring(0, length / 4) + token.substring(index1 + 1, index2 + 1) + token.substring(index2 + 2, index3 + 2) + token.substring(index3 + 3);
        return token;
    }

}
