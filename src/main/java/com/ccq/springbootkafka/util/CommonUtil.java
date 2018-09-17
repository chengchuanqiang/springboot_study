package com.ccq.springbootkafka.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by mobayes on 4/2/18.
 */
public class CommonUtil {

    public static boolean isBlank(String str){
        if(null==str||str.length()==0){
            return true;
        }
        return false;
    }


    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }


    public static String getDateString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static String getDateTimeString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 获取机器MAC地址
     *
     * @param inetAddress
     * @return 机器MAC地址
     * @throws Exception
     */
    private static String getMACAddress(InetAddress inetAddress) throws SocketException {
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            //mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        //把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }

    /**
     *获取机器MAC地址
     * @return 机器MAC地址
     */
    public static String getMACAddress() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            return getMACAddress(ia);
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            return null;
        }
    }

}
