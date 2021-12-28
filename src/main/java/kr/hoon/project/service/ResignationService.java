package kr.hoon.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.resignation.ResignationDAO;
import kr.hoon.project.vo.resignation.ResignationVO;

@Service
public class ResignationService {

	@Autowired
	private ResignationDAO resignationDAO;
	
	public List<InfoVO> selectDep(int depart){
		return resignationDAO.selectdep(depart);
	}
	
	//임시저장 해줄 때
	public void insertTemp(int Id_emp_v, ResignationVO vo) {
		resignationDAO.deletetemp(Id_emp_v);
		resignationDAO.tempsave(vo);
	}
	
	// 저장된 거에 몇개 있는지
	public int getCountSubmit(int Id_emp_v) {
		return resignationDAO.getCountSubmit(Id_emp_v);
	}
	
	// 임시저장에서 가져오기
	public ResignationVO select_temp(int Id_emp_v) {
		return resignationDAO.selectId_emp_v(Id_emp_v);
	}
	
	// 제출하기
	public void submitResignation (int Id_emp_v, ResignationVO vo) {
		resignationDAO.deletetemp(Id_emp_v);
		resignationDAO.resignationviewsave(vo);
	}
	
	// 목록 보이기(인사부 점검용)
	public List<ResignationVO> selectList(Integer depart2){
		int depart= 0;
		if(depart2 ==null) {
			return resignationDAO.resignListAll(); 
		}else {
			depart= depart2;			
			return resignationDAO.resignListDep(depart);
		}
	}
	// 목록 보이기(승인용)
	public List<ResignationVO> selectListApp(Integer depart2){
		int depart= 0;
		if(depart2 ==null) {
			return resignationDAO.resignListApprovalAll();
		}else {
			depart= depart2;			
			return resignationDAO.resignListApprovalDep(depart);
		}
	}
	
	//인사부에서 결재를 요청을 할 때
	public void resignerOk ( HttpServletRequest request ) {
		String[] Id_emp = request.getParameterValues("checkList[]");
		
		for (int i = 0; i < Id_emp.length; i++) {
			int key = Integer.parseInt(Id_emp[i]);
			String check = request.getParameter("check" + key);
			
			String dep =  request.getParameter("checkDep[" + key + "]");
			int lev =  Integer.parseInt(request.getParameter("checkLev[" + key + "]"));
			String lev_l =  request.getParameter("checkLev_l[" + key + "]");
			String duty_d =  request.getParameter("checkDuty_d[" + key + "]");
			int duty =  Integer.parseInt(request.getParameter("checkDuty[" + key + "]"));
			String reason =  request.getParameter("checkReason[" + key + "]");
			String name_p =  request.getParameter("checkName_p[" + key + "]");
			String name_v =  request.getParameter("checkName_v[" + key + "]");
			int id_v =  Integer.parseInt(request.getParameter("checkId_v[" + key + "]"));
			int id_p =  Integer.parseInt(request.getParameter("checkId_p[" + key + "]"));
			
			if(check != null){
				resignationDAO.resignationapproval(dep, lev, lev_l, duty_d, duty, reason, name_p, name_v, id_v, id_p);
				resignationDAO.resignationonlysave(dep, lev, lev_l, duty_d, duty, reason, name_p, name_v, id_v, id_p);
				resignationDAO.viewdelete(id_v);
			}else{				
				
			}
		}
	}
	//인사부에서 사퇴요구를 철회할때
	public void resingerNo(HttpServletRequest request) {
		String[] Id_emp = request.getParameterValues("checkList[]");
		System.out.println(Id_emp);
		for (int i = 0; i < Id_emp.length; i++) {
			int key = Integer.parseInt(Id_emp[i]);
			String check = request.getParameter("check" + key);
			
			int id_v =  Integer.parseInt(request.getParameter("checkId_v[" + key + "]"));
			
			if(check != null){
				resignationDAO.resignerNo(id_v);
			}else{				

			}
		}
	}
	
	
	
	//퇴사 최종승인!
	public void resigneapprovalOk ( HttpServletRequest request ) {
		String[] Id_emp = request.getParameterValues("checkList[]");
		System.out.println(Id_emp);
		for (int i = 0; i < Id_emp.length; i++) {
			int key = Integer.parseInt(Id_emp[i]);
			String check = request.getParameter("check" + key);
			
			int lev =  Integer.parseInt(request.getParameter("checkLev[" + key + "]"));
			int duty =  Integer.parseInt(request.getParameter("checkDuty[" + key + "]"));
			int id_v =  Integer.parseInt(request.getParameter("checkId_v[" + key + "]"));
			int depart =  Integer.parseInt(request.getParameter("checkDepart[" + key + "]"));
			
			if(check != null){
				//history 추가
				resignationDAO.insertHistory(id_v, lev, depart, duty);
				//상태 바꿔주기
				resignationDAO.updateInfoState(id_v);
				// type3 지우기
				resignationDAO.deleteapproval(id_v);
			}else{				
				
			}
		}
	}
	//퇴사 최종승인 거절!
	public void resigneapprovalNo ( HttpServletRequest request ) {
		String[] Id_emp = request.getParameterValues("checkList[]");
		System.out.println(Id_emp);
		for (int i = 0; i < Id_emp.length; i++) {
			int key = Integer.parseInt(Id_emp[i]);
			String check = request.getParameter("check" + key);
				int id_v =  Integer.parseInt(request.getParameter("checkId_v[" + key + "]"));
			if(check != null){
				//철회하기 위해서 변경값을 바꿔준다.
				resignationDAO.resigneApprovalNo(id_v);
			}else{				
				
			}
		}
	}
}
