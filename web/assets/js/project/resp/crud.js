$(document).on('click', '.deletecode', function(){
    let ele = $(this)[0].parentElement.parentElement
    let id = $(ele).attr('idRes')
    deleteCode(id)
})


function deleteCode(id){
    
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
            messageOk('Eliminado con éxito')
            queryResponsables()
        }
        
    })
    
}


function drawTable(data){
    
    
    let select = document.getElementById('tabla');
    let str = ` `
   
    for (var item of data) {

        str += `<tr idRes="${item.id}" class="chiquito">
                                                    <td class="element">${item.nombre}</td>
                                                    <td class="element">${item.codigo}</td>
                                                    <td class="element">${item.year}</td>
                                                 <td>         
                                                      <button class="seeRespon btn btn-warning btn-sm">
                                                            <i class="fas fa-edit"></i>
                                                        </button>    
                                                        <button class="deletecode btn btn-danger btn-sm">
                                                            <i class="fas fa-trash"></i>
                                                        </button>     
                                                </td>
                                                </tr> `
    }
    
    select.innerHTML = str;
    
}


$(document).on('click','.seeRespon', function(){
    
    let ele = $(this)[0].parentElement.parentElement
    let id = $(ele).attr('idRes')
    let element = $(this).closest('tr').find('.element')
    var data = {
        id:id,
        nombre:element[0].outerText,
        codigo:element[1].outerText,
        year:element[2].outerText
    }
    
    update(data)
    
})

function update(data){
    

    $('#modalUpdate').modal('show')
    
    $('#idE').val(data.id)
    $('#nameE').val(data.nombre)
    $('#codeE').val(data.codigo)
    
    generateYearsEdit(data.year)
    
}

document.getElementById('formEdit').addEventListener('submit', function(ev){
    

    ev.preventDefault()
    
    let id = document.getElementById('idE').value
    let nombre = document.getElementById('nameE').value
    let codigo = document.getElementById('codeE').value
    let year = document.getElementById('yearE').value
    
    if (nombre == '' || codigo == '' || year == '' || nombre.length <= 2 || codigo.length <= 1) {
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
            $('#formEdit').removeClass('was-validated')
            queryResponsables()
        }
        
        
    })
}