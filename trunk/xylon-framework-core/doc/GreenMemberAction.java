package com.hc360.hcloan.action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hc360.core.JsonUtil;
import com.hc360.hcloan.Static;
import com.hc360.hcloan.bean.Page;
import com.hc360.hcloan.bean.PageBean;
import com.hc360.hcloan.po.City;
import com.hc360.hcloan.po.GreenMember;
import com.hc360.hcloan.po.Industry;
import com.hc360.hcloan.po.MemberType;
import com.hc360.hcloan.po.Province;
import com.hc360.hcloan.service.GreenMemberService;
import com.hc360.hcloan.service.MmtGreenService;
import com.hc360.hcloan.service.OperateLogService;
import com.hc360.hcloan.util.Constants;
import com.hc360.hcloan.util.DateUtil;
import com.hc360.hcloan.util.FileUploadBean;
import com.hc360.hcloan.util.GenericBean;
import com.hc360.hcloan.util.UserSession;
import com.hc360.hcloan.vo.GreenJsonVO;
import com.hc360.hcloan.vo.GreenMemberVO;
import com.hc360.hcloan.vo.MmtVO;
import com.hc360.hcloan.vo.OperateLogVO;

/**
 * 绿色通行号管理action
 * @author panly
 * @date 2012-1-2
 */
@Controller
@Scope("prototype")
public class GreenMemberAction {

	@Resource
	GreenMemberService greenMemberService;
	@Resource
	OperateLogService operateLogService;	
	@Resource	
	MmtGreenService mmtService;
	
	/** 生成自后展示的所有数据list*/
	private List<GreenMemberVO> voList = new ArrayList<GreenMemberVO>();
	/** 完全匹配的数据list*/
	private List<GreenMemberVO> addList = new ArrayList<GreenMemberVO>();
	/** 手动输入用户对应的类型*/
	private Map<String,String> map = new HashMap<String,String>();
	/** Excel上传对应的用户类型*/
	private Map<String,String> Emap = new HashMap<String,String>();	
	
