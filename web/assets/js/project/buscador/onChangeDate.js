

function cambiarFecha(){

    
        let tipos = document.getElementById('TypeActivity').value
    
        let coor = document.getElementById('coor').value

   
    if(tipos == 'No' && coor == 'No'){
        listarActivitysSearch(returtDataActivitys()) 
    }else if(isNaN(coor)){   
       selectCoordinacion()
    }else if(isNaN(tipos)){
       listarActivitysSearch(returtDataActivitys())
    }
    

    
}

