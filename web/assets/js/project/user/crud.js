$(document).on('click', '.deletecode', function(){
    let ele = $(this)[0].parentElement.parentElement
    let id = $(ele).attr('idUser')
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
        url: './deluser',
        datatype: 'json',
        data:{
            id:id
        }
    }).done(function (data) {
   
        if (data) {
            messageOk('Eliminado con éxito')
            Users()
        }else{
            messageError('=( up ocurrio un error')
        }
        
    })
    
}


function drawTable(data){
    
    
    let select = document.getElementById('tabla');
    let str = ` `
   
    for (var item of data) {

        str += `<tr idUser="${item.id}" idPerfil="${item.perfilO.idPerfil}" class="chiquito">
                                                    <td class="element">${item.nombre}</td>
                                                    <td class="element">${item.apellido}</td>
                                                    <td class="element">${item.email}</td>
                                                    <td class="element">${item.estatus == 1 ? 'habilitado' : 'deshabilitado' }</td>
                                                    <td class="element">${item.perfilO.nombre}</td>
                                                 <td>         
                                                      <button class="seeRespon btn btn-primary btn-sm">
                                                            <i class="fas fa-edit"></i>
                                                        </button>    
                                                        <button class="formPass btn btn-warning btn-sm">
                                                            <i class="fas fa-key"></i>
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
    let id = $(ele).attr('idUser')
    let element = $(this).closest('tr').find('.element')
    let idPerfil = $(ele).attr('idPerfil')
 
    var data = {
        id:id,
        nombre:element[0].outerText,
        apellido:element[1].outerText,
        email:element[2].outerText,
        estado:element[3].outerText,
    perfil:{
        idPerfil:idPerfil,
        nombre:element[4].outerText
    }
    }
    update(data)
    
})

function update(data){
    
    

    $('#modalUpdate').modal('show')
    
    $('#idE').val(data.id)
    $('#nameE').val(data.nombre)
    $('#surnameE').val(data.apellido)
    $('#emailE').val(data.email)
    
    generatePerfiles('perfilE', data.perfil)
    generateEstadoEdit(data.estado)
    
}

document.getElementById('formEdit').addEventListener('submit', function (ev){
    ev.preventDefault()
    
    let id = document.getElementById('idE').value
      let nombre = document.getElementById('nameE').value
    let surname = document.getElementById('surnameE').value
    let email = document.getElementById('emailE').value
    let perfil = document.getElementById('perfilE').value
    let estado = document.getElementById('estadoE').value

    if (nombre == '' || surname == '' || email == '' || perfil == '' || estado == ''
             || nombre.length <= 2 || surname.length <= 2 || !email.toString().includes("@")) {
        messageInfo('complete el formulario')
    } else {

        let data = {
            id:id,
            nombre: nombre,
            apellido: surname,
            correo: email,
            perfil: perfil,
            estado: estado
        }

      
        editRecord(data);
    }
    
})


function editRecord(data){
    $.ajax({
        type: "POST",
        url: './editUser',
        datatype: 'json',
        data:data
    }).done(function (data) {
   
      
        if (data) {
            messageOk('Editado con éxito')
            Users()
        }else{
            messageError('=( ups ocurrio un error')
        }
        
        
    })
}