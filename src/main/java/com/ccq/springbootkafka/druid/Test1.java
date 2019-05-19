package com.ccq.springbootkafka.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.hive.ast.HiveInsertStatement;
import com.alibaba.druid.sql.dialect.hive.stmt.HiveCreateTableStatement;
import com.alibaba.druid.sql.dialect.hive.visitor.HiveOutputVisitor;
import com.alibaba.druid.sql.dialect.hive.visitor.HiveSchemaStatVisitor;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;

/********************************
 *** 使用解析器解析 hive sql
 *** 感觉使用不怎么好，自己解析感觉会更好
 ***@Author chengchuanqiang
 ***@Date 2019/4/29 11:10
 ***@Version 1.0.0
 ********************************/
public class Test1 {

    public static void main(String[] args) {

        String sqlStr = "CREATE TABLE IF NOT EXISTS `your_table_name` (\n`Id_P` INT COMMENT '',\n`LastName` STRING COMMENT '',\n`FirstName` STRING COMMENT '',\n`Address` STRING COMMENT '',\n`City` STRING COMMENT '')\nCOMMENT '*' \nPARTITIONED BY (ds STRING)\nSTORED AS ORC;select 1,id,name,age from `user` where `id` = 1 group by `name` limit 1;update `user` set `name`='11' where `id`=2; insert into table user select * from test;";

        try {
//            SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sqlStr, JdbcConstants.HIVE);
//            List<SQLStatement> sqlStatements = parser.parseStatementList();
            List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sqlStr, JdbcConstants.HIVE);
            for (SQLStatement statement : sqlStatements) {

                // hive的create语句
                if (statement instanceof HiveCreateTableStatement) {
                    System.out.println("-------create sql-----------");
                    HiveCreateTableStatement tableStatement = (HiveCreateTableStatement) statement;

                    HiveSchemaStatVisitor visitor = new HiveSchemaStatVisitor();
                    tableStatement.accept(visitor);

                    System.out.println(visitor.getColumns());
                }

                // hive的select语句
                if (statement instanceof SQLSelectStatement) {
                    System.out.println("-------select sql-----------");
                    SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) statement;

                    HiveSchemaStatVisitor visitor = new HiveSchemaStatVisitor();
                    sqlSelectStatement.accept(visitor);

                    System.out.println(visitor);
                }

                // hive的update语句
                if (statement instanceof SQLUpdateStatement) {
                    System.out.println("-------update sql-----------");
                    SQLUpdateStatement sqlUpdateStatement = (SQLUpdateStatement) statement;
                }

                // hive的insert语句
                if (statement instanceof HiveInsertStatement) {
                    System.out.println("-------insert sql-----------");
                    HiveInsertStatement hiveInsertStatement = (HiveInsertStatement) statement;
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}