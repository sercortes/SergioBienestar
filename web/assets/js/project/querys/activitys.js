$(document).ready(function () {
 
    listarActivitysSearch(returtDataActivitys())
   
});

function returtDataActivitys(){
    
        let fechai = document.getElementById('fechaI').value
        let fechaf = document.getElementById('fechaF').value
        let word = document.getElementById('keyWord').value
        let select = document.getElementById('TypeActivity').value
    
    if(select == 'No'){
        select = ''
    }
    
        let data = {
            palabra:word,
            fechaInicial:fechai,
            fechaFinal:fechaf,
            tipo:select
        }
        
        return data
    
}

