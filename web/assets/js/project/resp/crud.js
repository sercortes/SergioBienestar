function deleteCode(id){

    var split = id.split(',')
    
    id = split[1]
    
    Swal.fire({
  title: 'esta seguro?',
  text: "No se pueden revertir los cambios!",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Si, Eliminar!'
}).then((result) => {
  
        if (result.value) {
                deleteOk(id)
        }
  
})
    
    
}

function deleteOk(id){
    
     $.ajax({
        type: "POST",
        url: './delResponsables',
        datatype: 'json',
        data:{
            id:id
        }
    }).done(function (data) {
   
        if (data) {
            messageOk('Generado con éxito')
            queryResponsables()
        }
        
        
    })
    
}


function drawTable(data){
    
    
    let select = document.getElementById('tabla');
    let str = ` `
   
    for (var item of data) {

        str += `<tr id="${item.id}" class="chiquito">
                                                    <td>${item.nombre}</td>
                                                    <td>${item.codigo}</td>
                                                    <td>${item.year}</td>
                                                 <td>         
                                                      <button class="btn btn-warning btn-sm" role="button" onclick = "update('${Object.entries(item)}')" >
                                                            <i class="fas fa-edit"></i>
                                                        </button>    
                                                        <button class="btn btn-danger btn-sm" role="button" onclick = "deleteCode('${Object.entries(item)}')" >
                                                            <i class="fas fa-trash"></i>
                                                        </button>     
                                                </td>
                                                </tr> `
    }
    
    select.innerHTML = str;
    
}


function update(data){
    
   var split = data.split(',')
    
    $('#modalUpdate').modal('show')
    
    var data = {
        id:split[1],
        nombre:split[3],
        codigo:split[5],
        year:split[7]
    }
    
    $('#idE').val(data.id)
    $('#nameE').val(data.nombre)
    $('#codeE').val(data.codigo)
    
    generateYearsEdit(data.year)
    
}

$('#botonUpdateForm').click(function (){
    
    let id = document.getElementById('idE').value
    let nombre = document.getElementById('nameE').value
    let codigo = document.getElementById('codeE').value
    let year = document.getElementById('yearE').value
    
    if (nombre == '' || codigo == '' || year == 'No') {
        messageInfo('complete el formulario')
    }else{
        
        let data = {
            id:id,
            nombre:nombre,
            codigo:codigo,
            year:year
        }
        
        editRecord(data);
        
    }
    
})

function editRecord(data){
    $.ajax({
        type: "POST",
        url: './editResponsables',
        datatype: 'json',
        data:data
    }).done(function (data) {
   
      
        if (data) {
            messageOk('Editado con éxito')
            queryResponsables()
        }
        
        
    })
}