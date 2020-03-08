
$(function(){
    
  
    
})

document.getElementById("filtroPrograma").addEventListener("click", function (){

    let filtros = document.getElementById('filtros')
    let filtros1 = document.getElementById('filtros1')

    
    let str = ``
    str +=` <label for="validationTooltip03">Fecha Inicial</label>
      <input type="date" class="form-control" id="fechaI" placeholder="City" required>`
    
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
            
            filtros.innerHTML = str;
               
            
        })
        
        filtros1.innerHTML = `<label for="validationTooltip03">Fecha final</label>
      <input type="date" class="form-control" id="fechaF" placeholder="City" required>`
    
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
       
});





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
            listarPrograma(data)
    }
    
}
