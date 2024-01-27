package com.miner.xiaowangminer.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: TianYu.Wang
 * @Date: 2024/1/27 12:23
 */
public class MyService {
    public void executeBashScript() throws IOException, InterruptedException {
        //在代码中指定 Bash 的路径
        String bashPath = "C:\\Program Files\\Git\\bin\\bash.exe";
        // 指定脚本的路径
        String scriptPath = "D:\\gitlab\\xiaoWangMiner\\myscript.sh";

        // 构建执行脚本的命令
        String[] command = {bashPath, scriptPath};

        // 执行脚本
        Process process = new ProcessBuilder(command).start();
        // 处理输出流（标准输出）
        readAndPrintStream(process.getInputStream(), "Standard Output");

        // 处理错误流（标准错误）
        readAndPrintStream(process.getErrorStream(), "Standard Error");


        // 等待脚本执行完成
        int exitCode = process.waitFor();

        // 打印脚本执行结果
        System.out.println("Script executed with exit code: " + exitCode);
    }
    private static void readAndPrintStream(InputStream inputStream, String streamType) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        System.out.println("=== " + streamType + " ===");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("=================");
    }
}
