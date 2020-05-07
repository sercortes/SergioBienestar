
$(document).ready(function () {


$('#loader').hide()

menu('menuUpload')

})

$("#send").click(function (e) {
    
    e.preventDefault()

    let file = $('#fileToUpload').val()
    
    if (file == "") {
        messageInfo('Seleccione un archivo')
        return false
    }
    
     if (!checkextension()) {

        return false
    }
    
//    let size = $('#fileToUpload')[0].files[0].size
//    let sizeDefined = Math.round((size / 1024))
//    
//    if (sizeDefined > 14000) {
//        messageError('archivo muy grande')
//        return false
//    }


    var form = $('#formulario')[0]
    var data = new FormData(form)

    $('#loader').show()

    document.getElementById('send').disabled = true
    document.getElementById('fileToUpload').disabled = true
    $('li a').addClass('disabled')

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "Uploading",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (data) {

           console.log(data)
            
                if (data === '0') {
                    messageError('Seleccione un archivo válido')
                } else if(data === '00'){
                    messageInfo('Seleccione un archivo válido')
                }else if(data === '1'){
                    messageInfo('archivo ya subido')
                }else{
                    generateSucces()
                }

            hideAnimation()

        },
        error: function (e) {

            console.log(e)
            
            hideAnimation()
            
            messageError('=(')

        }
    });
    
 
})


function hideAnimation() {
    $('#loader').hide()
    document.getElementById('send').disabled = false
    document.getElementById('fileToUpload').disabled = false
    $('li a').removeClass('disabled')
    $('#formulario').trigger('reset')
}


function generateSucces() {

    Swal.fire({
        title: "Buen trabajo!",
        text: "El archivo ha sido importado con éxito!",
        icon: "success",
        backdrop: `
    rgba(0,0,123,0.4)
    url("assets/img/cat3.gif")
    left top
    no-repeat`
    });
}


function checkextension() {
    var file = document.querySelector("#fileToUpload");
    if (/\.(xls)$/i.test(file.files[0].name) === false) {
            messageInfo('Seleccione un archivo válido')
        return false
    } else {
        return true
    }
}