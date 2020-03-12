function validationDate(data){
    if(data.fechaInicial === "" || data.fechaFinal === ""){
        messageInfo('Ingrese el rango de fechas')
        return false;
    }else{
        return true;
    }
}

function messageInfo(message){
     Swal.fire({
            icon: 'info',
            title: message,
            showConfirmButton: true
        })
}
function messageError(message){
     Swal.fire({
            icon: 'error',
            title: message,
            showConfirmButton: true
        })
}