$(function () {

    menu('menuUsers')

    Users()

})

$('#botonAdd').click(function () {

    $('#modalAdd').modal('show')
    generateEstado()
    generatePerfiles('perfil', null)

})

function generatePerfiles(per, value) {
    $.ajax({
        type: "GET",
        url: './getPerfil',
        datatype: 'json'
    }).done(function (data) {

     
        
     let perfil = document.getElementById(per)
     let srt = ``
        if (value != null) {
            srt = `<option value="${value.idPerfil}">${value.nombre}</option>`
            for (var item of data) {
                 if (item.idPerfil !== parseInt(value.idPerfil)) {
                        srt += `<option value="${item.idPerfil}">${item.nombre}</option>`   
                   }
       }
        }else{
            srt = '<option value="">No</option>'
            for (var item of data) {
                srt += `<option value="${item.idPerfil}">${item.nombre}</option>`              
           }
       }

    perfil.innerHTML = srt

    })

}


  function generateEstadoEdit(num) {
    let estado = document.getElementById('estadoE')
    let ac = 'Habilitado'
    let ina = 'Deshabilitado'
    let srt = ``
      if (num=='habilitado') {
        srt += `<option value="${1}">${ac}</option>`
        srt += `<option value="${0}">${ina}</option>`
    }else{
        srt += `<option value="${0}">${ina}</option>`
        srt += `<option value="${1}">${ac}</option>`
    }

    estado.innerHTML = srt

}



function generateEstado() {

    let estado = document.getElementById('estado')
    let srt = '<option value="">No</option>'
    let ac = 'Habilitado'
    let ina = 'Deshabilitado'
    srt += `<option value="${1}">${ac}</option>`
    srt += `<option value="${0}">${ina}</option>`

    estado.innerHTML = srt

}

document.getElementById('formAdd').addEventListener('submit', function (ev){
    
    ev.preventDefault()
    
    let nombre = document.getElementById('name').value
    let surname = document.getElementById('surname').value
    let email = document.getElementById('email').value
    let pass = document.getElementById('password').value
    
    let passTwo = document.getElementById('passwordTwo').value
    
    let perfil = document.getElementById('perfil').value
    let estado = document.getElementById('estado').value

    if (nombre == '' || surname == '' || email == '' || pass == '' || perfil == '' || estado == ''
            || nombre.length <= 2 || surname.length <= 2 || pass.length <= 6 || !email.toString().includes('@')) {
        messageInfo('complete el formulario')
        
    } else if(pass !== passTwo){
        messageInfo('Las contraseñas no coinciden')
    }else {

        let data = {
            nombre: nombre,
            apellido: surname,
            correo: email,
            contra: pass,
            perfil: perfil,
            estado: estado
        }

        addNewRecord(data);
    }


    
})

function addNewRecord(data) {
    $.ajax({
        type: "POST",
        url: './addUser',
        datatype: 'json',
        data: data
    }).done(function (data) {

        messages(data)

    })

}

function messages(data) {
    
    if (data == 1) {
        messageOk('Generado con éxito')
        $('#formAdd').removeClass('was-validated')
        $('#formAdd').trigger('reset')
        Users()
    } else if (data == 2) {
        messageError('Correo ya esta en uso =(')
    } else {
        messageError('Up ocurrio un error =(')
    }

}

function Users() {


    $.ajax({
        type: "POST",
        url: './SelectUsers',
        datatype: 'json'
    }).done(function (data) {

        drawTable(data)

    })

}


