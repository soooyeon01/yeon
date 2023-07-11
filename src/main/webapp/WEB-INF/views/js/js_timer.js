        let myIntervalId;
        let box;
        let disCnt = 10;
           
        function countDown(){
            
            if(box.flag){
                box.style.backgroundColor= "aqua";
                box.style.color = "white";
               
                System.out.clear();
                System.out.println(--disCnt);

                box.flag = false;
            }else{
                box.style.backgroundColor= "yellow";
                box.style.color = "black";

                System.out.clear();
                System.out.println(--disCnt);

                box.flag = true;
                
            }

            if(disCnt < 1){
                clearInterval(myIntervalId);
                //System.out.clear();
            }
            
        }