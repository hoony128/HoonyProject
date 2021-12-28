package kr.hoon.project.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import kr.hoon.project.vo.Info.InfoDAO;
import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.logcheck.LogCheckDAO;
import kr.hoon.project.vo.logcheck.LogCheckVO;
import kr.hoon.project.vo.logcheck.Paging;
import kr.hoon.project.vo.promotionresult.HistoryDAO;
import kr.hoon.project.vo.promotionresult.HistoryVO;

@Service
public class InfoService {
	
	@Autowired
	private InfoDAO infoDAO;
	
	@Autowired
	private LogCheckDAO logCheckDAO;
	
	@Autowired
	private HistoryDAO historyDAO;
	@Autowired // 메일 센터 자동 주입
	private JavaMailSenderImpl mailSender;
	
	public void insert (InfoVO vo) {
		if(vo!=null && vo.getId_emp() != 0) {
			infoDAO.insert(vo);
		}
	}
	
	public int getUseridCount(int id_emp) {
		 return infoDAO.idCount(id_emp);
	}
	
	public int loginCheck(int Id_emp,String password) {
		int idcount = infoDAO.idCount(Id_emp);
		String log_password= infoDAO.passwordCheck(Id_emp);
		String state = infoDAO.getState(Id_emp);
		
		if(idcount == 0 ) {
			return 1;
		}else if(idcount!=0 && !password.equals(log_password) ) {
			return 2;
		}else if(idcount!=0 && password.equals(log_password) && (!state.equals("재직")|| state.equals(null))) {
			return 3;
		}else {
			return 4;
		}
	}
	// 아이디 찾기
	public InfoVO selectByUserid(int Id_emp) {
		return infoDAO.selectByUserid(Id_emp);
	}

	
	
	// 정보 수정
	public void update (String first_phone,String mid_phone,String last_phone, String email, String zipcode, String addr1, String addr2, String bank, String account,int Id_emp,Date birth ) {
			infoDAO.updateInfo(first_phone, mid_phone, last_phone, email, zipcode, addr1, addr2, bank, account, Id_emp, birth);
	}
	// 로그인 기록
	public void logInR(int Id_emp, String ip) {
		logCheckDAO.login(Id_emp, ip);
	}
	// 로그아웃 기록
	public void logoutR(int Id_emp, String ip) {
		logCheckDAO.logout(Id_emp, ip);
	}
	
	//1페이지 가져오기(로그인 기록)
	public Paging<LogCheckVO> selectListLog(int currentPage,int pageSize,int blockSize,Integer id_emp){
		Paging<LogCheckVO> paging = null;

			if(id_emp !=null) {
				int totalCount = logCheckDAO.getCountIdEmp(id_emp);
				paging = new Paging<LogCheckVO>(totalCount, currentPage, pageSize, blockSize,id_emp);
				List<LogCheckVO> list = logCheckDAO.selectListIdEmp(paging.getStartNo(), paging.getEndNo(),id_emp);
				paging.setLists(list);
				return paging;
			}else {
				int totalCount = logCheckDAO.getCount();
				paging = new Paging<LogCheckVO>(totalCount, currentPage, pageSize, blockSize,0);
				List<LogCheckVO> list = logCheckDAO.selectList(paging.getStartNo(), paging.getEndNo());
				paging.setLists(list);
				return paging;
			}

		
	}
	
	public List<LogCheckVO> excelselectList(Integer Id_emp){		
		if(Id_emp !=null) {
			return logCheckDAO.excelselectListIdEmp(Id_emp);
		}else {
			return logCheckDAO.excelselectList();
		}
	}
	
	
	
	public List<InfoVO> selectAlldep(int depart){
		return infoDAO.selectAlldep(depart);
	}
	

	
	
	
	
	
	
	//1페이지 가져오기(부서별 임직원!)
	public List<InfoVO> selectListfindDept(int depart){
		List<InfoVO> list = infoDAO.selectAlldep(depart);
		return list;
	}
	
