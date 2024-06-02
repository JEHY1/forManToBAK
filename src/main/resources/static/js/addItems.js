   // 페이지 로드 후 실행
   $(document).ready(function () {
    // 첫 번째 product_group_id 입력란의 값이 변경될 때마다 두 번째 product_group_id1 입력란에 동일한 값을 설정
    $('#product_group_id').on('input', function () {
        $('#product_group_id1').val($(this).val());
        $('#product_group_id2').val($(this).val());
    });
});


$(document).ready(function () {
    $('#name').on('input', function () {
        $('#name_option').val($(this).val());
    });
});


$(document).ready(function(){
    // 추가된 제품 옵션 순서를 나타내는 변수
    var optionCounter = 1;

    // "추가" 버튼 클릭 시
    $("#addOptionalProduct").click(function(){
        // 새로운 제품 옵션 입력 필드를 생성하여 추가합니다.
        var newFieldset = `
            <fieldset>
                <legend id="container_option" ><h1>제품상세 <strong>옵션 ${optionCounter}</strong></h1></legend>
                <ul id="add_option  " autocapitalize="off">
                <li>
                    <label for="product_id">제품일련번호</label>
                    <input class="form-control" type="text" id="product_id" name="product_id">
                </li>
                <hr>
                <li>
                    <label for="product_group_id">제품그룹일련번호</label>
                    <input class="form-control" type="text" id="product_group_id" name="product_group_id" placeholder="1.메이크업/2.썬/3.바디/4.헤어/5.클렌징/6.향수" >
                </li>
                <hr>
                <li>
                    <label for="price">가격</label>
                    <input class="form-control" type="number" id="price" name="price">
                    <span>원</span>
                </li>
                <hr>
                <li>
                    <label for="quantity">재고량</label>
                    <input class="form-control" type="number" id="quantity" name="quantity">
                    <span>개</span>
                </li>
                <hr>
                <li>
                    <label for="name">제품이름</label>
                    <input class="form-control" type="text" id="name" name="name" >
                </li>
                <hr>
                <li>
                    <label for="img_src">이미지</label>
                    <input class="form-control" type="file" id="img_src" multiple>
                </li>
            </fieldset>
            <hr>
        `;
        // 새로운 필드셋을 #optionalProducts에 추가합니다.
        $("#optionalProducts").append(newFieldset);
        optionCounter++; // 다음 옵션 번호 증가
    });
});


// $(document).ready (function () {
//     $('.btnAdd').click (function () {
//         $('.buttons').append (
//             ' <input class="form-contorl" type="text" id="product_id" name="product_id" > <input type="button" class="btnRemove" value="Remove"><br>'
//         ); // end append
//         $('.btnRemove').on('click', function () {
//             $(this).prev().remove (); // remove the textbox
//             $(this).next ().remove (); // remove the <br>
//             $(this).remove (); // remove the button
//         });
//     }); // end click
// }); // end ready