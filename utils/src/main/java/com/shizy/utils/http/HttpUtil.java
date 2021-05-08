package com.shizy.utils.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HttpUtil {

    /**
     * @param url
     * @param bodyParam
     * @param urlParam
     * @param headers
     * @return
     */
    public static String post(String url, Map bodyParam, Map urlParam, Map<String, String> headers) {

        CloseableHttpClient httpClient = null;
        try {
            StringBuilder uri = new StringBuilder(url);

            if (urlParam != null && !urlParam.isEmpty()) {
                uri.append("?");
                for (Map.Entry entry : (Set<Map.Entry>) urlParam.entrySet()) {
                    uri.append(URLEncoder.encode(entry.getKey().toString(), "UTF-8"));
                    uri.append("=");
                    if (entry.getValue() != null) {
                        uri.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                    }
                    uri.append("&");
                }
            }

            HttpPost httpPost = new HttpPost(uri.toString());
            UrlEncodedFormEntity formEntity = null;

            httpPost.setEntity(new StringEntity(JSON.toJSONString(bodyParam), ContentType.APPLICATION_JSON));

            for (Map.Entry entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey().toString(), entry.getValue().toString());
            }

            httpClient = HttpClients.custom()
                    .evictExpiredConnections()
                    .evictIdleConnections(30, TimeUnit.SECONDS)
                    .build();

            HttpResponse httpresponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpresponse.getEntity();
            return EntityUtils.toString(httpEntity, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String get(String url, Map urlParam, Map<String, String> headers) {

        CloseableHttpClient httpClient = null;
        try {
            StringBuilder uri = new StringBuilder(url);

            if (urlParam != null && !urlParam.isEmpty()) {
                if(!url.contains("?")){
                    uri.append("?");
                }else{
                    uri.append("&");
                }
                for (Map.Entry entry : (Set<Map.Entry>) urlParam.entrySet()) {
                    uri.append(URLEncoder.encode(entry.getKey().toString(), "UTF-8"));
                    uri.append("=");
                    if (entry.getValue() != null) {
                        uri.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                    }
                    uri.append("&");
                }
            }

            HttpGet httpGet = new HttpGet(uri.toString());

            if (headers != null) {
                for (Map.Entry entry : headers.entrySet()) {
                    httpGet.addHeader(entry.getKey().toString(), entry.getValue().toString());
                }
            }

            httpClient = HttpClients.custom()
                    .evictExpiredConnections()
                    .evictIdleConnections(30, TimeUnit.SECONDS)
                    .build();

            HttpResponse httpresponse = httpClient.execute(httpGet);

            HttpEntity httpEntity = httpresponse.getEntity();
            return EntityUtils.toString(httpEntity, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
