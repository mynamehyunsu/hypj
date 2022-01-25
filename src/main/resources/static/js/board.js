function replysave(){

    $.ajax({

        url : '/boardReplySave.do',
        type : 'POST',
        data : { 'num' : $('#boardnum').val(),'content' : $('#reply-content').val() },
        datatype : 'json',
        success : function(result){
            alert('댓글달기 성공');
            $('#reply-content').val('');
            location.href='';
        },
        error : function(request,status,error){
            console.log(error);
        }
    });
}
