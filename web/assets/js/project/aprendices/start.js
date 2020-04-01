

$(function(){
    
    let menuItem = document.getElementById('menuAprendices')
    menuItem.classList.add('active')
    
    let coor = document.getElementById('coor')
    
    let cordinacion = ``
    
    $.ajax({
            type: "GET",
            url: './getCoor',
            datatype: 'json'
        }).done(function (data) {
                      

            for (var item of data) {
                cordinacion += `<option value="${item.coordinacion}">${item.coordinacion}</option>`
            }
            
            
            coor.innerHTML += cordinacion;
               
            
        })
    
    setDefaultDate()
    
    searchAprendiz(returnDataAprendiz())
    
    
})

function cambiarFecha(){
    
    selectCoordinacion()
    
}

$('#buttonSearch').click(function(){
    
     let word = document.getElementById('keyWord').value

        if (word == '') {
        searchAprendiz(returnDataAprendiz())
   }else if (word.length <= 2) {
       messageInfo('Completa el documento o el nombre')
        return false
    }else{
        searchAprendizWord(returnDataAprendiz())
    }
  
    
    
    
})

function searchAprendizWord(data){
    
     $.ajax({
        type: "GET",
        url: './getSearchAprendizWord',
        datatype: 'json',
        data:data
    }).done(function (data) {
   
   console.log(data)
   
     if(data <= 0){
            validationSizeSelect(returnDataAprendiz().palabra)
            return false
        }
        
    validationResult(data.length)
        
       
        let sum  = 0
        
     let select = document.getElementById('tabla');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Programa</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {
            str += `<tr id="" class="chiquito1 text-justify">
                                                    <td>${item.documento_aprendiz}</td>
                                                    <td>${item.nombre_aprendiz}</td>
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.actividades.cantidad}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-xs" role="button" onclick = "getActivitysByAprendiz('${item.id_aprendiz}')" >
                                                            <i class="fas fa-list-ol"></i> 
                                                        </button>            
                                                </td>
                                               
                                                </tr> `
        sum += parseInt(item.actividades.cantidad);
        }
        console.log(sum)
        total = sum
        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Programa</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>`


        select.innerHTML = str;    
        
   
       
    })
    
    
}


function searchAprendiz(data){
    
     $.ajax({
        type: "GET",
        url: './getSearchAprendiz',
        datatype: 'json',
        data:data
    }).done(function (data) {
   
     if(data <= 0){
            validationSizeSelect(returnDataAprendiz().palabra)
            return false
        }
        
    validationResult(data.length)
        
       
        let sum  = 0
        
     let select = document.getElementById('tabla');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Programa</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {
            str += `<tr id="" class="chiquito1 text-justify">
                                                    <td>${item.documento_aprendiz}</td>
                                                    <td>${item.nombre_aprendiz}</td>
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.actividades.cantidad}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-xs" role="button" onclick = "getActivitysByAprendiz('${item.id_aprendiz}')" >
                                                            <i class="fas fa-list-ol"></i> 
                                                        </button>            
                                                </td>
                                               
                                                </tr> `
        sum += parseInt(item.actividades.cantidad);
        }
        console.log(sum)
        total = sum
        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="tablas">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Programa</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>`


        select.innerHTML = str;    
        
   
       
    })
    
    
}

function returnDataAprendiz(){
      let fechai = document.getElementById('fechaI').value
        let fechaf = document.getElementById('fechaF').value
        let word = document.getElementById('keyWord').value
         let select = document.getElementById('coor').value
    
    if(select == 'No'){
        select = ''
    }
    
        let data = {
            palabra:word,
            fechaInicial:fechai,
            fechaFinal:fechaf
        }
        
        return data
    
}
