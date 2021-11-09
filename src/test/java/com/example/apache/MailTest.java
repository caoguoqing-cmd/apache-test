package com.example.apache;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author caogq
 * @create 2021/8/5 18:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MailTest {

    @Test
    public void test01(){
        MailAccount account = new MailAccount();
        account.setHost("smtp.163.com");
        account.setPort(25);
        account.setAuth(true);
        account.setFrom("caogq2020@163.com");
        account.setUser("caogq2020");
        account.setPass("ETFIOTXJODRLJULI");
        String s = MailUtil.send(account, CollUtil.newArrayList("785853249@qq.com"), "罗成", "<h1>邮件来自Hutool测试</h1>", true);
        System.out.println("=============>"+s);
    }


}
