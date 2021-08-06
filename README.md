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


**水平划分测试表**

```mysql
DROP TABLE IF EXISTS `course_1`;
CREATE TABLE `course_1`  (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;
```








### 垂直划分（多数据源）
### 主从同步
**垂直划分测试表**
```mysql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;
```
**一主两从**
[docker-compose部署mysql主从同步](https://blog.csdn.net/gybshen/article/details/115212432?ops_request_misc=&request_id=&biz_id=102&utm_term=docker%20compose%20mysql%E9%9B%86%E7%BE%A4&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~sobaiduweb~default-5-.pc_v2_rank_blog_default&spm=1018.2226.3001.4450)

