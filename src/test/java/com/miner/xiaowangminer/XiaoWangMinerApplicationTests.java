package com.miner.xiaowangminer;

import com.miner.xiaowangminer.Service.MyService;
import com.miner.xiaowangminer.Service.RemoteScriptExecutorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class XiaoWangMinerApplicationTests {

    @Test
    void contextLoads() throws InterruptedException, IOException {
        MyService myService = new MyService();
        myService.executeBashScript();
    }
    @Test
    void test(){
        RemoteScriptExecutorService scriptExecutor = new RemoteScriptExecutorService();

        // 替换为你的 Linux 服务器信息和脚本内容
        String host = "172.30.222.168";
        int port = 22;
        String username = "root";
        String password = "root";
        String script = "D:\\gitlab\\xiaoWangMiner\\myscript.sh";

        String result = scriptExecutor.executeScript(host, port, username, password, script);
        System.out.println("Script execution result: " + result);
    }

}
