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

import kr.hoon.project.vo.boardcheck.BoardCheckDAO;
import kr.hoon.project.vo.boardcheck.BoardCheckVO;

@Service
public class BoardCheckService {

	@Autowired
	private BoardCheckDAO boardCheckDAO;

	// 1차 조건 검사
	public List<BoardCheckVO> boardType() {
		return boardCheckDAO.boardType();
	}

	// 2차조건검사
	public List<BoardCheckVO> typeselect(String boardtype) {
		List<BoardCheckVO> list = null;
		if(boardtype !=null && boardtype.trim().length()>0) {
		  	list = boardCheckDAO.typeselect(boardtype);
		}else {
			;
		}
		return list;
	}

	// 검색결과
	public List<BoardCheckVO> boardchecklist(String boardtype,String type) {
		if (boardtype != null && boardtype.trim().length() > 0) {
			return boardCheckDAO.boardCheckSearch(boardtype, type);
		} else {
			return boardCheckDAO.boardCheckSearchAll();
		}
	}

	// 엑셀 저장

	public void excelboardcheck(String boardtype, String type, HttpServletResponse response) {

// 게시판 목록조회
		List<BoardCheckVO> list = null;
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("boardtyp:나오나?" +boardtype);
		if (boardtype != null && boardtype.trim().length()>0) {
			list = boardCheckDAO.boardCheckSearch(boardtype,type);
		} else {			
			list = boardCheckDAO.boardCheckSearchAll();
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
		cell.setCellValue("저장공간");
		cell = row.createCell(1);
		cell.setCellStyle(headStyle);
		cell.setCellValue("글번호");
		cell = row.createCell(2);
		cell.setCellStyle(headStyle);
		cell.setCellValue("형태");
		cell = row.createCell(3);
		cell.setCellStyle(headStyle);
		cell.setCellValue("사번");
		cell = row.createCell(4);
		cell.setCellStyle(headStyle);
		cell.setCellValue("일자");
		cell = row.createCell(5);
		cell.setCellStyle(headStyle);
		cell.setCellValue("IP");


// 데이터 부분 생성

		for (BoardCheckVO vo : list) {
			row = sheet.createRow(rowNo++);
			cell = row.createCell(0);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBoardtype());
			cell = row.createCell(1);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getBoard_idx());
			cell = row.createCell(2);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getType());
			cell = row.createCell(3);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getId_emp());
			cell = row.createCell(4);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue((vo.getUpdatedate().getYear()+1900)+"/"+vo.getUpdatedate().getMonth()+"/"+vo.getUpdatedate().getDate()+" "+ vo.getUpdatedate().getHours()+":"+vo.getUpdatedate().getMinutes());
			cell = row.createCell(5);
			cell.setCellStyle(bodyStyle);
			cell.setCellValue(vo.getIp());
		}

// 컨텐츠 타입과 파일명 지정
		response.setContentType("ms-vnd/excel");
		if (boardtype != null && boardtype.trim().length()>0) {
			try {
				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
						(boardtype == "익명게시판댓글" ? "익명게시판댓글" : (boardtype == "공지사항" ? "공지사항" : (boardtype == "업무도움방" ? "업무도움방" :  "익명게시판"))),
						"UTF-8") + URLEncoder.encode("사원이력", "UTF-8") + ".xls");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		} else {
			try {
				response.setHeader("Content-Disposition",
						"attachment;filename=" + URLEncoder.encode("전게시글이력", "UTF-8") + ".xls");
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
