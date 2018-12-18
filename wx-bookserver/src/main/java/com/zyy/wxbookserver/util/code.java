package com.zyy.wxbookserver.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.bouncycastle.jce.provider.BouncyCastleProvider;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zyy.wxbookserver.controller.LoginController;
import org.apache.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

public class code {
    private static final Logger log = Logger.getLogger(code.class);

    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {

// 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);

// 加密秘钥

        byte[] keyByte = Base64.decode(sessionKey);

// 偏移量

        byte[] ivByte = Base64.decode(iv);

        try {

// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要

            int base = 16;

            if (keyByte.length % base != 0) {

                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);

                byte[] temp = new byte[groups * base];

                Arrays.fill(temp, (byte) 0);

                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);

                keyByte = temp;

            }

// 初始化

            Security.addProvider(new BouncyCastleProvider());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");

            parameters.init(new IvParameterSpec(ivByte));

            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

            byte[] resultByte = cipher.doFinal(dataByte);

            if (null != resultByte && resultByte.length > 0) {

                String result = new String(resultByte, "UTF-8");

                return JSON.parseObject(result);

            }

        } catch (NoSuchAlgorithmException e) {

            log.error(e.getMessage(), e);

        } catch (NoSuchPaddingException e) {

            log.error(e.getMessage(), e);

        } catch (InvalidParameterSpecException e) {

            log.error(e.getMessage(), e);

        } catch (IllegalBlockSizeException e) {

            log.error(e.getMessage(), e);

        } catch (BadPaddingException e) {

            log.error(e.getMessage(), e);

        } catch (UnsupportedEncodingException e) {

            log.error(e.getMessage(), e);

        } catch (InvalidKeyException e) {

            log.error(e.getMessage(), e);

        } catch (InvalidAlgorithmParameterException e) {

            log.error(e.getMessage(), e);

        } catch (NoSuchProviderException e) {

            log.error(e.getMessage(), e);

        }

        return null;

    }
}
