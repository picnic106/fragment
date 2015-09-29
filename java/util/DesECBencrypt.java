package util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 *
 * 使用ecb模式des加密
 * ecb模式是电子密码本模式，安全模式教低
 *
 * ECB模式：
 * 优点：
 * 1. 简单；
 * 2. 有利于并行计算；
 * 3. 误差不会被传递；
 * 缺点：
 * 1. 不能隐藏明文的模式；
 * 2. 可能对明文进行主动攻击；
 *
 * DES ECB（电子密本方式）其实非常简单，就是将数据按照8个字节一段进行DES加密或解密得到一段段的8个字节的密文或者明文，
 * 最后一段不足8个字节（一般补0或者F），按照需求补足8个字节进行计算（并行计算），
 * 之后按照顺序将计算所得的数据连在一起即可，各段数据之间互不影响。
 *
 */
public class DesECBencrypt {
    /**
     * 自定义一个key
     * @param keyRule
     */
    public static byte[] getKey(byte[] keyRule) {
        Key key = null;
        byte[] keyByte = keyRule;
        // 创建一个空的八位数组,默认情况下为0
        byte[] byteTemp = new byte[8];
        // 将用户指定的规则转换成八位数组
        for (int i = 0; i < byteTemp.length && i < keyByte.length; i++) {
            byteTemp[i] = keyByte[i];
        }
        key = new SecretKeySpec(byteTemp, "DES");
        return key.getEncoded();
    }

    /***
     * 解密数据
     * @param decryptString
     * @param decryptKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptDES2(byte[] decryptString, byte[] decryptKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKey(decryptKey), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte decryptedData[] = cipher.doFinal(decryptString);
        return decryptedData;
    }

    /**
     * 加密数据
     * @param encryptbyte  注意：这里的数据长度只能为8的倍数
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptDES(byte[] encryptbyte, byte[] encryptKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(getKey(encryptKey), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(encryptbyte);
        return encryptedData;
    }


    //将十六进制编码字符串转换为普通字符串
    public static String toStringHex(String s)
    {
        byte[] baKeyword = new byte[s.length()/2];
        for(int i = 0; i < baKeyword.length; i++)
        {
            try
            {
                baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i*2, i*2+2),16));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            s = new String(baKeyword, "utf-8");//UTF-16le:Not
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        return s;
    }

    // 转化字符串为十六进制编码
    public static String toHexString(String s)
    {
        String str="";
        for (int i=0;i<s.length();i++)
        {
            int ch = (int)s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }


    /**
     *
     * 对加密结果不做任何处理
     * @param enData 要加密的数据
     * @param key    密钥
     * @return
     * @throws Exception
     */
    public static byte[] encodeDes(String enData,String key) throws Exception {
        byte[] s = encryptDES(enData.getBytes(),key.getBytes());
        return s;
    }

    /**
     *
     * 将加密结果转码成16进制之后返回
     * @param enData 要加密的数据
     * @param key    密钥
     * @return
     * @throws Exception
     */
    public static String encodeDesToHex(String enData,String key) throws Exception {
        byte[] s = encryptDES(enData.getBytes(),key.getBytes());
        return toHexString(new String(s));
    }

    /**
     * 对加密后的数据进行base64编码
     * 如果
     * @param enData  要加密的数据
     * @param key     密钥
     * @param tohex   是否对加密后的结果进行16进制转码
     * @return
     * @throws Exception
     */
    public static String encodeDesToBase64(String enData,String key,boolean tohex) throws Exception {
        byte[] s = encodeDes(enData, key);
        if (tohex){
            return toHexString(Base64.encode(s));
        }else {
            return Base64.encode(s);
        }
    }


    /**
     * 解密数据
      * @param deData 要解密的数据
     * @param key     密钥
     * @param tohex   是否进行16进制解码
     * @return
     * @throws Exception
     */
    public static String decodeDes(String deData,String key,boolean tohex) throws Exception {
        if (tohex){
            deData=toStringHex(deData);
        }
        return new String(decryptDES2(deData.getBytes(),key.getBytes()));
    }


    public  static String decodeDesFromBase64(String deData,String key,boolean tohex) throws Exception {
        if (tohex){
            deData=toStringHex(deData);
        }
        return new String(decryptDES2(Base64.decode(deData),key.getBytes()));
    }



 
}
