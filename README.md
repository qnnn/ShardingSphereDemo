# ShardingSphereDemo
**依赖**

|               依赖                |  版本   |
| :-------------------------------: | :-----: |
|            spring-boot            |  2.5.1  |
|   spring-boot-starter-data-jpa    |  2.5.1  |
|             HikariCP              |  4.0.3  |
|       mysql-connector-java        | 8.0.25  |
| sharding-jdbc-spring-boot-starter |  4.1.1  |
|              lombok               | 1.18.20 |

## Sharding-JDBC

### 水平划分
![db](https://github.com/qnnn/ShardingSphereDemo/blob/main/photo/db.png?raw=trueg)
**数据库分片规则：**
+ 根据user_id的奇偶进行数据库选择

**表的分片规则：**
+ 根据主键的奇偶进行选择



### 垂直划分
