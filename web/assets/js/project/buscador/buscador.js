
$('#buttonSearch').click(function (){
   
   let input = document.getElementById('keyWord').value
   
    if (input.length <= 3) {
        messageInfo('Completa la palabra clave')
        return false
    }
   
   
    listarActivitysSearch(returtDataActivitys())
    
});



function listarActivitysSearch(data) {
    $.ajax({
        type: "GET",
        url: './ListActivitysSearch',
        data:data,
        datatype: 'json'
    }).done(function (data) {

console.log(data)

let num = 0

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

