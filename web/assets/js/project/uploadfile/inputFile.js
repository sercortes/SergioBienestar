
$(document).ready(function () {


$('#loader').hide()

let menuItem = document.getElementById('menuUpload')
menuItem.classList.add('active')


$("#send").click(function (e) {

    let file = $('#fileToUpload').val()

    if (file == "") {
        Swal.fire({
            icon: 'info',
            title: 'Seleccione un archivo',
            showConfirmButton: true,
            timer: 1500,
        })
        return false
    }

    if (!checkextension()) {

        return false
    }



    e.preventDefault()

    var form = $('#formulario')[0]
    var data = new FormData(form)


    $('#loader').show()

    document.getElementById('send').disabled = true
    document.getElementById('fileToUpload').disabled = true

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "Uploading",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            console.log(data)
            
                if (data == '0') {
                    generateError()
                }else{
                    generateSucces()
                }

            hideAnimation()

        },
        error: function (e) {

            console.log(e)
            
            hideAnimation()
            
            generateError()

        }
    });
    
 
})


function hideAnimation() {
    $('#loader').hide()
    document.getElementById('send').disabled = false
    document.getElementById('fileToUpload').disabled = false
    $('#formulario').trigger('reset')
}


function generateSucces() {

    Swal.fire({
        title: "Buen trabajo!",
        text: "El archivo ha sido importado con éxito!",
        icon: "success",
        backdrop: `
    rgba(0,0,123,0.4)
    url("assets/img/cat2.gif")
    left top
    no-repeat
  `
    });
}
function generateError() {
    Swal.fire({
        icon: 'error',
        title: 'Seleccione un archivo válido',
        showConfirmButton: true
    })

}

function checkextension() {
    var file = document.querySelector("#fileToUpload");
    if (/\.(xls)$/i.test(file.files[0].name) === false) {
        Swal.fire({
            icon: 'info',
            title: 'Seleccione un archivo válido',
            showConfirmButton: true,
            timer: 1500
        })
        return false
    } else {
        return true
    }
}



})