	/**
	 * 绿色通行号列表查询
	 */
	@RequestMapping(value = "/greenMemberList.action", method = {RequestMethod.GET,RequestMethod.POST})
	public String list(HttpServletRequest request,@ModelAttribute("pageBean")PageBean pageBean,@ModelAttribute("greenMemberVO")GreenMemberVO greenMemberVO,ModelMap model)throws Exception{
		String dueTimeStart1 = request.getParameter("dueTimeStart1");
		String dueTimeEnd1 = request.getParameter("dueTimeEnd1");
		String nameType1 = request.getParameter("nameType1");
		String memberName1 = request.getParameter("memberName1");
		String isValid1 = request.getParameter("isValid1");
		String provinceId1 = request.getParameter("provinceId1");
		String cityId1 = request.getParameter("cityId1");
		String industryId1 = request.getParameter("industryId1");
		String memberType1 = request.getParameter("memberType1");
		String isBinding1 = request.getParameter("isBinding1");
		if(dueTimeStart1 !=null && !"".equals(dueTimeStart1.trim())){
			greenMemberVO.setDueTimeStart(dueTimeStart1.trim());
		}
		if(dueTimeEnd1 !=null && !"".equals(dueTimeEnd1.trim())){
			greenMemberVO.setDueTimeEnd(dueTimeEnd1.trim());
		}
		if(nameType1!=null && !"".equals(nameType1.trim())){
			greenMemberVO.setNameType(new BigDecimal(nameType1.trim()));
		}
		if(memberName1 !=null && !"".equals(memberName1.trim())){
			greenMemberVO.setMemberName(memberName1.trim());
		}
		if(isValid1 !=null && !"".equals(isValid1.trim())){
			greenMemberVO.setIsValid(new BigDecimal(isValid1.trim()));
		}
		if(provinceId1 !=null && !"".equals(provinceId1.trim())){
			greenMemberVO.setProvinceId(new BigDecimal(provinceId1.trim()));
		}
		if(cityId1 !=null && !"".equals(cityId1.trim())){
			greenMemberVO.setCityId(new BigDecimal(cityId1.trim()));
		}
		if(industryId1 !=null && !"".equals(industryId1.trim())){
			greenMemberVO.setIndustryId(new BigDecimal(industryId1.trim()));
		}
		if(memberType1 !=null && !"".equals(memberType1.trim())){
			greenMemberVO.setMemberType(new BigDecimal(memberType1.trim()));
		}
		if(isBinding1 !=null && !"".equals(isBinding1.trim())){
			greenMemberVO.setIsBinding(new BigDecimal(isBinding1.trim()));
		}
		
		/** 列表页查询项*/
		/** 省份*/
		List<Province> provinceList = Static.provinces;
		/** 行业*/
		List<Industry> industryList = Static.industries;
		/** 会员类型*/
		List<MemberType> memberTypeList = Static.memberTypes;
		/** 地级市*/
		String provinceId = request.getParameter("provinceId");			
		List<City> citys = Static.citys;
		List<City> cityList = new ArrayList<City>();
		
		if(provinceId!=null && citys!=null && citys.size()>0){
			for(int i=0;i<citys.size();i++){
				Long id = citys.get(i).getProvinceId().longValue();
				if(id==Long.valueOf(provinceId)){
					cityList.add(citys.get(i));
				}
			}
		}
		if(provinceId1 !=null && !"".equals(provinceId1.trim()) && citys!=null && citys.size()>0){
			for(int i=0;i<citys.size();i++){
				Long id = citys.get(i).getProvinceId().longValue();
				if(id==Long.valueOf(provinceId1)){
					cityList.add(citys.get(i));
				}
			}
		}
		model.addAttribute("cityList",cityList);
		model.addAttribute("provinceList",provinceList);
		model.addAttribute("industryList",industryList);
		model.addAttribute("memberTypeList",memberTypeList);		
		
		/**页大小没有设置时，默认设置为10*/
		if(pageBean.getPageSize() < 1) {
			pageBean.setPageSize(10); 
		}	
		if(greenMemberVO.getNameType()!=null && greenMemberVO.getNameType().equals(new BigDecimal(1))
				&& greenMemberVO.getMemberName()!=null && !"".equals(greenMemberVO.getMemberName())){/** 公司名称*/
			greenMemberVO.setEnterpriseName(greenMemberVO.getMemberName().trim());
		}
		if(greenMemberVO.getNameType()!=null && greenMemberVO.getNameType().equals(new BigDecimal(2))
				&& greenMemberVO.getMemberName()!=null && !"".equals(greenMemberVO.getMemberName())){/** 绿色通行号*/			
			greenMemberVO.setGreenNumber(greenMemberVO.getMemberName().trim());
		}		
		/** 分页查询*/
		Page page = greenMemberService.findList(greenMemberVO, pageBean);
		/** 获取数据*/
		List<GreenMemberVO> greenMemberlist = (List<GreenMemberVO>) page.getLstResult();
		
		PageBean bean = page.getPageBean();
		/** 把查询数据显示到页面*/
		model.addAttribute("greenMemberlist", greenMemberlist);
		/** 把分页信息回显到页面*/ 
		model.addAttribute("pageBean", bean);
		
		HttpSession session = request.getSession();
		session.setAttribute("greenMemberVO", greenMemberVO);
		
		return "/greenMember/green_list";		
	}
	
	/**
	 * 根据省份id查询地级市
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loan/ajaxCity.action")
	public String ajaxCity(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String provinceId = request.getParameter("provinceId");		
		List<City> citys = Static.citys;
		List<City> cityList = new ArrayList<City>();
		
		if(citys!=null && citys.size()>0){
			for(int i=0;i<citys.size();i++){
				Long id = citys.get(i).getProvinceId().longValue();
				if(id==Long.valueOf(provinceId)){
					cityList.add(citys.get(i));
				}
			}
		}		
		/** 输出*/
		out.println(JsonUtil.object2json(cityList));	
		
