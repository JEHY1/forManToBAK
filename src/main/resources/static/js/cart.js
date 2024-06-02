//세정
var checkAll = document.querySelector('#checkAll');

checkAll.addEventListener('click', function () {

    var totalPriceResults = document.querySelectorAll(".totalPriceResult");//물건 구매가
    var isChecked = checkAll.checked; //체크했을때

    if (isChecked) {
        const checkboxes = document.querySelectorAll('.chk');//체크하나

        for (let i = 0; i < checkboxes.length; i++) {
            let checkbox = checkboxes[i];
            checkbox.checked = true;
            // 전체 체크시 총 판매가에 나타남
            document.getElementById("totalPriceId").innerText  =
            parseInt(document.getElementById("totalPriceId").innerText) + parseInt(totalPriceResults[i].innerText);

        }
    }

    else {
        const checkboxes = document.querySelectorAll('.chk');
        for (let i = 0; i < checkboxes.length; i++) {
            let checkbox = checkboxes[i]
            checkbox.checked = false;
            document.getElementById("totalPriceId").innerText = 0;

        }
    }
})


// 하나 미체크시 전체 체크 없어짐
////////////////////////////////////////////////////////////


var totalPriceResults = document.querySelectorAll(".totalPriceResult");
var checkboxes = document.querySelectorAll('.chk');
for (let i = 0; i < checkboxes.length; i++) {
    let checkbox = checkboxes[i]
    checkbox.addEventListener('click', function () {

        const totalCnt = checkboxes.length;

        const checkedCnt = document.querySelectorAll('.chk:checked').length;

        if (totalCnt == checkedCnt) {
            document.querySelector('#checkAll').checked = true;
        }
        else {
            document.querySelector('#checkAll').checked = false;

        }
        // 체크박스 하나 클릭시 총 판매가 플러스
        if(checkboxes[i].checked){
            document.getElementById("totalPriceId").innerText =parseInt(document.getElementById("totalPriceId").innerText)
            +parseInt(totalPriceResults[i].innerText);
        }
        // 체크박스 하나 클릭시 총 판매가 빼기
        if(!(checkboxes[i].checked)){
            document.getElementById("totalPriceId").innerText =  document.getElementById("totalPriceId").innerText
            - totalPriceResults[i].innerText;
        }
    });
}

//<!-- 수량 변경시 구매가 바뀜 -->
/*

const selectId = document.getElementById("selectId");

 if(selectId){
    selectId.addEventListener("change", function(){

    alert("확인1");

      var priceCounts = document.querySelectorAll(".priceCount");
        var totalPriceResults = document.querySelectorAll(".totalPriceResult");
        var originalPrice = document.querySelectorAll(".originalPrice");
        var chk = document.querySelectorAll(".chk");
        // var isChecked = checkAll.checked;
        var checkSum = 0;


        //querySelectorAll은 배열로 반환
        for (let i = 0; i < priceCounts.length; i++) {
            let x = priceCounts[i].value;
            let unitPrice = parseInt(originalPrice[i].innerText); // totalPriceResult 클래스 안의 텍스트 값을 정수로 파싱하여 추출
            totalPriceResults[i].innerHTML = x * unitPrice;

            // 체크시 총 판매가에 최종 바뀜
            if (chk[i].checked) {
                checkSum = parseInt(totalPriceResults[i].innerText) + checkSum;
                 alert("확인2");
                document.getElementById("totalPriceId").innerText = checkSum;
            }
        }


        console.log('셀랙트 박스 변환');
        alert("확인3");
            fetch(`/cart`,{
                method : "POST",
                headers : {
                     "content-type" : "application/json"
                },
                body : JSON.stringify({
                    productId : document.getElementById("productId"),
                    count: document.getElementById("countid")

                })
            }).then(response => {
                  if((response.status === 200 || response.status === 201)){
                    alert("db에 저장된 수량이 변경됩니다.");
                    return response.text();
                  }
                  else{
                     throw new Error("db에 샐렉트문 저장 안됌");
                  }
             }).then(html => {
                        document.documentElement.innerHTML = html;


                   }).catch(error =>{
                        console.log(error);//에러에러 나옴
                   })

    });

 }
*/







//  <!-- 수량 변경시 구매가 바뀜 -->

//var selectId = document.getElementById("selectId");

