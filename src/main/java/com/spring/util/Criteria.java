package com.spring.util;


public class Criteria {
   private int pageNum;    // 현재 내가 클릭한 페이지 넘버
   private int amount;    // 페이지당 몇개씩 읽어올까?
   
   public Criteria() {
      this(1, 10);
   }
   
   public Criteria(int pageNum){
      this(pageNum, 10);
   }
   
   public Criteria(int pageNum, int amount){
      this.pageNum = pageNum;
      this.amount = amount;
   }
   
   public int getPageNum() {
      return pageNum;
   }
   
   public void setPageNum(int pageNum) {
      this.pageNum = pageNum;
   }
   
   public int getAmount() {
      return amount;
   }
   
   public void setAmount(int amount) {
      this.amount = amount;
   }
   
}