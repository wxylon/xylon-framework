/**
  CONVERT 函数
  定义和用法
    CONVERT() 函数是把日期转换为新数据类型的通用函数。
    CONVERT() 函数可以用不同的格式显示日期/时间数据。
  语法
    CONVERT(data_type(length),data_to_be_converted,style)
    data_type(length) 规定目标数据类型（带有可选的长度）。data_to_be_converted 含有需要转换的值。style 规定日期/时间的输出格式。
  ref:http://www.w3school.com.cn/sql/func_convert.asp
*/
select CONVERT(VARCHAR(10),GETDATE(), 120)
