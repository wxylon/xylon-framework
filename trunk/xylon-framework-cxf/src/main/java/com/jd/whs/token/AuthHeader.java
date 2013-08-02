package com.jd.whs.token;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * **************************************************
 *
 * @author sunbingwei@360buy.com
 *         date: Feb 23, 2011
 *         <p/>
 *         ***************************************************
 */
public class AuthHeader {

    private final static String QNAME = "http://www.360buy.com/";
    private final static String KEY = "AuthenticationHeader";
    private final static String TOKEN = "Token";
    
    private String qName;
    private String key;
    private String token;
    private String content;
    private String seed;

    public AuthHeader() {
    }

    public String getTokenValue() {
        if (StringUtils.isNotEmpty(content) &&
                StringUtils.isNotEmpty(seed)) {
            byte[] bb = MD5Util.md5(content + "-" + seed);
            return new String(Base64.encodeBase64(bb));
        }
        return "";
    }

    public static void main(String[] args) {
        byte[] bb = MD5Util.md5("");
        System.out.println(new String(Base64.encodeBase64(bb)));
    }

    public boolean checkTokenValue(String token) {
        String tmp = getTokenValue();
        if (StringUtils.isEmpty(tmp)) {
            return true;
        }
        if (StringUtils.isEmpty(token)) {
            return false;
        }

        return tmp.equals(token);
    }

    public String getqName() {
        if (StringUtils.isEmpty(qName)) {
            qName = QNAME;
        }
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public String getKey() {
        if (StringUtils.isEmpty(key)) {
            key = KEY;
        }
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        if (StringUtils.isEmpty(token)) {
            token = TOKEN;
        }
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
}
