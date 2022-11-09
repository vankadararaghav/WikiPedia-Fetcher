package tech.codingclub.helix.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

    public static String sendGet(String str) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(str);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //int status = conn.getResponseCode();
        conn.setRequestMethod("GET");
        conn.addRequestProperty("User-Agent","Chrome");
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = br.readLine();
        while(line!=null)
        {
            result.append(line);
            line = br.readLine();
        }
        return result.toString();
    }
    public static void main(String[] args) {

        try{
            System.out.println(sendGet("https://example.com"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

