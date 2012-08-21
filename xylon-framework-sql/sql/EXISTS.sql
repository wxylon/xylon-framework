-- 获取每个actionName值相同的一列
-- logId > t.logId 获取最大
-- logId < t.logId 获取最小
-- 类似于group by actionName, 并且取每组logId最大或者最小的数据的功能
-- 参考：http://www.dldw.net/archives/mysql-explain-%E8%AF%A6%E7%BB%86%E8%A7%A3%E9%87%8A.html
-- 参考：http://www.huanxiangwu.com/192/mysql%E4%B8%ADexplain%E8%A7%A3%E9%87%8A%E5%91%BD%E4%BB%A4%E8%AF%A6%E8%A7%A3
EXPLAIN SELECT
  t.logId,
  t.operator,
  t.url,
  t.ipAddress,
  t.actionName,
  t.logDate
FROM log t
WHERE 
  NOT EXISTS(
	select 1 from log where logId > t.logId and actionName = t.actionName
)