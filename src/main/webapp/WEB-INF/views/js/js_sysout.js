const TARGET_ID = "box-div";
class System {
    out;
}

class Out {
    target;
    constructor(targetName){
        this.target = document.getElementById(targetName);
       // console.log("target이 만들어졌습니다.");
    }
    println(msg){
        if(msg == undefined){
            msg = "";
        }
        let text = document.createTextNode(msg);
        let br = document.createElement("br");
        this.target.appendChild(text);
        this.target.appendChild(br);
    }
    clear(){
        this.target.innerHTML = "";
    }
}

function init(){
    System.out = new Out(TARGET_ID);
}


