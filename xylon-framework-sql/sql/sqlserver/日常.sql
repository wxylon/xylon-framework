-- �����ַ�����ת�����ȣ����
select
case 
	when SUBSTRING('2012-05-29 15:59:53.040', 6, 2) in (1,2,3) then 1
	when SUBSTRING('2012-05-29 15:59:53.040', 6, 2) in (4,5,6) then 2
	when SUBSTRING('2012-05-29 15:59:53.040', 6, 2) in (7,8,9) then 3
	when SUBSTRING('2012-05-29 15:59:53.040', 6, 2) in (10,11,12) then 4
	else 0
end quarter,
SUBSTRING('2012-05-29 15:59:53.040', 1, 4) year


-- ��� �� ���ȵĻ�ȡ��ʽ
select
LTRIM(DATEPART( yyyy, '2012-05-29 15:59:53.040')) + LTRIM(DATEPART(qq, '2012-05-29 15:59:53.040'))


-- ���������� varchar ת��Ϊ numeric ʱ����
select cast(b.jine as Decimal(20,4) from t
select * from t where isnumeric(jiage)=0


--��ҳ
SELECT * 
FROM (
  SELECT T.*, ROW_NUMBER() OVER(ORDER BY auto_id) as ROWNUMBER
  FROM (
    SELECT * FROM TABLE_NAME
  ) T 
) K
WHERE ROWNUMBER BETWEEN 0 AND 10000


--�ַ������ң������ҵ��ַ����� �����ҵ��ַ����� ��ʼλ�ã���1��ʼ��
select Charindex('a','abcdefg',1) 

--ת��ΪСд
select LOWER('aaAAds');

--ת��Ϊ��д
select UPPER('aaAAds');

--�ж��Ƿ�Ϊ����  ISNUMERIC('fsfds') = 0 Ϊ�����ݣ� =1Ϊ����
select ISNUMERIC('fsfds')

--���ɱ������:SqlMapClient operation; bad SQL grammar []; nested exception is com.ibatis.common.jdbc.exception.NestedSQLException:    
--- The error occurred in mappers/srcb_yk_chukufanghuofudanhuizong.xml.   
--- The error occurred while applying a parameter map.   
--- Check the yk_chukufanghuofudanhuizong.getChukufanghuofudanhuizong-InlineParameterMap.   
--- Check the results (failed to retrieve results).   
--- Cause: com.microsoft.sqlserver.jdbc.SQLServerException: ���ݿ� 'tempdb' ��������־��������Ҫ�����޷�������־�еĿռ��ԭ������� sys.databases �е� log_reuse_wait_desc �С�
--�������ݿ� 
dbcc   shrinkdatabase(auto2012Q4) 

--�ӹ��̻�����ɾ������Ԫ��
DBCC FREEPROCCACHE
--�ӻ������ɾ���������������
DBCC DROPCLEANBUFFERS


