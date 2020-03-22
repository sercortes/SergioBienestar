function aprendicesByCoor(id){
    
    console.log(id)
    
   
      
    $('#modalPrograma').modal('show')
    
     let fechai = document.getElementById('fechaI').value
     let fechaf = document.getElementById('fechaF').value
    
    let data = {
        programa:id,
        fechaInicial:fechai,
        fechaFinal:fechaf
    }
    
    graphicByProgram(data)

   
     $.ajax({
        type: "GET",
        url: './ListAprendicesByCoor',
        datatype: 'json',
        data:data
    }).done(function (data) {
        
        console.log(data)
        
       
       let programa = document.getElementById('tittleAprendicesXPrograma')
       programa.innerHTML = " "+id
       
      let cantidad = document.getElementById('Total')
      cantidad.innerHTML = "# fichas "+data.length
      
      let num = 0
      let numPro = 0

     let select = document.getElementById('tablaPrograma');
        let str = `<table id="examples" class="table table-striped table-bordered">
                                <thead class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Ficha</th>
                                        <th>Coordinación</th>
                                        <th>Participaciones</th>
                                        <th>Detalles</th>
                                    </tr>
                                </thead>
                                            <tbody>
                                                 `

        for (var item of data) {
            str += `<tr id="row" class="chiquito">
                                                    <td>${item.ficha}</td>
                                                    <td>${item.coordinacion}</td>
                                                    <td>${item.participaciones}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-sm" role="button" onclick = "fichas(${item.ficha})" >
                                                            <i class="fas fa-address-card"></i>
                                                        </button>           
                                                </td>
                                                </tr> `
     
        num +=parseInt(item.participaciones)
        }
        console.log(num)

        str += `      </tbody>
                                <tfoot class="letrablanca">
                                    <tr class="bg-primary">
                                        <th>Documento</th>
                                        <th>Nombres</th>
                                        <th>Ficha</th>
                                        <th>Programa</th>
                                    </tr>
                                </tfoot>
                            </table>`

        select.innerHTML = str;

       
       
    })
    

    



    
    
}

