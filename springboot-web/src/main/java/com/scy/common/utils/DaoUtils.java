package com.scy.common.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

/**
 * 类名： DaoUtils <br>
 * 描述： 。在订单系统中，我们使用 DaoUtils 工具类来完成 MyBatis 中 SqlSession 以及事务的相关操作<br>
 * 创建日期： 2021/10/8 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class DaoUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            System.err.println("read mybatis-config.xml failed");
            e.printStackTrace();
            System.exit(1);
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static <R> R execute(Function<SqlSession, R> function) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            R apply = function.apply(sqlSession);
            sqlSession.commit();
            return apply;
        } catch (Throwable throwable) {
            sqlSession.rollback();
            System.out.println("execute error");
            throw throwable;
        } finally {
            sqlSession.close();
        }
    }
}
