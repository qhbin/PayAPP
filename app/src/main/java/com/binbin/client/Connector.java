package com.binbin.client;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import com.binbin.commom.util.Constants;
import com.binbin.proto.IprojHeader;
import com.binbin.proto.IprojHeader.Msg;
import com.google.protobuf.ByteString;

import static com.binbin.commom.util.Constants.*;


/**
 * Created by Administrator on 2016/4/16.
 */
public class Connector {
    private Socket m_sock = null;
    private static final Connector m_instance = new Connector();
    private Connector(){
        try{
         m_sock = new Socket(Constants.SERVER_IP, Constants.SERVER_PORT);}
        /// m_sock = new Socket("192.168.1.107",8989);}
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Connector getInstance(){
        return m_instance;
    }


    public void sendMessage(IprojHeader.Header msg_header, com.google.protobuf.Message msg){
        Msg sendMsg =Msg.newBuilder().setHeader(msg_header)
                .setSerializedMsgBytes(msg.toByteString()).build();
        new SendThread(sendMsg,m_sock).start();
    }

    private  boolean recIsFinish =false;
    private  Msg msg = null;
    public ByteString receiveMessage(){
        ReceiveThread recThread=new ReceiveThread(m_sock);
        recThread.start();
        while (!recIsFinish){
        }
        System.out.println("读取成功");
        return  msg.getSerializedMsgBytes();
    }

    class SendThread extends Thread{
        private Msg msg = null;
        private Socket socket = null;
        public SendThread(Msg msg ,Socket socket){
            this.msg = msg;
            this.socket=socket;
        }
        @Override
        public void run() {
            try{
                socket.getOutputStream().write(msg.toByteArray());
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    class ReceiveThread extends Thread{
        private Socket socket;

        public ReceiveThread(Socket socket){
            this.socket=socket;
        }

        public void run(){
        try{
            InputStream  input = socket.getInputStream();
            byte[] receiveArray = new byte[1024];
            int len = input.read(receiveArray);
            byte[] msgArray = new byte[len];
            for (int i = 0;i < len ;i++)
                msgArray[i] = receiveArray[i];
                msg = Msg.parseFrom(msgArray);
                recIsFinish = true;
                input.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        }
    }
  }

