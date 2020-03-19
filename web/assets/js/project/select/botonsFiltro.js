
Date.prototype.toDateInputValue = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    return local.toJSON().slice(0,10);
});

Date.prototype.getStartYear = (function() {
    var local = new Date(this);
    local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
    local.setMonth(this.getMonth()-(this.getMonth()))
    local.setDate(this.getDay()-this.getDay()+1)
    return local.toJSON().slice(0,10);
});

$(function(){
    
      let filtros = document.getElementById('filtros')
    let filtros1 = document.getElementById('filtros1')

    
    let str = ``
    str +=` `
    
    str += `<label for="validationTooltip03">Programa</label>`
      $.ajax({
            type: "GET",
            url: './getPrograms',
            datatype: 'json'
        }).done(function (data) {
            
          str += `<select name="perfil" class="form-control" id="programa" tabindex="4" onchange="selectProgram()" required>
          <option value="No">No</option>`

            for (var item of data) {
                str += `<option value="${item.nombrePrograma}">${item.nombrePrograma}</option>`
            }
            
            str += "</select>"
            
            filtros.innerHTML += str;
               
            
        })
        
    
    
    let cordinacion = ``
    
    cordinacion += `<label for="validationTooltip03">Coordinación</label>`
 

    
    $.ajax({
            type: "GET",
            url: './getCoor',
            datatype: 'json'
        }).done(function (data) {
                      
           cordinacion += `<select name="coor" class="form-control" id="coor" tabindex="4" onchange="selectCoordinacion()" required>
          <option value="No">No</option>`

            for (var item of data) {
                cordinacion += `<option value="${item.coordinacion}">${item.coordinacion}</option>`
            }
            
            cordinacion += "</select>"
            
            filtros1.innerHTML += cordinacion;
               
            
        })
    
    typesActivitys()

    setDefaultDate()
    
})

document.getElementById("filtroPrograma").addEventListener("click", function (){
      
       
});

  
function setDefaultDate(){
    document.getElementById('fechaI').value = new Date().getStartYear();
    document.getElementById('fechaF').value = new Date().toDateInputValue();
}




function selectProgram(){
    
    let select = document.getElementById('programa').value
    if (select == '') {
        listar()
    }else{
         $('#coor').val(1)
         $('#TypeActivity').val(1)
        let fechai = document.getElementById('fechaI').value
        let fechaf = document.getElementById('fechaF').value
      
        let data = {
            id : select,
            fechaInicial : fechai,
            fechaFinal : fechaf
        };
        
        if (!validationDate(data)) {
            return false
        }
        
        
            listarPrograma(data)
    }
    
}
