#二进制日志配置
vi /etc/my.cnf
  
#开启二进制日志
log-bin=mysql-bin
binlog_format=mixed
#二进制日志有效时间
expire_logs_days = 30

##清除mysql，binlog；
mysql> purge binary logs before 'date'


mysql> show binary logs;
+------------------+-----------+
| Log_name         | File_size |
+------------------+-----------+
| mysql-bin.000001 |      1932 |
| mysql-bin.000002 |       126 |
| mysql-bin.000003 |       107 |
+------------------+-----------+
3 rows in set (0.00 sec)


mysql> show variables like '%binlog%';
+-----------------------------------------+----------------------+
| Variable_name                           | Value                |
+-----------------------------------------+----------------------+
| binlog_cache_size                       | 32768                |
| binlog_direct_non_transactional_updates | OFF                  |
| binlog_format                           | MIXED                |
| binlog_stmt_cache_size                  | 32768                |
| innodb_locks_unsafe_for_binlog          | OFF                  |
| max_binlog_cache_size                   | 18446744073709547520 |
| max_binlog_size                         | 1073741824           |
| max_binlog_stmt_cache_size              | 18446744073709547520 |
| sync_binlog                             | 0                    |
+-----------------------------------------+----------------------+
9 rows in set (0.00 sec)


mysql> purge binary logs to 'mysql-bin.000001';
Query OK, 0 rows affected (0.10 sec)


