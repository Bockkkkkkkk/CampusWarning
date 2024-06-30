package com.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class netUtil {
    private static final String url="http://127.0.0.1:8080";

    public  static  String doPost(final String urlString, final String data) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                StringBuilder res = new StringBuilder();
                try {
                    URL myUrl = new URL(url + urlString);
                    HttpURLConnection conn = (HttpURLConnection) myUrl.openConnection();  //获取URLConnection对象
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");  // 设置为POST方式
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                    BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                    bufferedWriter.write(data);
                    bufferedWriter.flush();

                    InputStream inputStream = conn.getInputStream();  //读数据,得到的是字节流
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");//包装为字符流
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        res.append(line);
                    }
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return res.toString();
            }
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        thread.join();
        return futureTask.get();

    }

}