	// 1페이지 가져오기(전체 임직원)
	public List<InfoVO> selectListfindAll(){
		List<InfoVO> list = infoDAO.selectAllEmp();		
		return list;
	}
	
	public List<InfoVO> selectAllEMP(Integer depart){
		if(depart ==null) {
			return infoDAO.selectAllEmp();
		}else {
			return infoDAO.selectAlldep(depart);
		}
	}
	
	//myhistory
	public List<HistoryVO> myhistory(int Id_emp, Integer type){
		if(type == null) {
			return historyDAO.myhistoryAll(Id_emp);
		}else if(type == 1) {
			return historyDAO.myhistoryPro(Id_emp);
		}else {
			return historyDAO.myhistoryDep(Id_emp);
		}
	}
	
	//전사원이력기록
	public List<HistoryVO> history(Integer type){
		if(type ==null) {
			return historyDAO.historyAll();
		}else if(type ==1) {
			return historyDAO.historyPro();
		}else if(type ==2) {
			return historyDAO.historyRes();
		}else {
			return historyDAO.historyDep();
		}
	}
	
	public void changepassword(String password, int Id_emp) {
		infoDAO.changepassword(password, Id_emp);
	}
	
	// 아이디 찾기
	public int findid(String name, String email, String mid_phone, String last_phone) {
		
		try {
			return infoDAO.findid(name, email, mid_phone, last_phone);
		}catch(Exception e) {
			return -1;
		}
		
	}
	
