function selectCoordinacion(){
    let select = document.getElementById('coor').value
    
    if(select == 'No'){
            
    }else{
       $('#programa').val(1)
         $('#TypeActivity').val(1)
    
        let fechai = document.getElementById('fechaI').value
        let fechaf = document.getElementById('fechaF').value
        
        let data = {
            id : select,
            fechaInicial : fechai,
            fechaFinal : fechaf
        };
        
        if(!validationDate(data)){
            return false
        }
        
            listarCoor(data)
       
       
    }
}



function listarCoor(data) {
    $.ajax({
        type: "GET",
        url: './getByCoor',
        datatype: 'json',
        data:{
            id:data.id,
            fechaInicial: data.fechaInicial,
            fechaFinal: data.fechaFinal
        }
    }).done(function (data) {
        let sum  = 0

     let select = document.getElementById('tabla');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="bg-primary">
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
                                                    <td>${item.nombrePrograma}</td>
                                                    <td>${item.coordinacion}</td>
                                                    <td>${item.participaciones}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-xs" role="button" onclick = "aprendicesByCoor('${item.nombrePrograma}')" >
                                                            <i class="fas fa-list-ol"></i> 
                                                        </button>            
                                                </td>
                                               
                                                </tr> `
        sum += parseInt(item.participaciones);
        }
        console.log(sum)
        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="bg-primary">
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