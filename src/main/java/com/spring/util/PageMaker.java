package com.spring.util;


// 페이지 계산


public class PageMaker {

   private double totalCount; // 전체 게시글
   private Criteria cri;
   // prev <
   // next >
   // startPage
   // endPage
   private boolean prev;
   private boolean next;
   private int startPage; // 1
   private int endPage; // 40
   private int realEndPage; // 34

   private int displayPageAmount; // dpa:5= prev 1 2 3 4 5 next, dpa:10= prev 1~10 next

   // 게시글 339, dispalyPageAmount 10, cri.amount 5,
   // case 1 startPage 1, endPage 10 : 1~50
   // case 2 startPage 11, endPage 20 : 101~200
   // case 3 startPage 21, endPage 30 : 201~300
   // case 4 startPage 31, endPage 34 : 301~339

   public PageMaker(Criteria cri, int totalCount) {
      this.cri = cri;
      this.totalCount = totalCount;
      this.displayPageAmount = 10;
      init();
   }

   public void init() {
      // endPage 계산
      // pageNum = 8 -> 71~80,case1 endPage 10
      // pageNum = 12 -> 111~120,case2 endPage 20
      // pageNum = 32 -> case4, endPage 40 -> realEndPage 34 -> endPage = realEndPage;
      // 339 / cri.amount 페이지당 10개씩, 339/10 -> 33.9 -. 34, 31~34
      this.endPage = (int) Math.ceil((cri.getPageNum() * 1.0) / this.displayPageAmount) * this.displayPageAmount; // 8
                                                                                       // ->
                                                                                       // 8
                                                                                       // /
                                                                                       // diplayPageAmount
                                                                                       // ->
                                                                                       // 1
                                                                                       // *
                                                                                       // displayPageAmount
      // 4*1.0 / 10 = 0.4, 1 * 10 -> 10
      // display 5, 4.0 / 5, 0.8= 1.0 * 5 > 5

      // 339
      // display 10, cri.amount 5
      // case 1, 1~10 : 1~50
      // case 2, 11~20 : 51 ~ 100
      // case 3, 21~30 :101~150
      // case 4, 31~40 : 151~200
      // case 5, 41~50 :201~250
      // case 6, 51~60 : 251~300 endPage 60 < realEndPage 68
      // case 7, 61~70 :301~350,endPage 70, realEndPage 68

      this.realEndPage = (int) Math.ceil(this.totalCount * 1.0 / cri.getAmount()); // 339 / 5, 67.8 -> ceil 68

      // 10, 1 endPage - (displayPageAmount - 1) = 1
      // 20, 11 20 - (displayPageAmount - 1) = 11
      // 30, 21

      // 14 - (10 - 1)= 14 - 9 = 5, 11~14
      this.startPage = this.endPage - (displayPageAmount - 1);

      if (realEndPage < endPage) {
         endPage = realEndPage;
      }

      prev = startPage > 1 && startPage > displayPageAmount;
      next = endPage < realEndPage;
   }

   public Criteria getCri() {
      return cri;
   }

   public void setCri(Criteria cri) {
      this.cri = cri;
      init();
   }

   public double getTotalCount() {
      return totalCount;
   }

   public void setTotalCount(double totalCount) {
      this.totalCount = totalCount;
      init();
   }

   public boolean isPrev() {
      return prev;
   }

   public void setPrev(boolean prev) {
      this.prev = prev;
   }

   public boolean isNext() {
      return next;
   }

   public void setNext(boolean next) {
      this.next = next;
   }

   public int getStartPage() {
      return startPage;
   }

   public void setStartPage(int startPage) {
      this.startPage = startPage;
   }

   public int getEndPage() {
      return endPage;
   }

   public void setEndPage(int endPage) {
      this.endPage = endPage;
   }

   public int getDisplayPageAmount() {
      return displayPageAmount;
   }

   public void setDisplayPageAmount(int displayPageAmount) {
      this.displayPageAmount = displayPageAmount;
      this.init();
   }

   @Override
   public String toString() {
      return "현재페이지=" + cri.getPageNum() + "\n" + ",startPage=" + startPage + ",endPage=" + endPage + ",realEndPage="
            + realEndPage + ",prev=" + prev + ",next=" + next + ",cri.amount=" + cri.getAmount() + ",displayNum="
            + displayPageAmount;
   }

   public static void main(String[] args) {

      Criteria cri = new Criteria(6, 10); // 현재페이지, 한페이지당 출력갯수
      PageMaker pageMaker = new PageMaker(cri, 100); // cri, totalCount=100
      pageMaker.setDisplayPageAmount(5);
      System.out.println(pageMaker);

      cri.setPageNum(4);
      cri.setAmount(10);
      pageMaker.setCri(cri);
      pageMaker.setDisplayPageAmount(10);
      System.out.println(pageMaker);

      cri.setPageNum(8);
      pageMaker.setCri(cri);
      System.out.println(pageMaker);

      pageMaker.setTotalCount(139);
      System.out.println(pageMaker);

      cri.setPageNum(11);
      pageMaker.setCri(cri);
      System.out.println(pageMaker);

      /*
       * int realEndPage = (int) Math.ceil(339 * 1.0 /5 ) ;
       * 
       * System.out.println(realEndPage); System.out.println((int) Math.ceil(339 /5
       * )); // 1.0을 해줘야함. 이러면 안됨
       */
   }

}