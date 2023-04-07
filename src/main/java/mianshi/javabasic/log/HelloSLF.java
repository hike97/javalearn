package mianshi.javabasic.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: hike97
 * @createTime: 2023/04/06 16:50
 * @description: TODO
 */
public class HelloSLF {
    public static void main(String[] args) {
        //生成一个日志 括号中有作为唯一id区分
        Logger logger = LoggerFactory.getLogger(HelloSLF.class);
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        //slf4j 需要具体实现 例如logback logback 中已经集成了slf4j 所以不用特意引用 slf4j的gav
    }
}
