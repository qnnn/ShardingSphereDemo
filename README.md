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



### 垂直划分（多数据源）


### 主从同步
**一主两从**


**docker-compose.yml配置**


```yaml
version: "3"

services:
  mysql-master:
    image: mysql:8.0.13
    container_name: mysql-master
    restart: unless-stopped
    environment:
      - MYSQL_ROOT_PASSWORD=123456
      - TZ=Asia/Shanghai
    ports:
      - "3306:3306"
    expose:
      - "3306"
    volumes:
      - ./mysql-master-data:/var/lib/mysql
      - ./init-db-sql/sakila-schema.sql:/docker-entrypoint-initdb.d/1-schema.sql
      - ./init-db-sql/sakila-data.sql:/docker-entrypoint-initdb.d/2-data.sql
      - ./init-db-sql/init-master.sh:/docker-entrypoint-initdb.d/3-init-master.sh
    command: [
      "--log-bin=mysql-bin",
      "--server-id=1",
      "--character-set-server=utf8mb4",
      "--collation-server=utf8mb4_unicode_ci",
      "--innodb_flush_log_at_trx_commit=1",
      "--sync_binlog=1"
      ]

  mysql-node-1:
    image: mysql:8.0.13
    container_name: mysql-node-1
    restart: unless-stopped
    environment:
      - MYSQL_ROOT_PASSWORD=123456
      - MASTER_MYSQL_ROOT_PASSWORD=123456
      - TZ=Asia/Shanghai
    ports:
      - "3307:3306"
    depends_on:
      - mysql-master
    volumes:
      - ./mysql-node-1-data:/var/lib/mysql
      - ./init-db-sql/sakila-schema.sql:/docker-entrypoint-initdb.d/1-schema.sql
      - ./init-db-sql/sakila-data.sql:/docker-entrypoint-initdb.d/2-data.sql
      - ./init-db-sql/init-node.sh:/docker-entrypoint-initdb.d/3-init-node.sh
    command: [
      "--server-id=10",
      "--character-set-server=utf8mb4",
      "--collation-server=utf8mb4_unicode_ci",
      ]

  mysql-node-2:
    image: mysql:8.0.13
    container_name: mysql-node-2
    restart: unless-stopped
    environment:
      - TZ=Asia/Shanghai
      - MYSQL_ROOT_PASSWORD=123456
      - MASTER_MYSQL_ROOT_PASSWORD=123456
    ports:
      - "3308:3306"
    volumes:
      - ./mysql-node-2-data:/var/lib/mysql
      - ./init-db-sql/sakila-schema.sql:/docker-entrypoint-initdb.d/1-schema.sql
      - ./init-db-sql/sakila-data.sql:/docker-entrypoint-initdb.d/2-data.sql
      - ./init-db-sql/init-node.sh:/docker-entrypoint-initdb.d/3-init-node.sh
    command: [
      "--server-id=20",
      "--character-set-server=utf8mb4",
      "--collation-server=utf8mb4_unicode_ci",
      ]
```

