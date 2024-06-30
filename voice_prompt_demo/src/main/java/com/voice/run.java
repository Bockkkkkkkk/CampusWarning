package com.voice;


import com.alibaba.fastjson.JSON;
import com.common.Location;
import com.common.WaningMessage;
import com.common.netUtil;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.iflytek.cloud.speech.*;
import com.iflytek.util.DebugLog;
import com.iflytek.util.JsonParser;
import com.iflytek.util.Version;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class run {

    public static void main(String[] args) {

        // 初始化
        StringBuffer param = new StringBuffer();

        param.append("appid=" + Version.getAppid());

        //  param.append( ","+SpeechConstant.LIB_NAME_32+"=myMscName" );

        SpeechUtility.createUtility(param.toString());

        final VoiceSpeech t = new VoiceSpeech();
        // 创建定时任务
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        t.startBtnActionPerformed(null);
                    }
                });
            }
        }, 1, 300, TimeUnit.MILLISECONDS);
    }
}

class VoiceSpeech extends Frame implements ActionListener {
    public static final Location location=new Location("LOC001","学校操场","normal") ;
    public volatile boolean isSent=false;

    Button startBtn;

    Button stopBtn;

    TextArea textArea;

    // 语音听写对象
    SpeechRecognizer speechRecognize;

    private static final String DEF_FONT_NAME = "宋体";

    private static final int DEF_FONT_STYLE = Font.BOLD;

    private static final int DEF_FONT_SIZE = 30;

    private static final int TEXT_COUNT = 100;

    public VoiceSpeech() {

// 初始化听写对象

        speechRecognize = SpeechRecognizer.createRecognizer();

        startBtn = new Button("start");

        stopBtn = new Button("stop");

        textArea = new TextArea();
        textArea.setText("               ");
        Panel btnPanel = new Panel();

        Panel textPanel = new Panel();

        // Button startBtn = new Button("开始");

        //添加监听器

        startBtn.addActionListener(this);

        stopBtn.addActionListener(this);

        btnPanel.add(startBtn);

        btnPanel.add(stopBtn);

        textPanel.add(textArea);

        add(btnPanel);

        add(textPanel);

        // 设置窗体

        setLayout(new GridLayout(2, 1));

        setSize(400, 300);

        setTitle("语音识别");

        setLocation(200, 200);

        setVisible(true);


    }

    public void startBtnActionPerformed(ActionEvent e) {
        if (!speechRecognize.isListening()) {
            speechRecognize.startListening(recognizerListener);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            if (!speechRecognize.isListening()) {
                speechRecognize.startListening(recognizerListener);
            }
        } else if (e.getSource() == stopBtn) {

            speechRecognize.stopListening();

        }

    }

    /**
     * 听写监听器
     */

    private RecognizerListener recognizerListener = new RecognizerListener() {

        public void onBeginOfSpeech() {

            // DebugLog.Log( "onBeginOfSpeech enter" );

            // ((JLabel) jbtnRecognizer.getComponent(0)).setText("听写中...");

            // jbtnRecognizer.setEnabled(false);

        }

        public void onEndOfSpeech() {

//            DebugLog.Log("onEndOfSpeech enter");

        }

        /**
         * 获取听写结果. 获取RecognizerResult类型的识别结果，并对结果进行累加，显示到Area里

         */

        public void onResult(RecognizerResult results, boolean islast) {

//            DebugLog.Log("onResult enter");

            // 如果要解析json结果，请考本项目示例的 com.iflytek.util.JsonParser类

            String text =JsonParser.parseIatResult(results.getResultString());

            textArea.append(text);

            text = textArea.getText();

            if (SensitiveWordHelper.contains(text)) {
                DebugLog.Log("包含敏感词");
                try {
                    location.setStatus("abnormal");
                    netUtil.doPost("/bullying-alerts/warning", JSON.toJSON(new WaningMessage(location,text)).toString());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }


            if (null != text) {

                int n = text.length() / TEXT_COUNT + 1;

                int fontSize = Math.max(10, DEF_FONT_SIZE - 2 * n);

                DebugLog.Log("onResult new font size=" + fontSize);

                int style = n > 1 ? Font.PLAIN : DEF_FONT_SIZE;

                Font newFont = new Font(DEF_FONT_NAME, style, fontSize);

                textArea.setFont(newFont);

            }

            if (islast) {

                iatSpeechInitUI();

            }
            textArea.setText("               ");

        }


        public void onVolumeChanged(int volume) {

            //DebugLog.Log("onVolumeChanged enter");

            if (volume == 0)

                volume = 1;

            else if (volume >= 6)

                volume = 6;

            // labelWav.setIcon(new ImageIcon("res/mic_0" + volume + ".png"));

        }

        public void onError(SpeechError error) {

//            DebugLog.Log("onError enter");

            if (null != error) {

                DebugLog.Log("onError Code：" + error.getErrorCode());

                textArea.setText(error.getErrorDescription(true));

                iatSpeechInitUI();

            }

        }

        public void onEvent(int eventType, int arg1, int agr2, String msg) {

//            DebugLog.Log("onEvent enter");

        }

    };

    /**
     * 听写结束，恢复初始状态
     */

    public void iatSpeechInitUI() {

        // labelWav.setIcon(new ImageIcon("res/mic_01.png"));

        // jbtnRecognizer.setEnabled(true);

        // ((JLabel) jbtnRecognizer.getComponent(0)).setText("开始听写");

    }


}

