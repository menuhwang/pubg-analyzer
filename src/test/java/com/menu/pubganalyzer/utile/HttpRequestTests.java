package com.menu.pubganalyzer.utile;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class HttpRequestTests {
    private String[] methods = new String[]{"GET", "POST", "PATCH", "DELETE"};
    private URL url;

    HttpRequestTests(String url) {
        try {
            this.url = new URL(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String request(String method) {
        HttpURLConnection connection = null;

        try {
            if (!Arrays.asList(this.methods).contains(method)) {
                throw new Exception("method is wrong");
            }
            //Create connection
            connection = (HttpURLConnection) this.url.openConnection();
            // 요청 메소드에 따라서 GET,DELETE, PUT 등으로 변경하시면 됩니다.
            connection.setRequestMethod(method);
            //Header 설정
            connection.setRequestProperty("Accept",
                    "application/vnd.api+json");
            connection.setRequestProperty("Authorization",
                    "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJmYjE3MTMxMC0yOGNjLTAxMzktNGRlZC0wZmQ1NjA2NTMyOTYiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjA4ODkzNDUyLCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6Ii03Y2U0NmIyMy0yMjAxLTRkOGMtOGQyYy0yMjc4MDA1OGExYTAifQ.p31NEwPGundw5jvUEFKf08v3KvTkD14Uv8u_nMYUbQY");

            connection.setUseCaches(false);// 캐싱데이터를 받을지 말지 세팅합니다.
            connection.setDoOutput(true); // 쓰기모드를 지정할지 세팅합니다.

            //Send request
            //위에서 세팅한 정보값을 바탕으로 요청을 보냅니다.
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
            //파라미터 정보를 보냅니다.
            //wr.writeBytes(parameters); // POST 일때
            //요청 실행후 dataOutputStream을 close 합니다.
            wr.close();

            //Get Response
            InputStream is = connection.getInputStream();
            //요청 결과 (response)를 BufferedReader로 받습니다.
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            // 자바 5 이상은 StringBuffer 를 이용해서 결과 값을 읽습니다.
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                //에러와 관계없이 모든 로직이 끝나면 connection 객체 (HttpURLConnection)를
                // disconnect 하여 모든 연결을 종료합니다.
                connection.disconnect();
            }
        }

    }
}
