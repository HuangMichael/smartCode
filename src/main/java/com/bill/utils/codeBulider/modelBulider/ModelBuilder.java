package com.bill.utils.codeBulider.modelBulider;

import com.bill.common.Constant;
import com.bill.utils.codeBulider.DBUtil;
import com.bill.coder.ColumnInfo;
import com.bill.coder.TableInfo;
import com.bill.utils.SpellUtil;
import com.bill.utils.TimeUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;


/**
 * model类生成器
 */

@Component
public class ModelBuilder {

    /**
     * modelVM路径
     */
    private java.lang.String modelVMPath = "templates/coder/java/Domain.vm";

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DBUtil.getDataSource());


    public void createFile(String packagePath, String tableName) throws Exception {
        FileWriter fw = null;
        String fileName = packagePath.endsWith(File.separator) ? packagePath + SpellUtil.toPascalCase(tableName) +
                Constant.suffix : packagePath + File.separator + SpellUtil.toPascalCase(tableName) + Constant.suffix;
        File file = new File(fileName);
        String code = createCode(tableName);
        try {
            fw = new FileWriter(file);
            fw.write(code);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }

    /**
     * 根据模板生成代码
     *
     * @param tableName 数据表名称
     * @return
     * @throws Exception
     */
    public String createCode(String tableName) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(modelVMPath);
        VelocityContext velocityContext = new VelocityContext();
        TableInfo tableInfo = getTableInfoByTableName(tableName);
        velocityContext.put("tableInfo", tableInfo);
        velocityContext.put("className", tableInfo.getClassName());
        velocityContext.put("date", getDate());
        velocityContext.put("author", getAuthor());
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 封装数据表信息
     *
     * @param tableName
     * @return
     */
    private TableInfo getTableInfoByTableName(String tableName) {
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);
        tableInfo.setClassName(SpellUtil.toPascalCase(tableName));
        try {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableName, "%");
            ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(null, null, tableName);
            Map<String, ColumnInfo> columnInfoMap = new HashMap<String, ColumnInfo>();
            Map<String, ColumnInfo> primaryKeys = new HashMap<String, ColumnInfo>();
            while (primaryKeysResultSet.next()) {
                String columnName = primaryKeysResultSet.getString("COLUMN_NAME");//列名
                ColumnInfo columnInfo = new ColumnInfo();
                primaryKeys.put(columnName, columnInfo);
            }
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");//列名
                ColumnInfo columnInfo = getColumnInfo(columns, columnName);
                if (primaryKeys.get(columnName) == null) {
                    columnInfoMap.put(columnName, columnInfo);
                } else {
                    primaryKeys.put(columnName, columnInfo);
                }
            }
            tableInfo.setPrimaryKeys(primaryKeys);
            tableInfo.setColumnInfo(columnInfoMap);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfo;
    }

    /**
     * 解析result set并封装
     *
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    private ColumnInfo getColumnInfo(ResultSet rs, String columnName) throws SQLException {
        ColumnInfo columnInfo = new ColumnInfo();
        int dataType = rs.getInt("DATA_TYPE"); //对应的java.sql.Types类型
        String remarks = rs.getString("REMARKS");//列描述
        columnInfo.setColumnName(columnName);
        columnInfo.setColumnDesc(remarks);
        columnInfo.setColumnType(dataType);
        columnInfo.setFieldType(transferColumnType(dataType));
        columnInfo.setFieldName(SpellUtil.toCamelCase(columnName));
        columnInfo.setPascalCaseFieldName(SpellUtil.toPascalCase(columnName));
        return columnInfo;
    }

    /**
     * 从DB类型转换成java类型
     *
     * @param dataType
     * @return
     */
    private String transferColumnType(int dataType) {
        String columnType = "java.lang.String";
        switch (dataType) {
            case Types.DOUBLE:
                columnType = "java.lang.Double";
                break;
            case Types.INTEGER:
                columnType = "java.lang.Integer";
                break;
            case Types.DATE:
            case Types.TIMESTAMP:
                columnType = "java.util.Date";
                break;
            case Types.BOOLEAN:
                columnType = "java.lang.Boolean";
                break;
            case Types.BLOB:
                throw new RuntimeException("暂不支持blob类型...");
        }
        return columnType;
    }

    public String getDate() {
        String date = TimeUtil.currentTime(TimeUtil.FORMATOR_YMD_POINT);
        return date;
    }

    public String getAuthor() {
        String author = System.getProperty("user.name");
        return author;
    }
}
