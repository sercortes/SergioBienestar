
function listarPrograma(data) {
    $.ajax({
        type: "GET",
        url: './getFichas',
        datatype: 'json',
        data:{
            id:data.id,
            fechaInicial: data.fechaInicial,
            fechaFinal: data.fechaFinal
        }
    }).done(function (data) {


     let select = document.getElementById('tabla');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Documento Aprendiz</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Nombre programa</th>
                                        <th>Coordinación</th>
                                        <th># Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {
            str += `<tr id="row${item.documento_aprendiz}" class="chiquito1 text-justify">
                                                    <td>${item.documento_aprendiz}</td>
                                                    <td>${item.nombre_aprendiz}</td>
                                                    <td>${item.ficha}</td>
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.coordinacion}</td>
                                                    <td>${item.participaciones}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-xs" role="button" onclick = "aprendices('${item.documento_aprendiz}')" >
                                                            <i class="fas fa-list-ol"></i> 
                                                        </button>            
                                                </td>
                                               
                                                </tr> `
        }
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
