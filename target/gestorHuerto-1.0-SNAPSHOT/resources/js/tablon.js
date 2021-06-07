 $(document).ready(function() {
                
    $('span').on('click',function(event) {
        var li = $(this).parent();
        var elemento = $(this).parent().attr('id');	
        //alert(elemento);

        $.ajax({  
            type: "POST",  
            url: "../Controller?opID=EliminarHorario",  
            data: "elemento="+elemento 

          }).done(function (Response) {
              //si todo va bien borra el link

              li.remove();
              $('#infoHorario').html("Horario eliminado con éxito");
              if($('.exito')){$('.exito').remove();}
        })
        .fail(function (Response) {

        });

    });//fin eliminar

//                elimina anuncio
    $('td.delete').on('click',function(event) {
        var fila = $(this).parent();
        var elemento = $(this).parent().attr('id');	
       //alert("tr" + elemento);

        $.ajax({  
            type: "POST",  
            url: "../Controller?opID=EliminarAnuncio",  
            data: "elemento="+elemento 

          }).done(function (Response) {
              //si todo va bien borra el link

              fila.remove();
              $('#infoHorario').html("Anuncio eliminado con éxito");
              if($('.exito')){$('.exito').remove();}
        })
        .fail(function (Response) {

        });

    });//fin eliminar anuncio
                
               
});


