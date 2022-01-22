//let index={
//    init : function(){
//        $("#btn-save").on("click",() => {
//            this.save();
//        });
//    },
//    save : function(){
//        alert("book save함수 실행");
//        let data = {
//            subject : $("#subject").val(),
//            price : $("#price").val(),
//            content : $("#content").val()
//        }
//        let files = e.target.files;
//        let file = {
//            file : $("#file")[0].files[0];
//        }
//
//        $.ajax({
//                //회원가입 수행요청
//                    type : "POST",
//                    url : "/bookPostProc.do",
//                    data : JSON.stringify(file), //http 데이터 바디 넘겨줄때
//                    contentType : "application/json; charset=utf-8",//body 데이터가 어떤타입인지
//                    dataType : "json"//받을때
//                }).done(function(result){//url로 가서 응답을 받고 결과물이 있으면 result를 반환
//                    alert("파일정보불러오기성공");
//                    alert(result);
//                    location.href="";
//                }).fail(function(){
//
//                });
////        console.log(data);
//        $.ajax({
//        //회원가입 수행요청
//            type : "POST",
//            url : "/bookPostProc.do",
//            data : JSON.stringify(data), //http 데이터 바디 넘겨줄때
//            contentType : "application/json; charset=utf-8",//body 데이터가 어떤타입인지
//            dataType : "json"//받을때
//        }).done(function(result){//url로 가서 응답을 받고 결과물이 있으면 result를 반환
//            alert("책 등록이 완료 되었습니다");
//            alert(result);
//            location.href="/mainBookList.do";
//        }).fail(function(){
//
//        });//ajax통신을 이용해서 4개의 데이터를  json으로 변경하여  insert 요청
//    }
//}
//
//index.init();
let index = {
    init : function(){
        $("#btn-save").on("click",() => {
            this.save();
        });
    },
    save : function(){
        event.preventDefault(); //폼동작 정지

        var form = $('#uploadForm')[0]
        var formData = new FormData(form);

        //버튼 비활성화
        $("#btn-save").prop("disabled",true);

        //ajax통신을 이용해서  3개의 데이터를 json으로 변경하여 insert 요청
        //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해주세요
        $.ajax({
            type : "POST",
            enctype : "multipart/form-data",
            url : "/bookPostProc.do",
            data : formData,//http 데이터 바디 넘겨줄때
            //ajax로 multipart 방식으로 보낼 때, processData는
            //일반적으로 서버에 전달되는 data는 query String 형태로 전달되어 집니다.
            //data 파라미터로 전달된 데이터는 jQuery 내부적으로 쿼리스트링으로 만들어 보내는데,
            //파일전송에는 이를 피해야함으로false로 설정해줍니다.
            processData : false,
            // contentType은 default 값이 "application/x-www-form = urlencoded; charset = UTF-8"
            //이므로,보내줄 때 multipart/form-data로 전송해야 하기 때문에 false로 설정해줍니다.
            contentType : false,//body 데이터가 어떤타입인지
            cache : false,
            timeout : 600000
        }).done(function(result){//url로 가서 응답을 받고 결과물이 있으면 result를 반환
            //버튼활성화
            $("#btn-save").prop("disabled",false);
            alert("파일업로드 성공");
            console.log(result);
//            location.href="/mainBookList.do";
        }).fail(function(){

        });

    }
}
index.init();
