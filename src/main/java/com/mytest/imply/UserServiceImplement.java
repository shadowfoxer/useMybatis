package com.mytest.imply;

import com.mytest.dao.UserMapper;
import com.mytest.domain.User;
import com.mytest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * implement
 *
 * @author
 * @create 2018-05-05 下午4:55
 **/

@Service
public class UserServiceImplement {

    private UserMapper userMapper;

    /**
     * JVM Property: config path
     */
    private static final String PROP_CONFIG_PATH = "config.path";
    private static final String CONFIG_PATH = "classpath:spring/spring-mvc.xml";
    private static ApplicationContext context;

    /**
     * 初始化 spring
     * @param confPath
     * @return
     */
    private static ApplicationContext buildApplicationContext(String confPath) {

        // check config path
        String configFilePath = System.getProperty(PROP_CONFIG_PATH, confPath);
        // resource loader
        ResourceLoader resourceLoader = new DefaultResourceLoader ();
        // Load resource
        org.springframework.core.io.Resource resource = resourceLoader.getResource(configFilePath);
        // if path is not exists
        if (!resource.exists()) {
            throw new IllegalArgumentException("config path: " + configFilePath + " does not exist!");
        }
        // build application context
        return new GenericXmlApplicationContext (resource);
    }

    /**
     * 启动 spring
     */
    private static void init() {
        context = buildApplicationContext(CONFIG_PATH);
    }



    public User findById(){
        return userMapper.selectByPrimaryKey (2);
    }

    public void insert(){
        User u = new User ();
        u.setUserId ( 2 );
        u.setUserName ( "abd" );
//        userMapper.select ();
        userMapper.insert ( u );
    }


    public static void main(String[] args){

        init ();

        System.out.println ( "hello" );
        UserServiceImplement ser = new UserServiceImplement ();
        ser.setUserMapper ( context.getBean ( UserMapper.class ) );
        System.out.println ( ser );
        System.out.println ( ser.userMapper );
//        ser.insert ();

//
        User t = ser.findById ();
        System.out.println ( t.getUserName () );

    }

    public UserMapper getUserMapper ( ) {
        return userMapper;
    }

    public void setUserMapper ( UserMapper userMapper ) {
        this.userMapper = userMapper;
    }
}
