package com.spring.myweb.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageCreator {
	
	private PageVO paging;
	private int articleTotalCount, endPage, beginPage;
	private boolean prev, next;
	
	private final int buttonNum = 5; //버튼개수 
	
	public PageCreator(PageVO paging, int articleTotalCount) {
		this.paging = paging;
		this.articleTotalCount = articleTotalCount;
		calcDataOfPage();
	}
	
	private void calcDataOfPage() {
		
		endPage = (int) (Math.ceil(paging.getPageNum() / (double) buttonNum) * buttonNum);  // ex) 사용자 7페이지   7/5(올림=2)*5 = 10페이지
		
		beginPage = endPage - buttonNum + 1;  // beginPage = endpage 10 - buttonNum 5 + 1 = 6
		
		prev = (beginPage == 1) ? false : true; // beginPage 시작페이지가 1이라면  false 아니면 true
		
		next = articleTotalCount <= (endPage * paging.getCpp()) ? false : true;
		
		if(!next) {
			endPage = (int) Math.ceil(articleTotalCount / (double) paging.getCpp());  // 87 / 7 = 8.7 올림 9
		}
	}
	
}
