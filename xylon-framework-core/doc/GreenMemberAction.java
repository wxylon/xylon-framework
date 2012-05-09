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
 * ��ɫͨ�кŹ���action
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
	
	/** �����Ժ�չʾ����������list*/
	private List<GreenMemberVO> voList = new ArrayList<GreenMemberVO>();
	/** ��ȫƥ�������list*/
	private List<GreenMemberVO> addList = new ArrayList<GreenMemberVO>();
	/** �ֶ������û���Ӧ������*/
	private Map<String,String> map = new HashMap<String,String>();
	/** Excel�ϴ���Ӧ���û�����*/
	private Map<String,String> Emap = new HashMap<String,String>();	
	
	/**
	 * ��ɫͨ�к��б��ѯ
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
		
		/** �б�ҳ��ѯ��*/
		/** ʡ��*/
		List<Province> provinceList = Static.provinces;
		/** ��ҵ*/
		List<Industry> industryList = Static.industries;
		/** ��Ա����*/
		List<MemberType> memberTypeList = Static.memberTypes;
		/** �ؼ���*/
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
		
		/**ҳ��Сû������ʱ��Ĭ������Ϊ10*/
		if(pageBean.getPageSize() < 1) {
			pageBean.setPageSize(10); 
		}	
		if(greenMemberVO.getNameType()!=null && greenMemberVO.getNameType().equals(new BigDecimal(1))
				&& greenMemberVO.getMemberName()!=null && !"".equals(greenMemberVO.getMemberName())){/** ��˾����*/
			greenMemberVO.setEnterpriseName(greenMemberVO.getMemberName().trim());
		}
		if(greenMemberVO.getNameType()!=null && greenMemberVO.getNameType().equals(new BigDecimal(2))
				&& greenMemberVO.getMemberName()!=null && !"".equals(greenMemberVO.getMemberName())){/** ��ɫͨ�к�*/			
			greenMemberVO.setGreenNumber(greenMemberVO.getMemberName().trim());
		}		
		/** ��ҳ��ѯ*/
		Page page = greenMemberService.findList(greenMemberVO, pageBean);
		/** ��ȡ����*/
		List<GreenMemberVO> greenMemberlist = (List<GreenMemberVO>) page.getLstResult();
		
		PageBean bean = page.getPageBean();
		/** �Ѳ�ѯ������ʾ��ҳ��*/
		model.addAttribute("greenMemberlist", greenMemberlist);
		/** �ѷ�ҳ��Ϣ���Ե�ҳ��*/ 
		model.addAttribute("pageBean", bean);
		
		HttpSession session = request.getSession();
		session.setAttribute("greenMemberVO", greenMemberVO);
		
		return "/greenMember/green_list";		
	}
	
	/**
	 * ����ʡ��id��ѯ�ؼ���
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
		/** ���*/
		out.println(JsonUtil.object2json(cityList));	
		
		return null;
		
	}
	/**
	 * ����ɫͨ�к�״̬
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
		
		/** ��ɫͨ�к�id*/
		String greenId = request.getParameter("greenId");
		/** ��״̬*/
		String oldState = request.getParameter("oldState");
		/** ��״̬*/
		String newState = request.getParameter("newState");
		
		GreenJsonVO greenJsonVO = new GreenJsonVO();
		try{
			/**��ȡ��ɫͨ�к���Ϣ*/
			GreenMember greenMember = greenMemberService.getGreenById(Long.valueOf(greenId));			
			if(oldState.equals("0") && newState.equals("1")){/** ��Ч����Ч*/
				greenMember.setIsValid(BigDecimal.valueOf(1));
				greenMemberService.updateGreen(greenMember);
			}
			if(oldState.equals("1") && newState.equals("0")){/** ��Ч����Ч*/
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
			/** ��¼������־*/
			/** ��ȡ��Ա����*/
			UserSession userSession = GenericBean.getUserSession(request);
			String userName = userSession.getUsername();
			/** ��¼������־*/
			OperateLogVO operateLog = new OperateLogVO();
			operateLog.setOperateModule(BigDecimal.valueOf(2));
			operateLog.setOperateTime(new Date());
			if(oldState.equals("0") && newState.equals("1")){
				operateLog.setOperateType(BigDecimal.valueOf(13));
			}else{
				operateLog.setOperateType(BigDecimal.valueOf(14));
			}
			
			operateLog.setOperateName(userName);	
			
			operateLog.setOperateContent("��Ա��"+greenMember.getMemberName()+"��"+greenMember.getEnterpriseName()+"��");		
			
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
	 * ������ɫͨ�к�ҳ��
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
		/** ��ȡ�����̺ŵķ�ʽ*/		
		String greenType = greenMemberVO.getProduceType();			
		if(greenType=="" || greenType==null){
			greenMemberVO.setProduceType("0");			
		}		
		model.addAttribute("greenMemberVO",greenMemberVO);
		session.setAttribute("greenMemberVO", greenMemberVO);
		return "/greenMember/green_create";		
	}
	/**
	 * ������ɫͨ�к�
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
		
		if(greenType.equals("0")){/** Excel�ϴ�*/
			if(file.getFile().isEmpty()){
				model.addAttribute("isEmpty","��δ�ϴ��ļ�����������");
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
					    	 model.addAttribute("fileError","�ļ������⣬�����ļ�����");
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
						     DecimalFormat df = new DecimalFormat("0");/** ��ʽ�� number String �ַ�*/ 	   
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
					   /** ��ȡ��һ�±������*/ 
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
					    	 model.addAttribute("fileError","�ļ������⣬�����ļ�����");
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
						     DecimalFormat df = new DecimalFormat("0");/** ��ʽ�� number String �ַ� */						    
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
						/** ����mmt�ӿ�*/
						if(!createGreen.equals("")){					
							getMmtInfo(createGreen,greenType,request,model);
						}
					}else{
						 model.addAttribute("fileError","�ļ������⣬�����ļ�����");
						 voList = null;		
						 model.addAttribute("greenMemberVO",greenMemberVO);
						 return "/greenMember/green_create";
					}				

			 }catch(Exception ex){
				 ex.printStackTrace();
				 model.addAttribute("fileError","�ļ������⣬�����ļ�����");
				 voList = null;
			 }
			
		}else if(greenType.equals("1")){/** �ֶ�����*/
			/** ��ȡ�ַ���������������*/			
			model.addAttribute("memberName",request.getParameter("memberName"));
			String memberName = request.getParameter("memberName").replaceAll("��", ";");			
			if(memberName.equals("")){
				model.addAttribute("isEmpty","��δ�������ݣ���������");
				voList = null;	
				greenMemberVO.setMemberName(greenMemberVO.getMemberName());
				model.addAttribute("greenMemberVO",greenMemberVO);
				return "/greenMember/green_create";
			}			
			if(memberName!=null && memberName.length()>0){
				String[] memberStr = memberName.split(";");				
				if(memberStr.length>10){/** ������û�������10��*/					
					model.addAttribute("memLength",1);	
					voList = null;
				}else{
					String createGreen = CreateString(memberStr,greenType,model);
					/** ����mmt�ӿ�*/
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
	 * ��֤��ɫͨ�к���������
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
				
					
				/** �ж��û����ڱ����Ƿ����*/
				GreenMemberVO greenVO = greenMemberService.getGreenByName(gvo);
				/** �ж�����������Ƿ�ƥ��*/
				MemberType memberType = Static.MemTypeMap.get(membertype);
				GreenMemberVO green = new GreenMemberVO();
					
				if(memberType == null){/** ��Ա���Ͳ�ƥ��*/
					green.setMemberName(membername);
					green.setGreenNumber(Constants.MEMBERYPE_INVALID);					
					voList.add(green);							
				}else if(greenVO!=null){							
					if(greenVO.getIsValid().equals(new BigDecimal(1))){/** �����ѹ��ڵ���ɫͨ�к�*/
						green.setMemberName(membername);
						green.setGreenNumber(Constants.PAST_GREENNUM);
						green.setDueTime(greenVO.getDueTime());
						green.setEnterpriseName(greenVO.getEnterpriseName());
						voList.add(green);
					}else if(greenVO.getIsValid().equals(new BigDecimal(0))){/** ����δ���ڵ�ͨ�к�*/
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
				model.addAttribute("fileError","�ļ������⣬�����ļ�����");
			}else if(greenType.equals("1")){				
				model.addAttribute("shuruError","���������⣬������������");				
			}
			voList = null;
			return "";
		}
		
		
	}
	/**
	 * ���㵽��ʱ��
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
	 * ���ݴ�����û����ַ�����ȡmmt�ӿ�����
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
						gmvo.setMemberType(new BigDecimal(Emap.get(mmtvo.getItemName())));/** ���������л�ȡ*/
					}		
				}else if(greenType.equals("1")){
					if(map.get(mmtvo.getItemName())!=null && !"".equals(map.get(mmtvo.getItemName()))){
						gmvo.setMemberType(new BigDecimal(map.get(mmtvo.getItemName())));/** ���������л�ȡ*/
					}		
				}
					
				
				if(mmtvo.getUserid()!=null && !"".equals(mmtvo.getUserid()) &&
					mmtvo.getProviderid()!=null && !"".equals(mmtvo.getProviderid()) &&
					mmtvo.getProvince()!=null && !"".equals(mmtvo.getProvince()) && 
					mmtvo.getCity()!=null && !"".equals(mmtvo.getCity()) && 
					mmtvo.getName()!=null && !"".equals(mmtvo.getName()) && 
					mmtvo.getAreacode()!=null && !"".equals(mmtvo.getAreacode())){/** ��ȫƥ�������list*/
					addList.add(gmvo);				
				}else{
					gmvo.setGreenNumber(Constants.NOT_NAME);
					gmvo.setDueTime(null);
				}
				
				voList.add(gmvo);
			}
		}		
		
		/** ������ɫͨ�кţ�����Ҫ���������ӵ����ݿ�*/
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
				/** ��¼������־*/
				/** ��ȡ��Ա����*/
				UserSession userSession = GenericBean.getUserSession(request);
				String userName = userSession.getUsername();
				/** ��¼������־*/
				OperateLogVO operateLog = new OperateLogVO();
				operateLog.setOperateModule(BigDecimal.valueOf(2));
				operateLog.setOperateTime(new Date());
				operateLog.setOperateType(BigDecimal.valueOf(15));
				operateLog.setOperateName(userName);	
				
				operateLog.setOperateContent("����" +addList.size()+"����ɫͨ�к�");		
				
				operateLogService.add(operateLog);
			}
				
		}catch(Exception ex){
			ex.printStackTrace();
			if(greenType.equals("0")){				
				model.addAttribute("fileError","�ļ������⣬�����ļ�����");
			}else if(greenType.equals("1")){				
				model.addAttribute("shuruError","���������⣬������������");				
			}
			voList = null;
		}
		return greenType;			
	}
	/**
	 * Excel����
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
				/** ����һ��*/				
				HSSFRow row = sheet.createRow(++j);
				/** ������*/
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
		/** �ṩ����*/
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
	 * ��ʷ���ݴ���
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/history.action", method = {RequestMethod.GET,RequestMethod.POST})
	public String history(HttpServletRequest request,ModelMap model)throws Exception{
		/** ��ȡҪ�������ʷ����*/
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
					if(vo!=null){/** ��ȡ���ݲ�Ϊ�� ʱ���»�ȡ��������*/
						String type = green.getGreenNumber();
						String memberType = type.substring(type.length()-1,type.length());
						green.setDueTime(DateUtil.getDate("2013-2-09"));//���õ���ʱ��Ϊ����ʱ��+1��
						if(!"".equals(vo.getName()) && vo.getName()!=null){
							green.setEnterpriseName(vo.getName());//������ҵ����
						}
						if(!"".equals(vo.getProvince()) && vo.getProvince()!=null){
							green.setProvinceId(new BigDecimal(vo.getProvince()));//����ʡ��ID
						}
						if(!"".equals(vo.getCity()) && vo.getCity()!=null){
							green.setCityId(new BigDecimal(vo.getCity()));//���õؼ���ID
						}
						if(!"".equals(vo.getAreacode()) && vo.getAreacode()!=null){
							green.setIndustryId(new BigDecimal(vo.getAreacode()));//������ҵcode
						}
						if(!"".equals(vo.getProviderid()) && vo.getProviderid()!=null){
							green.setProviderid(new BigDecimal(vo.getProviderid()));//������ҵID
						}
						green.setMemberType(new BigDecimal(memberType));//���û�Ա����ID
						if(!"".equals(vo.getName()) && vo.getName()!=null
							&& !"".equals(vo.getProvince()) && vo.getProvince()!=null
							&& !"".equals(vo.getCity()) && vo.getCity()!=null
							&& !"".equals(vo.getAreacode()) && vo.getAreacode()!=null
							&& !"".equals(vo.getProviderid()) && vo.getProviderid()!=null){
							greenMemberService.updateHistory(green);
							sum++;
						}else{/** ��ȡ�������ݵ��û���*/
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
