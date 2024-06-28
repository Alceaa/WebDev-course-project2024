package utils;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfProperties {
    protected static InputStream inputStream;
    protected static Properties PROPERTIES;
    static {
        try {
            inputStream = ConfProperties.class.getResourceAsStream("/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace(); } } }
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key); } }