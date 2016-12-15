package com.bill.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;


/**
 * huangbin
 */
public class TranslatorBuliderUtil {

    private static final String TEMPLATE_VM_PATH = "templates/views/list.vm";
    private static final String PACKAGE_PATH = "/home/huangbin/IdeaProjects/velocity/velocity/src/main/resources/templates/hello/";
    private static final String SUFFIX = ".html";



    /**
     * 创建bean的Service的实现<br>
     *
     * @throws Exception
     */
    public void createFile() throws Exception {
        String fileName = PACKAGE_PATH + getClassName() + SUFFIX ;
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(createCode(TEMPLATE_VM_PATH));
        fw.flush();
        fw.close();
    }

    /**
     * 根据模板生成代码
     *
     * @param fileVMPath 模板路径
     * @return
     * @throws Exception
     */
    public String createCode(String fileVMPath) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(fileVMPath);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tableName",getTableName());
        velocityContext.put("className",getClassName());
        velocityContext.put("date",getDate());
        velocityContext.put("isChangeTableName",getIsChangeTableName());
        velocityContext.put("midTableName",getMidTableName());
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 创建文件
     *
     * @param file
     */
    public void createFilePath(File file) {
        if (!file.exists()) {
            System.out.println("创建[" + file.getAbsolutePath() + "]情况：" + file.mkdirs());
        } else {
            System.out.println("存在目录：" + file.getAbsolutePath());
        }
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        return TimeUtil.currentTime(TimeUtil.FORMATOR_YMD_DOC);
    }

    public static String getTableName() {
        return "user";
    }

    public static boolean getIsChangeTableName() {
        return false;
    }

    public static String getMidTableName() {
        return "";
    }

    public static String getClassName() {
        String className = SpellUtil.toPascalCase(getTableName());
        return className;
    }
}
