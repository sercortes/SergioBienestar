
$(document).on('click', '.formPass', function (){
    
    let ele = $(this)[0].parentElement.parentElement
    let eleTwo = $(this).closest('tr').find('.element')
    let id = $(ele).attr('idUser')
    let email = eleTwo[2].outerText
    let data = {
        id:id,
        email:email
    }
    
    updatePassword(data)
    
})

function updatePassword(data){
    
    $('#emailPass').val(data.email)
    $('#modalUpdatePassword').modal('show')
    $('#idPass').val(data.id)
    
}


$('#botonUpdatePass').click(function(){
     
     let id = $('#idPass').val()
     let passOne = $('#passE').val()
     let passTwo = $('#passTwo').val()
     
     if (passOne == '' || passTwo == '') {
        messageInfo('complete el formulario')
        return false;
    }
    if (passOne !== passTwo) {
        messageInfo('las contraseñas no coinciden')
        return false;
    }
     if (passOne.length <=6 || passTwo <=6) {
        messageInfo('Genere una contraseña más larga')
        return false;
    }
    
    let data = {
        id:id,
        pass:passOne
    }
 
    updatePasswordQuery(data)
    
})

function updatePasswordQuery(data){
    $.ajax({
        type: "POST",
        url: './updatePass',
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