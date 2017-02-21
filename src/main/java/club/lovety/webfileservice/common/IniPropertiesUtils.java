package club.lovety.webfileservice.common;


import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Created by 念梓  on 2017/2/17.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public final class IniPropertiesUtils {

    private static Hashtable<String, Object> hashtable = new Hashtable<>();

    public static IniPropertiesUtils instance = new IniPropertiesUtils();

    private IniPropertiesUtils() {

    }

    public IniPropertiesUtils init(String confFile) throws Exception {
        assertNotBank(confFile, "配置文件不能为空");
        if (hashtable.isEmpty()) {
            Properties properties = new Properties();
            InputStream inputStream = IniPropertiesUtils.class.getClassLoader().getResourceAsStream(confFile);
            properties.load(inputStream);
            setIntValue(properties, AppConstant.FDFS_CONNECT_TIMEOUT_KEY);
            setIntValue(properties, AppConstant.FDFS_NETWORK_TIMEOUT_KEY);
            setValue(properties, AppConstant.FDFS_CHARSET_KEY);
            setValue(properties, AppConstant.FDFS_TRACKER_HTTP_PORT_KEY);
            setValue(properties, AppConstant.FDFS_ANTI_STEAL_TOKEN_KEY);
            setValue(properties, AppConstant.FDFS_SECRET_KEY_KEY);
            setValues(properties, AppConstant.FDFS_TRACKER_SERVER_KEY);
        }
        return instance;
    }

    public String assertNotBank(String src, String msg) throws Exception {
        if (StringUtils.isNotBlank(src)) {
            return null;
        } else {
            throw new Exception(StringUtils.isBlank(msg) ? "改字段不能为空" : msg);
        }
    }

    public String getValue(String key) {
        return hashtable.get(key).toString();
    }


    public int getIntValue(String key) {
        return Integer.parseInt(hashtable.get(key).toString());
    }

    public String[] getValues(String key) {

        return (String[]) hashtable.get(key);
    }


    public String getValue(String key, String defaultValue) {
        Object obj = hashtable.get(key);
        if (obj == null) {
            return defaultValue;
        }
        return obj.toString();
    }

    public int getIntValue(String key, int defaultValue) {
        Object obj = hashtable.get(key);
        if (obj == null) {
            return defaultValue;
        }
        return Integer.parseInt(obj.toString());
    }

    public boolean getBoolValue(String key) {
        Object obj = hashtable.get(key);
        return "yes".equals(obj.toString()) ? true : false;
    }

    public boolean getBoolValue(String key, boolean defalutValue) {
        Object obj = hashtable.get(key);
        if (obj == null) {
            return defalutValue;
        }
        return "yes".equals(obj.toString()) ? true : false;
    }

    public void setIntValue(Properties properties, String key) {
        Object obj = properties.get(key);
        if (obj != null) {
            hashtable.put(key, Integer.parseInt(obj.toString()));
        }
    }

    public void setValue(Properties properties, String key) {
        hashtable.put(key, properties.get(key));
    }

    public void setValues(Properties properties, String key) {
        Object obj = properties.get(key);
        if (obj != null) {
            String[] values = obj.toString().split(",");
            hashtable.put(key, values);
        }
    }

}