		return null;
		
	}
	/**
	 * 置绿色通行号状态
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loan/ajaxValid.action")
	public String isValid(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		/** 绿色通行号id*/
		String greenId = request.getParameter("greenId");
		/** 旧状态*/
		String oldState = request.getParameter("oldState");
		/** 新状态*/
		String newState = request.getParameter("newState");
		
		GreenJsonVO greenJsonVO = new GreenJsonVO();
		try{
			/**获取绿色通行号信息*/
			GreenMember greenMember = greenMemberService.getGreenById(Long.valueOf(greenId));			
			if(oldState.equals("0") && newState.equals("1")){/** 有效变无效*/
				greenMember.setIsValid(BigDecimal.valueOf(1));
				greenMemberService.updateGreen(greenMember);
			}
			if(oldState.equals("1") && newState.equals("0")){/** 无效变有效*/
				greenMember.setIsValid(BigDecimal.valueOf(0));	
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
				Date nowDate = new Date();	
				Date newDate = DateUtil.getDate(format.format(nowDate));
				Date duetime = DateUtil.getDate(format.format(greenMember.getDueTime()));				
				if(newDate.compareTo(duetime)<=0){
					greenMember.setDueTime(greenMember.getDueTime());
				}
				if(newDate.compareTo(duetime)>0){
					Date dueTime = getDueTime(new Date());
					greenMember.setDueTime(dueTime);
				}
				greenMemberService.updateGreen(greenMember);
				
			}
			/** 记录操作日志*/
			/** 获取会员名称*/
			UserSession userSession = GenericBean.getUserSession(request);
			String userName = userSession.getUsername();
			/** 记录操作日志*/
			OperateLogVO operateLog = new OperateLogVO();
			operateLog.setOperateModule(BigDecimal.valueOf(2));
			operateLog.setOperateTime(new Date());
			if(oldState.equals("0") && newState.equals("1")){
				operateLog.setOperateType(BigDecimal.valueOf(13));
			}else{
				operateLog.setOperateType(BigDecimal.valueOf(14));
			}
			
			operateLog.setOperateName(userName);	
			
			operateLog.setOperateContent("会员："+greenMember.getMemberName()+"（"+greenMember.getEnterpriseName()+"）");		
			
			operateLogService.add(operateLog);	
			greenJsonVO.setId(greenId);
			greenJsonVO.setOldState(oldState);
			greenJsonVO.setResult(1);
		}catch(Exception ex){
			greenJsonVO.setResult(0);
		}finally{
			out.println(JsonUtil.object2json(greenJsonVO));
		}
		if(out != null){
			out.flush();
			out.close();
		}		
		return null;		
	}
	
	
	/**
	 * 创建绿色通行号页面
	 * @param request
	 * @param pageBean
	 * @param greenMemberVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createMember.action", method = {RequestMethod.GET,RequestMethod.POST})
	public String createMember(HttpServletRequest request,@ModelAttribute("pageBean")PageBean pageBean,@ModelAttribute("greenMemberVO")GreenMemberVO greenMemberVO,ModelMap model)throws Exception{		
		HttpSession session = request.getSession();
		greenMemberVO = (GreenMemberVO) session.getAttribute("greenMemberVO");
		/** 获取生成绿号的方式*/		
		String greenType = greenMemberVO.getProduceType();			
		if(greenType=="" || greenType==null){
			greenMemberVO.setProduceType("0");			
		}		
		model.addAttribute("greenMemberVO",greenMemberVO);
		session.setAttribute("greenMemberVO", greenMemberVO);
		return "/greenMember/green_create";		
	}
	/**
	 * 生成绿色通行号
	 * @param request
	 * @param pageBean
	 * @param greenMemberVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMember.action", method = {RequestMethod.GET,RequestMethod.POST})
	public String addGreenMember(HttpServletRequest request,@ModelAttribute("pageBean")PageBean pageBean,@ModelAttribute("greenMemberVO")GreenMemberVO greenMemberVO,FileUploadBean file,ModelMap model)throws Exception{		
		HttpSession session = request.getSession();
		greenMemberVO = (GreenMemberVO) session.getAttribute("greenMemberVO");
		String greenType = request.getParameter("produceType");			
		greenMemberVO.setProduceType(greenType);
		
		if(greenType.equals("0")){/** Excel上传*/
			if(file.getFile().isEmpty()){
				model.addAttribute("isEmpty","还未上传文件，不能生成");
				voList = null;	
				model.addAttribute("greenMemberVO",greenMemberVO);
				return "/greenMember/green_create";
			}
			try{				
				String fileName = file.getFile().getFileItem().getName(); 
				String value = "";
				String extension = fileName.lastIndexOf(".")==-1?"":fileName.substring(fileName.lastIndexOf(".")+1); 
				if("xls".equals(extension)){ 
					 HSSFWorkbook hwb = new HSSFWorkbook(file.getFile().getFileItem().getInputStream()); 
					 HSSFSheet sheet = hwb.getSheetAt(0); 
					  
					 HSSFRow row = null; 
					 HSSFCell cell = null; 
					  
					 for(int i = sheet.getFirstRowNum();i<= sheet.getPhysicalNumberOfRows();i++){ 
					    row = sheet.getRow(i); 
					    if (row == null) { 
					     continue; 
					    }	
					    int rows = sheet.getLastRowNum();
					    if(rows==0){
					    	 model.addAttribute("fileError","文件有问题，请检查文件内容");
							 voList = null;		
							 model.addAttribute("greenMemberVO",greenMemberVO);
							 return "/greenMember/green_create";
					    }else if(rows >=50){
					    	 model.addAttribute("excelLength",1);
							 voList = null;
					    }else{
						    for (int j = row.getFirstCellNum(); j <2; j++) { 	    	
						     cell = row.getCell((short) (j)); 
						     if (cell == null) { 
						      continue; 
						     }						     
						     DecimalFormat df = new DecimalFormat("0");/** 格式化 number String 字符*/ 	   
						     switch (cell.getCellType()) { 
						     case XSSFCell.CELL_TYPE_STRING: 	      
						      value += cell.getStringCellValue()+" "; 	      
						      break; 
						     case XSSFCell.CELL_TYPE_NUMERIC:	   
						      value += df.format(cell.getNumericCellValue())+" "; 	     
						      break; 
						     } 
						     if (value == null || "".equals(value)) { 
						      continue; 
						     } 	    
						    
						   }					 
						    value +=";";					    
						 } 
					}					
				 }else if("xlsx".equals(extension)){ 
					 XSSFWorkbook xwb = new XSSFWorkbook(file.getFile().getFileItem().getInputStream());  
					   /** 读取第一章表格内容*/ 
					   XSSFSheet sheet = xwb.getSheetAt(0); 					  
					   XSSFRow row = null; 
					   XSSFCell cell = null; 
					   for (int i = sheet.getFirstRowNum(); i <= sheet 
					     .getPhysicalNumberOfRows(); i++) { 
					    row = sheet.getRow(i); 
					    if (row == null) { 
					     continue; 
					    } 	
					    int rows = sheet.getLastRowNum();
					    if(rows==0){
					    	 model.addAttribute("fileError","文件有问题，请检查文件内容");
							 voList = null;		
							 model.addAttribute("greenMemberVO",greenMemberVO);
							 return "/greenMember/green_create";
					    }else if(rows >=50){
					    	 model.addAttribute("excelLength",1);
							 voList = null;
					    }else{
						   for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) { 
						     cell = row.getCell((short) (j)); 
						     if (cell == null) { 
						      continue; 
						     } 
						     DecimalFormat df = new DecimalFormat("0");/** 格式化 number String 字符 */						    
						     switch (cell.getCellType()) { 
						     case XSSFCell.CELL_TYPE_STRING: 					     
						      value += cell.getStringCellValue()+" "; 
						      break; 
						     case XSSFCell.CELL_TYPE_NUMERIC: 				      
						      
						     value += df.format(cell.getNumericCellValue())+" "; 
						      
						      break;					    
						     } 
						     if (value == null || "".equals(value)) { 
						      continue; 
						     } 
						     
						    } 
						    value +=";";
						   }  
					   }
					}
					if(!value.equals("")){
						String[] memStr = value.split(" ;");					
						String createGreen = CreateString(memStr,greenType,model);
						/** 调用mmt接口*/
						if(!createGreen.equals("")){					
							getMmtInfo(createGreen,greenType,request,model);
						}
					}else{
						 model.addAttribute("fileError","文件有问题，请检查文件内容");
						 voList = null;		
						 model.addAttribute("greenMemberVO",greenMemberVO);
						 return "/greenMember/green_create";
					}				

			 }catch(Exception ex){
				 ex.printStackTrace();
				 model.addAttribute("fileError","文件有问题，请检查文件内容");
				 voList = null;
			 }
			
		}else if(greenType.equals("1")){/** 手动输入*/
			/** 获取字符串遍历输入内容*/			
			model.addAttribute("memberName",request.getParameter("memberName"));
			String memberName = request.getParameter("memberName").replaceAll("；", ";");			
			if(memberName.equals("")){
				model.addAttribute("isEmpty","还未输入内容，不能生成");
				voList = null;	
				greenMemberVO.setMemberName(greenMemberVO.getMemberName());
				model.addAttribute("greenMemberVO",greenMemberVO);
				return "/greenMember/green_create";
			}			
			if(memberName!=null && memberName.length()>0){
				String[] memberStr = memberName.split(";");				
				if(memberStr.length>10){/** 输入的用户名超过10个*/					
					model.addAttribute("memLength",1);	
					voList = null;
				}else{
					String createGreen = CreateString(memberStr,greenType,model);
					/** 调用mmt接口*/
					if(!createGreen.equals("")){					
						getMmtInfo(createGreen,greenType,request,model);
					}
					
				}
	
			}
			
		}
		if(voList!=null){
			model.addAttribute("recordCount",voList.size());
		}	
		if(voList!=null && voList.size()>0){
			model.addAttribute("voList",voList);
		}
		
		model.addAttribute("greenMemberVO",greenMemberVO);
		
		session.setAttribute("excellList", voList);	
		return "/greenMember/green_create";
		
	}	
	/**
	 * 验证绿色通行号生成数据
	 * @param memberStr
	 * @return
	 * @throws Exception
	 */
	public String CreateString(String[] memberStr,String greenType,ModelMap model){	
		StringBuffer sbf = new StringBuffer();	
		try{
			for(int i=0;i<memberStr.length;i++ ){						
				String name = memberStr[i];
				String[] names = name.split(" ");						
					
				String membername = names[0];
				String membertype = names[1].toString();
				if(greenType.equals("0")){
					Emap.put(membername, membertype);
				}else if (greenType.equals("1")){
					map.put(membername, membertype);
				}
				
				GreenMemberVO gvo = new GreenMemberVO();
				gvo.setMemberName(membername);
				try{
					gvo.setMemberType(new BigDecimal(membertype));
				}catch(Exception ex){					
					gvo.setMemberType(new BigDecimal(-1));
				}
				
					
				/** 判断用户名在本地是否存在*/
				GreenMemberVO greenVO = greenMemberService.getGreenByName(gvo);
				/** 判断输入的类型是否匹配*/
				MemberType memberType = Static.MemTypeMap.get(membertype);
				GreenMemberVO green = new GreenMemberVO();
					
				if(memberType == null){/** 会员类型不匹配*/
					green.setMemberName(membername);
					green.setGreenNumber(Constants.MEMBERYPE_INVALID);					
					voList.add(green);							
				}else if(greenVO!=null){							
					if(greenVO.getIsValid().equals(new BigDecimal(1))){/** 存在已过期的绿色通行号*/
						green.setMemberName(membername);
						green.setGreenNumber(Constants.PAST_GREENNUM);
						green.setDueTime(greenVO.getDueTime());
						green.setEnterpriseName(greenVO.getEnterpriseName());
						voList.add(green);
					}else if(greenVO.getIsValid().equals(new BigDecimal(0))){/** 存在未过期的通行号*/
						green.setMemberName(membername);
						green.setGreenNumber(Constants.NOT_PAST_GREENNUM);
						green.setDueTime(greenVO.getDueTime());
						green.setEnterpriseName(greenVO.getEnterpriseName());
						voList.add(green);
					}
				}else{
					sbf.append(membername+",");
				}	
					
			}
			if(!sbf.toString().equals("") && sbf.toString()!=null){
				return sbf.toString().substring(0, sbf.toString().lastIndexOf(","));
			}else{
				return "";
			}
		}catch(Exception ex){
			ex.printStackTrace();
			if(greenType.equals("0")){				
				model.addAttribute("fileError","文件有问题，请检查文件内容");
			}else if(greenType.equals("1")){				
				model.addAttribute("shuruError","输入有问题，请检查输入内容");				
			}
			voList = null;
			return "";
		}
		
		
	}
	/**
	 * 计算到期时间
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public Date getDueTime(Date date)throws Exception{
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		Calendar c=new GregorianCalendar();			
		c.setTime(date);					
		c.add(Calendar.YEAR,1); 					
		Date dueTime = DateUtil.getDate(df.format(c.getTime()));
		return dueTime;		
	}	
	/**
	 * 根据传入的用户名字符串获取mmt接口数据
	 * @param createGreen
	 * @throws Exception
	 */
	public String getMmtInfo(String createGreen,String greenType,HttpServletRequest request,ModelMap model)throws Exception{
		List<MmtVO> mmtvoList = mmtService.getMmtVOList(createGreen);
		if(mmtvoList!=null && mmtvoList.size()>0){
			for(int i=0;i<mmtvoList.size();i++){
				MmtVO mmtvo = mmtvoList.get(i);
				
				GreenMemberVO gmvo = new GreenMemberVO();
				if(mmtvo.getItemName()!=null && !"".equals(mmtvo.getItemName())){
					gmvo.setMemberName(mmtvo.getItemName());
				}
				if(mmtvo.getName()!=null && !"".equals(mmtvo.getName())){
					gmvo.setEnterpriseName(mmtvo.getName());
				}
				if(mmtvo.getProvince()!=null && !"".equals(mmtvo.getProvince())){
					gmvo.setProvinceId(new BigDecimal(mmtvo.getProvince()));
				}
				if(mmtvo.getCity()!=null && !"".equals(mmtvo.getCity())){
					gmvo.setCityId(new BigDecimal(mmtvo.getCity()));
				}
				if(mmtvo.getAreacode()!=null && !"".equals(mmtvo.getAreacode())){
					gmvo.setIndustryId(new BigDecimal(mmtvo.getAreacode()));
				}
				if(mmtvo.getProviderid()!=null && !"".equals(mmtvo.getProviderid())){
					gmvo.setProviderid(new BigDecimal(mmtvo.getProviderid()));
				}						
				gmvo.setIsBinding(new BigDecimal(0));
				gmvo.setIsValid(new BigDecimal(0));
				Date dueTime = getDueTime(new Date());
				gmvo.setDueTime(dueTime);
				if(greenType.equals("0")){
					if(Emap.get(mmtvo.getItemName())!=null && !"".equals(Emap.get(mmtvo.getItemName()))){
						gmvo.setMemberType(new BigDecimal(Emap.get(mmtvo.getItemName())));/** 输入内容中获取*/
					}		
				}else if(greenType.equals("1")){
					if(map.get(mmtvo.getItemName())!=null && !"".equals(map.get(mmtvo.getItemName()))){
						gmvo.setMemberType(new BigDecimal(map.get(mmtvo.getItemName())));/** 输入内容中获取*/
					}		
				}
					
				
				if(mmtvo.getUserid()!=null && !"".equals(mmtvo.getUserid()) &&
					mmtvo.getProviderid()!=null && !"".equals(mmtvo.getProviderid()) &&
					mmtvo.getProvince()!=null && !"".equals(mmtvo.getProvince()) && 
					mmtvo.getCity()!=null && !"".equals(mmtvo.getCity()) && 
					mmtvo.getName()!=null && !"".equals(mmtvo.getName()) && 
					mmtvo.getAreacode()!=null && !"".equals(mmtvo.getAreacode())){/** 完全匹配的数据list*/
					addList.add(gmvo);				
				}else{
					gmvo.setGreenNumber(Constants.NOT_NAME);
					gmvo.setDueTime(null);
				}
				
				voList.add(gmvo);
			}
		}		
		
		/** 生成绿色通行号，符合要求的数据添加到数据库*/
		try{
			if(addList!=null && addList.size()>0){
				for(int j=0;j<addList.size();j++){
					GreenMemberVO vo = addList.get(j);
					greenMemberService.addGreen(vo);
					
					GreenMemberVO greenVO = greenMemberService.getGreenByName(vo);
					Date nowDate = new Date();
					String result = new java.text.SimpleDateFormat("yyyyMMdd").format(nowDate);
					for(int k=0;k<voList.size();k++){
						GreenMemberVO mvo = voList.get(k);
						if(greenVO.getMemberName().equals(mvo.getMemberName())){
							voList.remove(mvo);		
							mvo.setGreenNumber(result+String.valueOf(greenVO.getId()));
							mvo.setMemberName(greenVO.getMemberName());
							mvo.setDueTime(greenVO.getDueTime());
							mvo.setEnterpriseName(greenVO.getEnterpriseName());
							voList.add(mvo);
						}
					}
					
				}
				/** 记录操作日志*/
				/** 获取会员名称*/
				UserSession userSession = GenericBean.getUserSession(request);
				String userName = userSession.getUsername();
				/** 记录操作日志*/
				OperateLogVO operateLog = new OperateLogVO();
				operateLog.setOperateModule(BigDecimal.valueOf(2));
				operateLog.setOperateTime(new Date());
				operateLog.setOperateType(BigDecimal.valueOf(15));
				operateLog.setOperateName(userName);	
				
				operateLog.setOperateContent("生成" +addList.size()+"条绿色通行号");		
				
				operateLogService.add(operateLog);
			}
				
		}catch(Exception ex){
			ex.printStackTrace();
			if(greenType.equals("0")){				
				model.addAttribute("fileError","文件有问题，请检查文件内容");
			}else if(greenType.equals("1")){				
				model.addAttribute("shuruError","输入有问题，请检查输入内容");				
			}
			voList = null;
		}
		return greenType;			
	}
	/**
	 * Excel导出
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="greenexcel.action")
	public String expExcel(HttpServletRequest req , ModelMap model,HttpServletResponse res ) throws Exception{	
		HttpSession session = req.getSession();
		List<GreenMemberVO> list = (List<GreenMemberVO>) session.getAttribute("excellList");	
		OutputStream output = null;
		POIFSFileSystem fs = new POIFSFileSystem(Thread.currentThread().getContextClassLoader().getResourceAsStream("templates/greenmember.xls"));
		HSSFWorkbook workbook =  new HSSFWorkbook(fs);
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		if(list!=null && list.size()>0){
			int j=0;
			for(int i=0;i<list.size();i++){
				GreenMemberVO greenVO = (GreenMemberVO)list.get(i);
				/** 创建一行*/				
				HSSFRow row = sheet.createRow(++j);
				/** 创建列*/
				HSSFCell cell = row.createCell((short)(0));	
				HSSFRichTextString str = new HSSFRichTextString(greenVO.getMemberName());
				cell.setCellValue(str);
				HSSFCell cell1 = row.createCell((short)(1));
				HSSFRichTextString str1 = new HSSFRichTextString(greenVO.getEnterpriseName());
				cell1.setCellValue(str1);
				HSSFCell cell2 = row.createCell((short)(2));
				HSSFRichTextString str2 = new HSSFRichTextString(greenVO.getGreenNumber());
				cell2.setCellValue(str2);
				HSSFCell cell3 = row.createCell((short)(3));
				HSSFRichTextString str3 = new HSSFRichTextString(DateUtil.getDateStr(greenVO.getDueTime(), "yyyy-MM-dd"));
				cell3.setCellValue(str3);
			}
		}
		/** 提供下载*/
		res.reset();
		res.setContentType("application/x-download;charset=GB2312");
		res.setHeader("Content-Disposition", "attachment;filename=greenmember.xls");
			
		output = res.getOutputStream();
		workbook.write(output);
		output.flush();
		output.close();		
		return null;
	}
	/**
	 * 历史数据处理
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/history.action", method = {RequestMethod.GET,RequestMethod.POST})
	public String history(HttpServletRequest request,ModelMap model)throws Exception{
		/** 获取要处理的历史数据*/
		StringBuffer invalid = new StringBuffer();
		List<GreenMember> gmList = greenMemberService.getHistoryList();
		if(gmList!=null && gmList.size()>0){
			int sum = 0;
			for(int i=0;i<gmList.size();i++){
				GreenMember green = gmList.get(i);
				String memberName = green.getMemberName();
				if(green!=null && !"".equals(memberName)){
					List<MmtVO> mmtvo = mmtService.getMmtVOList(memberName);
					MmtVO vo = mmtvo.get(0);
					if(vo!=null){/** 获取数据不为空 时更新获取到的数据*/
						String type = green.getGreenNumber();
						String memberType = type.substring(type.length()-1,type.length());
						green.setDueTime(DateUtil.getDate("2013-2-09"));//设置到期时间为上线时间+1年
						if(!"".equals(vo.getName()) && vo.getName()!=null){
							green.setEnterpriseName(vo.getName());//设置企业名称
						}
						if(!"".equals(vo.getProvince()) && vo.getProvince()!=null){
							green.setProvinceId(new BigDecimal(vo.getProvince()));//设置省份ID
						}
						if(!"".equals(vo.getCity()) && vo.getCity()!=null){
							green.setCityId(new BigDecimal(vo.getCity()));//设置地级市ID
						}
						if(!"".equals(vo.getAreacode()) && vo.getAreacode()!=null){
							green.setIndustryId(new BigDecimal(vo.getAreacode()));//设置行业code
						}
						if(!"".equals(vo.getProviderid()) && vo.getProviderid()!=null){
							green.setProviderid(new BigDecimal(vo.getProviderid()));//设置企业ID
						}
						green.setMemberType(new BigDecimal(memberType));//设置会员类型ID
						if(!"".equals(vo.getName()) && vo.getName()!=null
							&& !"".equals(vo.getProvince()) && vo.getProvince()!=null
							&& !"".equals(vo.getCity()) && vo.getCity()!=null
							&& !"".equals(vo.getAreacode()) && vo.getAreacode()!=null
							&& !"".equals(vo.getProviderid()) && vo.getProviderid()!=null){
							greenMemberService.updateHistory(green);
							sum++;
						}else{/** 获取不到数据的用户名*/
							invalid.append(memberName+";");
						}
						
					}
				}
			}
			model.addAttribute("sum",sum);
			model.addAttribute("invalid",invalid.toString());
		}		
		return "/greenMember/green_history";		
	}

}
