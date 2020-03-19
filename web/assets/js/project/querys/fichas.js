function fichas(id){
    
    
      
    $('#exampleModal').modal('show')
    
  
        let fechai = document.getElementById('fechaI').value
        let fechaf = document.getElementById('fechaF').value
        
        let data = {
            id: id,
            fechaInicial : fechai,
            fechaFinal : fechaf
        };
        
        if(!validationDate(data)){
            return false
        }
      
      graphicByFicha(data)
  
   
     $.ajax({
        type: "GET",
        url: './ListAprendicesByFicha',
        datatype: 'json',
        data:{
            id:id,
            fechaInicial : data.fechaInicial,
            fechaFinal : data.fechaFinal
        }
    }).done(function (data) {
        
        console.log(data)
      
      let acti = data[0].ficha
      let num = 0
      
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
                                        <th>Participaciones</th>
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
                                                    <td>${item.participaciones}</td>
                                                 <td>         
                                                      <button class="btn btn-info btn-sm" role="button" onclick = "getActivitysByAprendiz(${item.documento_aprendiz})" >
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
                                        <th>Coordinación</th>
                                        <th>Coordinación</th>
                                        <th>Detalles</th>
                                    </tr>
                                </tfoot>
                            </table>`

        select.innerHTML = str;

       
       
    })
    

    
}

