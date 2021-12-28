package kr.hoon.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hoon.project.vo.Info.InfoVO;
import kr.hoon.project.vo.promotiontest.PromotionTestDAO;
import kr.hoon.project.vo.promotiontest.PromotionTestVO;

@Service
public class PromotionTestService {

	@Autowired
	private PromotionTestDAO promotionTestDAO;
	
	//승진평가 대상자(depart가 넘어오면 해당부서의 대상자만 없으면 모두 보여주기!)
	public List<PromotionTestVO> promotiontestpeople(Integer depart,int Id_emp_p){
		if(depart == null) {
			return promotionTestDAO.promotiontestpeople(Id_emp_p);
		}else {			
			return promotionTestDAO.promotiontestdepart(depart, Id_emp_p);
		}
	}
	
	//부장 직급이상의 사람들의 정보를 가져오기!
	public List<InfoVO> promotionadmin(){
		return promotionTestDAO.promotionadmin();
	}
	

	
}