	// 비밀번호찾기 검증
	public int pwcheck(int Id_emp, String name,String email) {
		int idcount = infoDAO.countpwfromname(Id_emp, name);
		String realEmail = infoDAO.selectemail(Id_emp);
		
		if(idcount==0) {
			return 1;
		}else if(idcount !=0 && !realEmail.equals(email)) {
			return 2;
		}else {
			return 3;
		}
	}
	public void mail(final int Id_emp ,final String email, final String name) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setFrom("hoony128@gmail.com");
				mimeMessage.setRecipient(Message.RecipientType.TO,
						new InternetAddress(email));
				mimeMessage.setSubject("project사입니다."+name + "님 비밀번호 변경을 위한 메일.");
				String key = "";
				for(int i=0;i<6;i++) key += (char)('a' + new Random().nextInt(26)); // 영문자로 10글자 생성
				infoDAO.updateKey(Id_emp, key); // 키를 디비에 저장
				StringBuffer sb = new StringBuffer();
				sb.append("다음의 링크를 클릭을하면 암호변경이 가능합니다.\n");
				sb.append("http://localhost:8080/project/resources/updatepassword?key="+key+"&Id_emp="+Id_emp+"\n");
				mimeMessage.setText(sb.toString());				
			}
		}; mailSender.send(preparator);
	}
	
	// 키값을 받기
	public String getKey(int Id_emp) {
		return infoDAO.getKey(Id_emp);
	}
	
	// temp2 삭제
	public void deletekey(int Id_emp) {
		infoDAO.updateKey(Id_emp, "");
	}
	
	public String realpw(int Id_emp) {
	 return infoDAO.passwordCheck(Id_emp);
	}
	
	
	
	
	
	
	
	
	//----------------여기서부터 엑셀 정렬
	public void excelogCheck(HttpServletResponse response, Integer Id_emp) {

	    // 게시판 목록조회
	    List<LogCheckVO> list = null;
	    
		if(Id_emp !=null) {
			list = logCheckDAO.excelselectListIdEmp(Id_emp);
		}else {
			list = logCheckDAO.excelselectList();
		}

	    // 워크북 생성
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("입출기록");
	    Row row = null;
	    Cell cell = null;
	    int rowNo = 0;

	    // 테이블 헤더용 스타일
	    CellStyle headStyle = wb.createCellStyle();
	    // 가는 경계선을 가집니다.
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);
	    // 배경색은 노란색입니다.
	    headStyle.setFillForegroundColor(HSSFColorPredefined.SKY_BLUE.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // 데이터는 가운데 정렬합니다.
	    headStyle.setAlignment(HorizontalAlignment.CENTER);

	    // 데이터용 경계 스타일 테두리만 지정
	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);

	    // 헤더 생성

	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("번호");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("사번");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("로그타입");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("날짜1");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("날짜2");
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("IP");


	    // 데이터 부분 생성

	    for(LogCheckVO vo : list) {
	        row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getIdx());
	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getId_emp());
	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(vo.getLogType()==1?"로그인":"로그아웃");
	        
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        try {
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	        	String date_s = sdf.format(vo.getLogDate());
				Date date = sdf.parse(date_s);
				SimpleDateFormat dt1= new SimpleDateFormat("yyyy-MM-dd");
				cell.setCellValue(dt1.format(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        cell = row.createCell(4);
	        cell.setCellStyle(bodyStyle);	     
	        cell.setCellValue((vo.getLogDate().getYear()+100)+"-"+(vo.getLogDate().getMonth()+1)+"-"+vo.getLogDate().getDate()+" "+vo.getLogDate().getHours()+":"+vo.getLogDate().getMinutes());
	        cell = row.createCell(5);
	        cell.setCellStyle(bodyStyle);	       
	        cell.setCellValue(vo.getIp());
	    }

	    // 컨텐츠 타입과 파일명 지정
	    response.setContentType("ms-vnd/excel");
	    if(Id_emp ==null) {
	    	try {
				response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("로그인이력","UTF-8")+".xls");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	    }else {
			try {
				response.setHeader("Content-Disposition", "attachment;filename="+Id_emp+URLEncoder.encode("로그인이력","UTF-8")+".xls");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	    }
	    // 엑셀 출력
	    try {
			wb.write(response.getOutputStream());
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public void exlefindemp(Integer depart2, HttpServletResponse response){
		List<InfoVO> list = null;
		int depart =0;
		if (depart2 == null) {
			list = infoDAO.selectAllEmp();
		} else {
			depart = depart2;
			list = infoDAO.selectAlldep(depart);
		}

// 워크북 생성
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("게시판");
		Row row = null;
		Cell cell = null;
		int rowNo = 0;

// 테이블 헤더용 스타일
		CellStyle headStyle = wb.createCellStyle();
// 가는 경계선을 가집니다.
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
// 배경색은 노란색입니다.

		headStyle.setFillForegroundColor(HSSFColorPredefined.SKY_BLUE.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

// 데이터는 가운데 정렬합니다.
		headStyle.setAlignment(HorizontalAlignment.CENTER);

// 데이터용 경계 스타일 테두리만 지정
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);

// 헤더 생성

		row = sheet.createRow(rowNo++);
		cell = row.createCell(0);
		cell.setCellStyle(headStyle);
		cell.setCellValue("부서");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("직급");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("직책");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("사번");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("이름");
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("이메일");
		cell = row.createCell(6);
		cell.setCellStyle(headStyle);
		cell.setCellValue("전화번호");

// 데이터 부분 생성

		for (InfoVO vo : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getDepart_d());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getLev_l());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getDuty_du());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getId_emp());
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getName());
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getEmail());
			cell = row.createCell(6);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getFirst_phone() + "-" + vo.getMid_phone() + "-" + vo.getLast_phone());
		}

// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		if (depart2 == null) {
			try {
				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode("전사원조회", "UTF-8") + ".xls");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
						(depart == 1 ? "개발" : (depart == 2 ? "총무" : (depart == 3 ? "개발" : (depart == 4 ? "회계" : "영업")))),
						"UTF-8") + URLEncoder.encode("사원이력", "UTF-8") + ".xls");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

// 엑셀 출력
		try {
			wb.write(response.getOutputStream());
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
}





