-- ��ȡÿ��actionNameֵ��ͬ��һ��
-- logId > t.logId ��ȡ���
-- logId < t.logId ��ȡ��С
-- ������group by actionName, ����ȡÿ��logId��������С�����ݵĹ���
-- �ο���http://www.dldw.net/archives/mysql-explain-%E8%AF%A6%E7%BB%86%E8%A7%A3%E9%87%8A.html
-- �ο���http://www.huanxiangwu.com/192/mysql%E4%B8%ADexplain%E8%A7%A3%E9%87%8A%E5%91%BD%E4%BB%A4%E8%AF%A6%E8%A7%A3
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