package com.example.springbootdemo;

import com.example.springbootdemo.mapper.goodhope.UserMapper;
import com.example.springbootdemo.pojo.goodhope.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
@EnableEurekaClient
@SpringBootApplication
public class SpringbootdemoApplication  {

	public final static Logger logger = LogManager.getLogger(SpringbootdemoApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

	@Autowired
	private UserMapper userMapper;

	/** 获取端口号 */
	String port="1003";

	/**
	 * 定义一个简单接口
	 * @param name
	 * @return
	 */
	@GetMapping("/hi/{name}")
	public String home(@PathVariable String name){
		logger.info("开始加载启动项目！！！！！！！！！！！！！！！！！！");
		System.out.println(userMapper.selectAllUser());
		return "hi " + name + ",I am from port :" + port;
	}
	/**
	 * 定义一个简单接口
	 * @param name
	 * @return
	 */
	@GetMapping("/hello")
	public String hello(String name,String accessToken){
		return "hello:name="+name+";accessToken="+accessToken;
	}
    @GetMapping("/user")
    public User getUser(String name, String accessToken){
        User user = new User();
        user.setUserName("姓名");
        user.setPassword("111111111111");
        return user;
    }
}
