//   // 페이지 로드 후 실행
//   $(document).ready(function () {
//    // 첫 번째 product_group_id 입력란의 값이 변경될 때마다 두 번째 product_group_id1 입력란에 동일한 값을 설정
//    $('#product_group_id').on('input', function () {
//        $('#product_group_id1').val($(this).val());
//        $('#productGroupId').val($(this).val());
//    });
//});


/*
$(document).ready(function () {
    $('#name_option').on('input', function () {
        $('#name').val($(this).val());
    });
});
*/





    function addOptionFieldset() {
        var optionCounter = $("#optionalProducts fieldset").length + 1;

        var newFieldset = `
            <fieldset>
                <legend id="container_option_${optionCounter}"><h1>제품상세 <strong>옵션 ${optionCounter}</strong></h1></legend>
                <ul id="add_option_${optionCounter}" autocapitalize="off">
                    <hr>
                    <li>
                        <label for="price_${optionCounter}">가격</label>
                        <input class="form-contorl" type="number" id="price_${optionCounter}" name="options[${optionCounter}][price]" >
                        <span>원</span>
                    </li>
                    <hr>
                    <li>
                        <label for="quantity_${optionCounter}">재고량</label>
                        <input class="form-contorl" type="number" id="quantity_${optionCounter}" name="options[${optionCounter}][quantity]" >
                        <span>개</span>
                    </li>
                    <hr>
                    <li>
                        <label for="name_${optionCounter}">제품이름</label>
                        <input class="form-contorl" type="text" id="name_${optionCounter}" name="options[${optionCounter}][name]" >
                    </li>
                    <hr>
                    <li>
                        <label for="imgSrc_${optionCounter}">이미지</label>
                        <input class="form-contorl" type="file" id="imgSrc_${optionCounter}" name="options[${optionCounter}][img_src]"  >
                    </li>
                </ul>
            </fieldset>
            <button onclick="removeOptionFieldset(this)">삭제</button>
            <hr>
        `;

        $("#optionalProducts").append(newFieldset);
    }

    function removeOptionFieldset(button) {
        $(button).prev().remove();
        $(button).remove();
    }



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




//셀렉트 옵션쪽
$(function(){
	$("#category_detail_id").val("${param.categoryDetailId}").attr("selected","selected");
});

