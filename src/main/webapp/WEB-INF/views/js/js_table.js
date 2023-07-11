class Table{
    tableElement;
    trElement;
    
    constructor(id){
        this.makeTable(id);
    }
    
    makeTable(id){
        let table = document.getElementById(id);
        
        if( table != null){
            this.tableElement = table;
        }else{
            this.tableElement = document.createElement('table');
            this.tableElement.id = id;
        }
        
    }
    
    makeTr(){
        this.trElement = document.createElement('tr');
    }
    
    insertElement(tag, text){
        let tagElement = document.createElement(tag);
        let textNode = document.createTextNode(text);
        tagElement.appendChild(textNode);
        this.trElement.appendChild(tagElement);
    }
    
    insertTr2Table(){
        this.tableElement.appendChild(this.trElement);
    }
   
   

    toTable(){
        return this.tableElement;
    }
}    