function changeCount() {
//console.log(document.getElementsByClassName("productIds"));
//console.log(Array.from(document.getElementsByClassName("productIds")));
//console.log(toListOne(document.getElementsByClassName("productIds")));
//console.log(toListOne(Array.from(document.getElementsByClassName("productIds"))));

console.log('첫번째 수량 변경');


    var priceCounts = document.querySelectorAll(".priceCount");
    var totalPriceResults = document.querySelectorAll(".totalPriceResult");
    var originalPrice = document.querySelectorAll(".originalPrice");
    var chk = document.querySelectorAll(".chk");
    // var isChecked = checkAll.checked;
    var checkSum = 0;


    //querySelectorAll은 배열로 반환
    for (let i = 0; i < priceCounts.length; i++) {
        let x = priceCounts[i].value;
        let unitPrice = parseInt(originalPrice[i].innerText); // totalPriceResult 클래스 안의 텍스트 값을 정수로 파싱하여 추출
        totalPriceResults[i].innerHTML = x * unitPrice;

        // 체크시 총 판매가에 최종 바뀜
        if (chk[i].checked) {
            checkSum = parseInt(totalPriceResults[i].innerText) + checkSum;
            document.getElementById("totalPriceId").innerText = checkSum;
        }
    }

    //수량 변경시 db에 저장후 페이지 리다이렉트 하기

console.log('셀랙트 박스 변환');

    fetch(`/selectUpdate`,{
        method : "POST",
        headers : {
             "content-type" : "application/json"
        },
        body : JSON.stringify({
//             productId : selection.parentElement.parentElement.children[0].value,
//            count: document.getElementById("selectId")
              productId : toList(Array.from(document.getElementsByClassName("productIds"))),
              count : toList(Array.from(document.getElementsByClassName("priceCount"))), // select로 변경한 수량
              customerId : toList(Array.from(document.getElementsByClassName("customerId"))),
              countOri : toList(Array.from(document.getElementsByClassName("countOri"))) // 원본 수량 모음


        })
    }).then(response => {
          if((response.status === 200 || response.status === 201)){
            alert("수량 변경이 완료되었습니다.");
            return response.text();
          }
          else{
             throw new Error("db에 샐렉트문 저장 안됌");
          }
           }).catch(error =>{
                console.log(error);//에러에러 나옴
           })


}







//장바구니--> 주문페이지로 데이터 넘기기
//된다면 아무것도 체크하지 않았을때 주문페이지가 넘어가지 않도록 하기
const orderButton = document.getElementById("orderButton");

if(orderButton){
    orderButton.addEventListener("click", () => {
    console.log('click');
    //총가격, 수량, 제품id를 order페이지로 url호출
       fetch(`/order`, {
            method : "POST",
            headers : {
                "content-type" : "application/json"
            },
            body : JSON.stringify({
                //총가격(int) 수량(List) 제품id(List)
                //디티오 :
                  totalPrice : document.getElementById("totalPriceId").textContent,
                  //Array.from을 사용한 이유는 json형태를 주기 위해서 , toList는 리스트 형태를 주기 위해서!
                  productIds : toList3(Array.from(document.getElementsByClassName("productIds")), document.getElementsByClassName('chk')),
                  counts : toList3(Array.from(document.getElementsByClassName("priceCount")), document.getElementsByClassName('chk')),
                  cartIds : toList2(Array.from(document.getElementsByClassName('chk')))
            })
            //결과response 체인기법
       }).then(response => {
            //성공
            //(response.status === 200 && response.status === 201)은 안됌
//            response.ok도 됌
            if((response.status === 200 || response.status === 201)){

                alert("주문 페이지로 넘아갑니다.");
                //리턴된 페이지에 text로 변환->body만 출력이 되도록
                //response를 text로 변환
                return response.text();
            }
            else{
                throw new Error("에러에러");
            }

            //text로 준 데이터를 그려주기
       }).then(html => {
            document.documentElement.innerHTML = html;


       }).catch(error =>{
            console.log(error);//에러에러 나옴
       })

    });
}

//다시 질문!
function toList3(elements, checkList){
//  리스트형태로 반환
//  html에서 each 로 반복한 데이터들
    let listName = [];
   //elements 반복씀
    elements.forEach((element,index) => {
        //공백아닐때 listName으로 데이터 넣음
        if(element.value != '' && checkList[index++].checked){
            listName.push(element.value);
        }
    });

    return listName;
}

function toList(elements){
//  리스트형태로 반환
//  html에서 each 로 반복한 데이터들
    let listName = [];
   //elements 반복씀
    elements.forEach((element,index) => {
        //공백아닐때 listName으로 데이터 넣음
        if(element.value != ''){
            listName.push(element.value);
        }
    });

    return listName;
}

function toList2(elements){
    let list = [];
    elements.forEach(element => {
    if(element.checked)
        list.push(element.parentElement.parentElement.children[0].value);
    });
    return list;
}

function httpRequest(url, method, body) {
    return fetch(url, {
        method: method,
        headers: {
            "Content-Type": "application/json"
        },
        body: body
    });
}