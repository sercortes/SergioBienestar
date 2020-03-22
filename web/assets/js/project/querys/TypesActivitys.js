function typesActivitys() {

    let filtros2 = document.getElementById('TypeActivity')

    let str = ``



    $.ajax({
        type: "GET",
        url: './getTypesActivitys',
        datatype: 'json'
    }).done(function (data) {


        for (var item of data) {
            str += `<option value="${item.tipo_actividad}">${item.tipo_actividad}</option>`
        }

      

        filtros2.innerHTML += str;
    })

}


function selectTypeActivity() {


    let select = document.getElementById('TypeActivity').value
 
    let input = document.getElementById('keyWord').value
    
    if (input !== "") {
        return false
    }
 
    if (select == 'No') {

    } else {
        $('#coor').val(1)

        let fechai = document.getElementById('fechaI').value
        let fechaf = document.getElementById('fechaF').value
        let data = {
            id: select,
            fechaInicial: fechai,
            fechaFinal: fechaf
        };
        
        if(!validationDate(data)){
            return false;
        }
        
        listarTypes(data)

    }
}

function listarTypes(data){
     $.ajax({
        type: "GET",
        url: './ListActivitysByType',
        datatype: 'json',
         data:{
            id:data.id,
            fechaInicial: data.fechaInicial,
            fechaFinal: data.fechaFinal
        }
    }).done(function (data) {
        

        if(data <= 0){
              let sel = document.getElementById('TypeActivity').value
            validationSizeSelect(sel)
            return false
        }

let num = 0;
     let select = document.getElementById('tabla');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Actividad</th>
                                        <th>Tipo</th>
                                        <th>Fecha Inicio</th>
                                        <th>Fecha Fin</th>
                                        <th>Responsable</th>
                                        <th>Aprendices</th>
                                        <th>Listado Aprendices</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {
            str += `<tr id="row${item.nombre_actividad}" class="chiquito1 text-justify">
                                                    <td>${item.nombre_actividad}</td>
                                                    <td>${item.tipo_actividad}</td>
                                                    <td>${item.fecha_inicio}</td>
                                                    <td>${item.fecha_fin}</td>
                                                    <td>${item.responsable}</td>
                                                    <td>${item.cantidad}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-xs" role="button" onclick = "aprendices('${item.idRealActividad}')" >
                                                            <i class="fas fa-list-ol"></i> 
                                                        </button>            
                                                </td>
                                               
                                                </tr> `
      num +=parseInt(item.cantidad)
        }
        console.log(num)
        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Actividad</th>
                                        <th>Tipo</th>
                                        <th>Fecha Inicio</th>
                                        <th>Fecha Fin</th>
                                        <th>Responsable</th>
                                        <th>Aprendices</th>
                                        <th>Listado Aprendices</th>
                                    </tr>
                                </tfoot>
                            </table>`

        select.innerHTML = str;       
       
    })
    
}