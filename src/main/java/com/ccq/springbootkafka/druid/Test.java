package com.ccq.springbootkafka.druid;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.hive.ast.HiveInsertStatement;
import com.alibaba.druid.sql.dialect.hive.stmt.HiveCreateTableStatement;
import com.alibaba.druid.util.JdbcConstants;

import java.util.List;

/********************************
 *** 解析 hive sql语句
 *** 一般的DML 和 DDL 语句都是支持的
 ***@Author chengchuanqiang
 ***@Date 2019/4/29 11:10
 ***@Version 1.0.0
 ********************************/
public class Test {

    public static void main(String[] args) {

        String sqlStr = "CREATE TABLE IF NOT EXISTS `your_table_name` (\n`Id_P` INT COMMENT '',\n`LastName` STRING COMMENT '',\n`FirstName` STRING COMMENT '',\n`Address` STRING COMMENT '',\n`City` STRING COMMENT '')\nCOMMENT '*' \nPARTITIONED BY (ds STRING)\nSTORED AS ORC;select 1,id,name,age from `user`;update `user` set `name`='11' where `id`=2; insert into table user select * from test;";

        try {
//            SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sqlStr, JdbcConstants.HIVE);
//            List<SQLStatement> sqlStatements = parser.parseStatementList();
            List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sqlStr, JdbcConstants.HIVE);
            for (SQLStatement statement : sqlStatements) {

                // hive的create语句
                if (statement instanceof HiveCreateTableStatement) {
                    System.out.println("-------create sql-----------");
                    HiveCreateTableStatement tableStatement = (HiveCreateTableStatement) statement;
                    List<SQLTableElement> tableElementList = tableStatement.getTableElementList();
                    System.out.println("******Column: ");
                    for (SQLTableElement sqlTableElement : tableElementList) {
                        SQLColumnDefinition sqlColumnDefinition = (SQLColumnDefinition) sqlTableElement;
                        System.out.println("name: " + sqlColumnDefinition.getName() + ", type: " + sqlColumnDefinition.getDataType() + ", comment: " + sqlColumnDefinition.getComment());
                    }
                    System.out.println();

                    List<SQLColumnDefinition> partitionColumns = tableStatement.getPartitionColumns();
                    System.out.println("******partition: ");
                    for (SQLColumnDefinition columnDefinition : partitionColumns) {
                        System.out.println("name: " + columnDefinition.getName() + ", type: " + columnDefinition.getDataType() + ", comment: " + columnDefinition.getComment());
                    }
                    System.out.println();

                    SQLName storedAs = tableStatement.getStoredAs();
                    System.out.println("******storedAs: " + storedAs.getSimpleName());
                }

                // hive的select语句
                if (statement instanceof SQLSelectStatement) {
                    System.out.println("\n-------select sql-----------");
                    SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) statement;
                    SQLSelectQueryBlock query = (SQLSelectQueryBlock) sqlSelectStatement.getSelect().getQuery();

                    List<SQLSelectItem> selectList = query.getSelectList();
                    System.out.println("*****selectList: " + selectList);
                    System.out.println();
                    SQLTableSource from = query.getFrom();
                    System.out.println("******from: " + from);
                    System.out.println();
                }

                // hive的update语句
                if (statement instanceof SQLUpdateStatement) {
                    SQLUpdateStatement sqlUpdateStatement = (SQLUpdateStatement) statement;
                }

                // hive的insert语句
                if (statement instanceof HiveInsertStatement) {
                    HiveInsertStatement hiveInsertStatement = (HiveInsertStatement) statement;
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}