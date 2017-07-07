
var tbDtMatrix = localStorage.getItem("tbDtMatrix");
tbDtMatrix = JSON.parse(tbDtMatrix);
if(tbDtMatrix == null)
tbDtMatrix = [];

$(document).ready(
    function(){
        var servico = 'QRArmazenado.html';
        $.get(servico,
            function(data){
                $('.content').html(data);
            }
        )
    }
)

$('.itemMenu').click(
    function(e){
       e.preventDefault();
       var servico = $(this).attr('href') ;
       $.get(servico,function(data){
            $('.content').empty();
            $('.content').html(data);
        }
        )
    }
)

$('.areaQR').ready(function(){
    cargaInicial();
});

$(document).on('click','a.deleteDM',function(){
    var i = $(this).attr('alt')    
    tbDtMatrix.splice(i,1);
    localStorage.setItem("tbDtMatrix", JSON.stringify(tbDtMatrix));
    $(this).parent().remove();
});

$(document).on('click','.addQ',function(){
    adicionar($('.addDatamatrix').val());
    var datamatrix =JSON.stringify({datamatrix:  $('.addDatamatrix').val() });
    tbDtMatrix.push(datamatrix);
    localStorage.setItem("tbDtMatrix", JSON.stringify(tbDtMatrix));
    $('.addDatamatrix').val('');
});

$(document).on('click','.gerarQ', function(){
    var inicio = $('.inicio').val();
    var fim = $('.fim').val();

    for (inicio; inicio <= fim ; inicio++) {
        console.log(inicio)
    }
});

function adicionar(datamatrix, i){
    if (datamatrix == '')
        return ;

    var link = 'http://datamatrix.kaywa.com/img.php?s=8&d='+ datamatrix;
    var img = $('<img></img>').attr('src',link);

    $('.areaQR').append(
        $('<div></div>').append(
            img, 
            $('<label></label>').append(datamatrix),
            $('<label></label>').append('JAMES'),
            $('<a></a>').append('Excluir').addClass('btn btn-danger deleteDM').attr('alt', i)
        ).addClass('qr-group')
    );  
}

function cargaInicial(){
    var servico = 'js/json/inicial.json';
    $.getJSON(servico, 
        function(data){
            for (var i = 0; i <  data.length; i++) {
               adicionar(data[i].datamatrix,-1);
            }
            for (var i = tbDtMatrix.length - 1; i >= 0; i--) {
                var dataM = JSON.parse(tbDtMatrix[i])
                adicionar(dataM.datamatrix, i);
            }
        }
    )
}




