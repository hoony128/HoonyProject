package kr.hoon.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.promotionresult.PromotionResultDAO;
import kr.hoon.project.vo.promotionresult.PromotionResultVO;
import kr.hoon.project.vo.promotiontest.PromotionTestDAO;
import kr.hoon.project.vo.promotiontest.PromotionTestVO;
import kr.hoon.project.vo.specialpromotion.SpecialPromotionDAO;
import kr.hoon.project.vo.specialpromotion.SpecialPromotionVO;

@Service
public class PromotionResultService {

	@Autowired
	private PromotionResultDAO promotionResultDAO;
	@Autowired
	private PromotionTestDAO promotiontestDAO;
	@Autowired
	private SpecialPromotionDAO specialPromotionDAO;
	
	// 권한부여
	public void promotionadminOk ( HttpServletRequest request ) {
		String[] Id_emp = request.getParameterValues("checkList[]");
		
		for (int i = 0; i < Id_emp.length; i++) {
			int key = Integer.parseInt(Id_emp[i]);
			String check = request.getParameter("check" + key);
			int userid =  Integer.parseInt(request.getParameter("checkId[" + key + "]"));
			
			if(check != null){
				promotiontestDAO.givetopromotionadmin(userid);
			}else{				
				promotiontestDAO.nogivetopromotionadmin(userid);
			}
		}
	}
	public int getCountsubmit(int Id_emp_p, int Id_emp_v) {
		return promotionResultDAO.getCountsbmit(Id_emp_p,Id_emp_v);
	}
	
	//결과 입력
	public void submitResult(PromotionResultVO vo) {
		promotionResultDAO.submitResult(vo);
		promotionResultDAO.saveResult(vo);
	}
	
	// 결과값을 다시 저장!
	public void submitResultFin() {
		promotionResultDAO.updateType();
		promotionResultDAO.updateadmininfo();
		
	}
	
	
	public List<PromotionResultVO> selectResultList(){
		return promotionResultDAO.selectResultList();
	}
	
	
	//승진결과
	public List<PromotionTestVO> findTestResult(int count){
		if(count==1) {
			return promotionResultDAO.findTestResultall();
		}else if(count==2) {
			return promotionResultDAO.findTestResultsuc();
		}else {
			return promotionResultDAO.findTestResultfail();
		}
	}
	
	
	
	// 승진 승인시 부서별로 보기!
	public List<PromotionTestVO> promotionList(Integer depart2){
		
		if(depart2 ==null) {			
			return promotionResultDAO.findTestResultsuc();
		}else {
			int depart = depart2;
			return promotionResultDAO.findTestResultsucDepart(depart);
		}
	}
	
	
	

	//승진처리
	public void promtionapprovalOk(HttpServletRequest request) {
		
			
		String[] Id_emp = request.getParameterValues("checkList[]");
		
		for (int i = 0; i < Id_emp.length; i++) {
			int key = Integer.parseInt(Id_emp[i]);
			String check = request.getParameter("check" + key);
			int userid =  Integer.parseInt(request.getParameter("checkId[" + key + "]"));
			int dep =  Integer.parseInt(request.getParameter("checkDep[" + key + "]"));
			int lev =  Integer.parseInt(request.getParameter("checkLev[" + key + "]"));
			int state =  Integer.parseInt(request.getParameter("checkState[" + key + "]"));
			int duty = Integer.parseInt(request.getParameter("checkDuty[" + key + "]"));
	
			if(check != null){
				// 체크가 됬다면
				// 1. Info의 lev 변경
				promotionResultDAO.updatelevInfo(userid);
				// 2. history 추가 (Id_emp)만 받기
				promotionResultDAO.levuphistory(userid, dep, lev, duty, state);
				// 3. promotiontestread 삭제
				promotionResultDAO.deletepromotionread();
				// 4. evalationread 삭제
				promotionResultDAO.deleteevalationread(userid);

			}else{
				// 체크가 안됬다면
				promotionResultDAO.deletepromotionread();
				}
			}
		} 
	
	public List<InfoVO> selectAllEMP(Integer depart){
		if(depart ==null) {
			return specialPromotionDAO.selectAllEmp();
		}else {
			return specialPromotionDAO.selectAlldep(depart);
		}
	}
	
	public void insertspecialpromotion(SpecialPromotionVO vo) {
		specialPromotionDAO.insertspecialpromotion(vo);
		specialPromotionDAO.insertspecialpromotionsave(vo);
	}
	
	public int specialpromotionCheck(int lev, SpecialPromotionVO vo) {
		int after_lev= vo.getLev_after();
	    int now_lev = lev;
		
	    if(after_lev-now_lev>2) {
	    	return 1;
	    }else if(after_lev-now_lev<=0) {
	    	return 2;
	    }else {
	    	return 3;
	    }
	}
	
	public List<SpecialPromotionVO> specialpromotionList(Integer depart2){
		
		if(depart2 ==null) {			
			return specialPromotionDAO.specialpromotionList();
		}else {
			int depart = depart2;
			return specialPromotionDAO.specialpromotionListDep(depart);
		}
	}
	
	// 비정기 승진 진행
	public void specialpromotionadminOk ( HttpServletRequest request ) {
		String[] Id_emp = request.getParameterValues("checkList[]");
		
		for (int i = 0; i < Id_emp.length; i++) {
			int key = Integer.parseInt(Id_emp[i]);
			String check = request.getParameter("check" + key);
			
			int lev =  Integer.parseInt(request.getParameter("checkLev[" + key + "]"));
			int dep =  Integer.parseInt(request.getParameter("checkDep[" + key + "]"));
			int userid =  Integer.parseInt(request.getParameter("checkId[" + key + "]"));
			int duty = Integer.parseInt(request.getParameter("checkDuty[" + key + "]"));
			int nowlev = Integer.parseInt(request.getParameter("checknowlev[" + key + "]"));
			 
			if(check != null){
				
				specialPromotionDAO.updateInfoLev(lev, userid); 
				specialPromotionDAO.levuphistory(userid, nowlev, lev, dep, duty);
				specialPromotionDAO.deletespecialpromotion(userid); 
				promotionResultDAO.deleteevalationread(userid);
			}else{				

			}
		}
	}
}
