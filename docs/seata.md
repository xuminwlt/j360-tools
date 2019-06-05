seata 源码阅读

## 相关概念

**2PC**

1. TC
2. TM
3. RM

**seata**



## undoLog生成规则

1. 分析更新语句
```
update * from test where name like %match%

```

2. 查询更新语句对应的Rows
```
select * from test where name like %match%
```

3. 分析对应Rows对应的PK, 主键IDS
4. 以主键PK作为关键字,生成对应行数据到undoLog
5. 执行业务逻辑 进入Commit6 或者rollback7
6. 提交业务本地事务, 异步1S一次执行undoLog对应batch delete
7. 撤销回滚本地事务, 执行undoLog对应的PK, 并按照undoLog恢复之前数据, 最后删除undoLog

**Q&A**
性能问题

checkSql
```
private static final String CHECK_SQL_TEMPLATE = "SELECT * FROM %s WHERE %s in (%s)";
String checkSQL = String.format(CHECK_SQL_TEMPLATE, sqlUndoLog.getTableName(), pkName,
                   replace.substring(0, replace.length() - 1));
```
beforeImage:
根据主键PK+update columns进行log保存,此处做到了按需存储undo
```
public static TableRecords buildRecords(TableMeta tmeta, ResultSet resultSet) throws SQLException {
        TableRecords records = new TableRecords(tmeta);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        while (resultSet.next()) {
            List<Field> fields = new ArrayList<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                String colName = resultSetMetaData.getColumnName(i);
                ColumnMeta col = tmeta.getColumnMeta(colName);
                Field field = new Field();
                field.setName(col.getColumnName());
                if (tmeta.getPkName().equals(field.getName())) {
                    field.setKeyType(KeyType.PrimaryKey);
                }
                field.setType(col.getDataType());
                field.setValue(resultSet.getObject(i));
                fields.add(field);
            }

            Row row = new Row();
            row.setFields(fields);

            records.add(row);
        }
        return records;
    }
```


## 事务存储相关

1. 启动服务生成 sessionStore/root.data
2. 开始事务1 TC:DefaultCore.begin StoreMode:DB,FILE -> SessionStore

```
    /**
     * The constant ROOT_SESSION_MANAGER_NAME.
     */
    public static final String ROOT_SESSION_MANAGER_NAME = "root.data";
    /**
     * The constant ASYNC_COMMITTING_SESSION_MANAGER_NAME.
     */
    public static final String ASYNC_COMMITTING_SESSION_MANAGER_NAME = "async.commit.data";
    /**
     * The constant RETRY_COMMITTING_SESSION_MANAGER_NAME.
     */
    public static final String RETRY_COMMITTING_SESSION_MANAGER_NAME = "retry.commit.data";
    /**
     * The constant RETRY_ROLLBACKING_SESSION_MANAGER_NAME.
     */
    public static final String RETRY_ROLLBACKING_SESSION_MANAGER_NAME = "retry.rollback.data";
```