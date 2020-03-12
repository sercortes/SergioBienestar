
function aprendices(id){
    
    $('#exampleModal').modal('show')
    
  
   
     $.ajax({
        type: "GET",
        url: './ListAprendices',
        datatype: 'json',
        data:{
            id:id
        }
    }).done(function (data) {
      
      let acti = data[0].actividades.nombre_actividad
      
      
       $('#titulo').text(acti)
       
      let cantidad = document.getElementById('cantidad')
      cantidad.innerHTML = "# Aprendices";
      cantidad.innerHTML += " "+data.length

     let select = document.getElementById('tabla1');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Programa</th>
                                        <th>Coordinación</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {
            str += `<tr id="row${item.nombre_actividad}" class="chiquito">
                                                    <td>${item.documento_aprendiz}</td>
                                                    <td>${item.nombre_aprendiz}</td>
                                                    <td>${item.ficha}</td>
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.coordinacion}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-sm" role="button" onclick = "eliminar(${item.documento_aprendiz})" >
                                                            <i class="fas fa-address-card"></i>
                                                        </button>           
                                                </td>
                                                </tr> `
        }
        

        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Programa</th>
                                        <th>Coordinación</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>`

        select.innerHTML = str;

       
       
    })
    

}



