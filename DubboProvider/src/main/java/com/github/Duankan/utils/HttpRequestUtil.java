/**
 * Copyright © 2018湖北金拓维信息技术有限公司. All rights reserved.
 *
 * @接口方法说明: HttpRequestUtil
 * @Prject: server-project
 * @Package: com.kingtopware.common.util
 * @ClassName: HttpRequestUtil
 * @注意事项: HTTP请求工具类
 * @author: luxiaolin
 * @date: 2018/8/7
 * @version: V1.0
 */
package com.github.Duankan.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.helper.StringUtil;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequestUtil {
    private HttpRequestUtil() {
        throw new UnsupportedOperationException("Not allow construct!");
    }

    public static String sendGet(String url) {
        return sendGet(url, "");
    }

    public static URLConnection getURLConnection(URL realUrl, String contentType) throws IOException {
        URLConnection conn = realUrl.openConnection();
        // 设置通用的请求属性
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        if (contentType.isEmpty())
            conn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
        else
            conn.setRequestProperty("Content-type", contentType + ";charset=UTF-8");
        conn.setRequestProperty("User-Operation-Info", "a3UjjlaLC9He");
        conn.setRequestProperty("Authorization", "Basic YWRtaW46Z2Vvc2VydmVy");
        return conn;
    }

    /**
     * @param url
     * @param param
     * @throws IOException
     * @功能:
     * @注意事项: 发送GET请求
     * @接口方法说明: sendGet
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            if (!StringUtil.isBlank(param))
                url += "?" + param;
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = getURLConnection(realUrl, "");
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            // 使用finally块来关闭输入流
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param url
     * @param param
     * @功能:
     * @注意事项: 发送POST请求
     * @接口方法说明: sendPost
     * @see: com.kingtopware.common.util.HttpRequestUtil#sendPost()
     */
    public static String sendPost(String url, Object param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = getURLConnection(realUrl, "");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            //使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param url
     * @param param
     * @return
     * @author： luxiaolin
     * @description：带编码格式的Post请求
     */
    public static String sendPost(String url, Object param, String contentType) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = getURLConnection(realUrl, contentType);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            //使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param url
     * @param obj
     * @功能:
     * @注意事项: 发送POST请求
     * @接口方法说明: sendHttpPost
     */
    public static String sendHttpPost(String url, Object obj) {
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(obj), "utf-8");//解决中文乱码问题
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        try {
            CloseableHttpResponse resp = client.execute(httpPost);
            HttpEntity he = resp.getEntity();
            result = EntityUtils.toString(he, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param url
     * @param param
     * @return
     * @Description: 发送http请求（解决请求云盘接口发送中文失败问题）
     * @Title: sendPostOfCloudDisk
     */
    public static String sendPostOfCloudDisk(String url, Object param) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = getURLConnection(realUrl, "");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(param.toString());
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            //使用finally块来关闭输出流、输入流
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGetImage(String url, String param) {
        String result = "";
        InputStream in;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            if (!StringUtil.isBlank(param))
                url += "?" + param;
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = getURLConnection(realUrl, "");
            // 建立实际的连接
            conn.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = conn.getInputStream();

            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = in.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            File desc = new File("G:\\dankin0814\\1.png");
            FileOutputStream out = new FileOutputStream(desc);
            out.write(outStream.toByteArray());
            out.close();
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
        }
        return result;
    }
}
