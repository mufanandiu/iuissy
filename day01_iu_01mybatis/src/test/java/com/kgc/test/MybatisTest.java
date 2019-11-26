package com.kgc.test;

import com.kgc.dao.IUserDao;
import com.kgc.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Mybatis入门案例
 */
public class MybatisTest {
    public static void main(String[]agrs) throws IOException {
        //1、读取被指文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2、创建SqlSessionFactory对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3、使用工厂生产sqlSession对象
        SqlSession session = factory.openSession();
        //4、使用sqlSession创建Dao接口的代理对象
        IUserDao dao = session.getMapper(IUserDao.class);
        //5、使用代理对象执行方法
        List<User> list = dao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
        //6、释放资源
        session.close();
        in.close();
    }
}
