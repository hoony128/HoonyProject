package kr.hoon.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import kr.hoon.project.vo.notice.NoticeDAO;


	
	public class DeleteData {
	
		
		@Scheduled(cron="0/5 * * * * *") 
		public void asdasd() {			
			System.out.println("초초초초");
			
		}
	}

