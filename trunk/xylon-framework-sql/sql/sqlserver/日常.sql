-- 日期字符串，转换季度，年份
select
case 
	when SUBSTRING('2012-05-29 15:59:53.040', 6, 2) in (1,2,3) then 1
	when SUBSTRING('2012-05-29 15:59:53.040', 6, 2) in (4,5,6) then 2
	when SUBSTRING('2012-05-29 15:59:53.040', 6, 2) in (7,8,9) then 3
	when SUBSTRING('2012-05-29 15:59:53.040', 6, 2) in (10,11,12) then 4
	else 0
end quarter,
SUBSTRING('2012-05-29 15:59:53.040', 1, 4) year


-- 年份 和 季度的获取方式
select
LTRIM(DATEPART( yyyy, '2012-05-29 15:59:53.040')) + LTRIM(DATEPART(qq, '2012-05-29 15:59:53.040'))


-- 从数据类型 varchar 转换为 numeric 时出错。
select cast(b.jine as Decimal(20,4) from t
select * from t where isnumeric(jiage)=0


--分页
SELECT * 
FROM (
  SELECT T.*, ROW_NUMBER() OVER(ORDER BY auto_id) as ROWNUMBER
  FROM (
    SELECT * FROM TABLE_NAME
  ) T 
) K
WHERE ROWNUMBER BETWEEN 0 AND 10000


--字符串查找：待查找的字符串， 被查找的字符串， 起始位置（从1开始）
select Charindex('a','abcdefg',1) 

--转换为小写
select LOWER('aaAAds');

--转换为大写
select UPPER('aaAAds');

--判断是否为数据  ISNUMERIC('fsfds') = 0 为非数据， =1为数字
select ISNUMERIC('fsfds')

--生成报表错误:SqlMapClient operation; bad SQL grammar []; nested exception is com.ibatis.common.jdbc.exception.NestedSQLException:    
--- The error occurred in mappers/srcb_yk_chukufanghuofudanhuizong.xml.   
--- The error occurred while applying a parameter map.   
--- Check the yk_chukufanghuofudanhuizong.getChukufanghuofudanhuizong-InlineParameterMap.   
--- Check the results (failed to retrieve results).   
--- Cause: com.microsoft.sqlserver.jdbc.SQLServerException: 数据库 'tempdb' 的事务日志已满。若要查明无法重用日志中的空间的原因，请参阅 sys.databases 中的 log_reuse_wait_desc 列。
--收缩数据库 
dbcc   shrinkdatabase(auto2012Q4) 

--从过程缓存中删除所有元素
DBCC FREEPROCCACHE
--从缓冲池中删除所有清除缓冲区
DBCC DROPCLEANBUFFERS


