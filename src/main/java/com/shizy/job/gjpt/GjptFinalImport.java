package com.shizy.job.gjpt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shizy.utils.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class GjptFinalImport {

    /**
     * gjpt 常量导出
     */
    private void dothis() {
        JSONArray constants = getConstants();

        for (int i = 0; i < constants.size(); i++) {
            String constant = (String) constants.get(i);

            JSONArray values = getConstantValues(constant);
            for (int j = 0; j < values.size(); j++) {
                String value = (String) values.get(j);
                System.out.println(constant+"\t"+value);
            }
            System.out.println();
        }
    }

    private JSONArray getConstants() {
        Map param = new HashMap();

        Map headers = new HashMap();
        headers.put("Cookie", "securityKey=bbe23d33-96c1-41eb-a325-f07bc8348793");

        String httpResult = HttpUtil.get("http://127.0.0.1:8080/api3.0/rest/properties_3_1/getConstants?templateId=&version=3.3&keyword=&_=1568101482295", param, headers);

        JSONObject rstObj = JSONObject.parseObject(httpResult);
        JSONArray constants = rstObj.getJSONArray("message");

        return constants;
    }

    private JSONArray getConstantValues(String key) {
        Map param = new HashMap();
        param.put("name", key);

        Map headers = new HashMap();
        headers.put("Cookie", "securityKey=bbe23d33-96c1-41eb-a325-f07bc8348793");

        String httpResult = HttpUtil.get("http://127.0.0.1:8080/api3.0/rest/properties_3_1/getConstantValues?templateId=&version=3.3&keyword=&_=1568101482296", param, headers);

        JSONObject rstObj = JSONObject.parseObject(httpResult);
        JSONArray values = rstObj.getJSONArray("data");

        return values;
    }

    public static void main(String[] args) {
        new GjptFinalImport().dothis();
    }

}





















