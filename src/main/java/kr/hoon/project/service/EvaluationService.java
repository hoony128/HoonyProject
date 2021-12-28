package kr.hoon.project.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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
import org.springframework.stereotype.Service;

import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.evaluation.EvaluationDAO;
import kr.hoon.project.vo.evaluation.EvaluationSaveVO;
import kr.hoon.project.vo.evaluation.EvaluationVO;
	


@Service
public class EvaluationService {
	@Autowired
	private EvaluationDAO evaluationDAO;
	
	public List<InfoVO> selectDep(int depart){
		return evaluationDAO.selectdep(depart);
	}

	public void evaluationsave(EvaluationSaveVO vo) {
		int Id_emp_v =vo.getId_emp_v();
		int count= evaluationDAO.getCountEvaluationsave(Id_emp_v);
		if(count==0) {
			evaluationDAO.evaluationsave(vo);
		}else {
			evaluationDAO.deleteEvaluationsave(Id_emp_v);
			evaluationDAO.evaluationsave(vo);
		}
	}
	
	public EvaluationSaveVO selectId_emp_v(int Id_emp_v) {
		return evaluationDAO.selectId_emp_v(Id_emp_v);
	}

	
	//최종제출 체크
	public int submitEvaluationCheck(int Id_emp_v) {
		return evaluationDAO.getCountSubmitEvaluation(Id_emp_v);		
	}
	
	// 최종 제출!(전체 테이블에도 저장!, 2년 용에도 저장! 
	public void submitEvaluation(EvaluationVO vo) {
		evaluationDAO.sumbitEvaluation(vo);
		evaluationDAO.deleteEvaluationsave(vo.getId_emp_v());
		evaluationDAO.submitReadEvaluation(vo);
		
	}
	
	// 안된 사람 찾기
	public List<InfoVO> findevaluation(Integer depart){
		if(depart ==null) {
			return evaluationDAO.findevaluation();
		}else {
			return evaluationDAO.findevaluationDep(depart);
		}
	}
	
	// 엑셀 저장
	
	public void excelfindevaluation(Integer depart, 
			HttpServletResponse response) {

// 게시판 목록조회
		List<InfoVO> list = null;
		if (depart != null && depart.toString().length()>0) {
			list = evaluationDAO.findevaluationDep(depart);
		} else {			
			list = evaluationDAO.findevaluation();
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

		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
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
		cell.setCellValue("소속부서");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("사번");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("직급");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("이름");

// 데이터 부분 생성

		for (InfoVO vo : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getDepart_d());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getId_emp());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getLev());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getName());
		}

// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		if (depart != null && depart.toString().length()>0) {
			try {
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
						(depart == 1 ? "인사" : (depart == 2 ? "총무" : (depart == 3 ? "개발" :  (depart ==4 ? "회계" : "영업")))),
						"UTF-8") + URLEncoder.encode("부서누락대상자", "UTF-8") + ".xls");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		} else {
			try {
				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode("인사고과누락대상자", "UTF-8") + ".xls");
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
