package com.miner.xiaowangminer.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: TianYu.Wang
 * @Date: 2024/1/27 13:39
 */
public class RemoteScriptExecutorService {
    public String executeScript(String host, int port, String username, String password, String script) {
        try {
            JSch jsch = new JSch();

            // 创建会话
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);

            // 配置不检查主机密钥
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // 连接
            session.connect();

            // 打开通道
            ChannelExec channel = (ChannelExec) session.openChannel("exec");

            // 设置执行的命令
            channel.setCommand(script);

            // 获取输入流（脚本的输出流）
            InputStream commandOutput = channel.getInputStream();

            // 连接通道
            channel.connect();

            // 读取脚本的输出
            StringBuilder outputBuffer = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = commandOutput.read(buffer)) > 0) {
                outputBuffer.append(new String(buffer, 0, bytesRead));
                System.out.print(new String(buffer, 0, bytesRead));
                System.out.flush();  // 及时刷新输出
            }

            // 关闭通道和会话
            channel.disconnect();
            session.disconnect();

            // 返回脚本输出
            return outputBuffer.toString();

        } catch (JSchException | java.io.IOException e) {
            e.printStackTrace();
            return "Error executing script: " + e.getMessage();
        }
    }
}
