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