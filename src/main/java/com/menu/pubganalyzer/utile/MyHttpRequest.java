package com.menu.pubganalyzer.utile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class MyHttpRequest {
    private String[] methods = new String[]{"GET", "POST", "DELETE"};
    private URL url;
    private Map<String, String> headers = new HashMap<String, String>();
    //gzip 여부
    boolean isGZIP = false;

    public MyHttpRequest(String url) {
        try {
            this.url = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHeader(String key, String value) {
        if (key.equals("Accept-Encoding") && value.equals("gzip")) {
            this.isGZIP = true;
        }
        this.headers.put(key, value);
    }

    public String get() {
        return this.request("GET");
    }

    private String request(String method, String ...parameters) {
        //http 통신을 하기위한 객체 선언 실시
        HttpURLConnection conn = null;

        //http 통신 요청 후 응답 받은 데이터를 담기 위한 변수
        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;

        //메소드 호출 결과값을 반환하기 위한 변수
        String returnData = "";

        try {
            //파라미터로 들어온 url을 사용해 connection 실시
            conn = (HttpURLConnection) this.url.openConnection();

            //http 요청에 필요한 타입 정의 실시
            for (String key:this.headers.keySet()) {
                conn.setRequestProperty(key, this.headers.get(key));
            }
            conn.setRequestMethod(method);

            if (method.equals("POST")) {
                conn.setDoOutput(true); //OutputStream을 사용해서 post body 데이터 전송
                try (OutputStream os = conn.getOutputStream()){
                    byte request_data[] = parameters[0].getBytes("utf-8");
                    os.write(request_data);
                    os.close();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }

            //http 요청 실시
            conn.connect();

            //http 요청 후 응답 받은 데이터를 버퍼에 쌓는다
            if ("gzip".equals(conn.getContentEncoding())) {
                br = new BufferedReader(new InputStreamReader(new GZIPInputStream(conn.getInputStream())));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            }
            while ((responseData = br.readLine()) != null) {
                returnData += responseData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //http 요청 및 응답 완료 후 BufferedReader를 닫아줍니다
            try {
                if (br != null) {
                    br.close();
                }
                conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return returnData;
    }
}
