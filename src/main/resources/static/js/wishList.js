//세정
var checkAll = document.querySelector('#checkAll');

checkAll.addEventListener('click', function () {

    var isChecked = checkAll.checked;

    if (isChecked) {
        const checkboxes = document.querySelectorAll('.chk');
        console.log('test');
        for (let i = 0; i < checkboxes.length; i++) {
            console.log('test2');
            let checkbox = checkboxes[i];
            checkbox.checked = true;
        }
    }

    else {
        const checkboxes = document.querySelectorAll('.chk');
        for (let i = 0; i < checkboxes.length; i++) {
            let checkbox = checkboxes[i]
            checkbox.checked = false;
        }
    }
});


// 하나 미체크시 전체 체크 없어짐
////////////////////////////////////////////////////////////

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

    });
}


/*
const orderButton = document.getElementById("orderButton");

if(orderButton){
    orderButton.addEventListener("click", function(){
        let body = JSON.stringify({
             totalPrice : document.getElementById("totalPriceId").value,

        });
    });
}
*/



//체크박스에서 선택된 상품 삭제

const selectItemRemove = document.getElementById("selectItemRemove");
  //db에 들어있는 정보를 삭제해야함
  //wish_tb에 있는 정보만 삭제하면 되나?
  //체크된 정보만 controller에 넘겨서 customerID로 그 레코드를 지우기? -->중복된 customerID까지 지울지도 모름

if(selectItemRemove){
    selectItemRemove.addEventListener("click",function(){

    const wishIds =  toList(Array.from(document.getElementsByClassName("wishIds")),document.getElementsByClassName('chk'));
    const productGroupIds = toList(Array.from(document.getElementsByClassName("productGroupIds")),document.getElementsByClassName('chk'));

    console.log(wishIds);
    console.log("productGroupIds : ",productGroupIds);


     console.log('진입1');
     fetch(`/wishItemDelet`, {
        method :"POST",
        headers : {
        "content-type" : "application/json"
        },
        body : JSON.stringify({
        wishIds : wishIds,
        productGroupIds :  productGroupIds

        })
     }).then(response => {
                 if((response.status === 200 || response.status === 201)){
                   alert("삭제가 완료되었습니다.");
                   //post형식의 redirect가 먹히지 않을때 사용하는 코드
                   location.replace('/wishlist');
                   return response.text();
                 }
                 else{
                    throw new Error("db에 샐렉트문 저장 안됌");
                 }
                  }).catch(error =>{
                       console.log(error);//에러에러 나옴
                  })

    });


  }

//옵션 클릭시 button에 가격, 이름 뜨게하기
/*
function optionClick(event){
   //모든 드롭다운 클래스를 들고와서 배열로 클릭이 맞는지 확인하기
   //이하


  const dropdownButton = document.querySelector(".btn.btn-outline-success.dropdown-toggle");
  const productName =  document.querySelector(".productName").textContent;
*/
/*  for(i=0;i<dropdownButton.length; i++){
        if(dropdownButton[i].checked){
           dropdownButton[i].textContent = productName[i];
        }

  }*//*


 dropdownButton.textContent = document.querySelector(".productName").textContent;
}
*/
/*


 function optionClick() {
            // 클릭된 a 태그를 가져옵니다
            const clickedItem = event.currentTarget;
            if (!clickedItem) return; // 클릭된 요소가 .dropdown-item 내부가 아니라면 아무것도 하지 않음

            // 각 span 태그의 텍스트를 가져옵니다
            const spans = document.querySelectorAll('span');
            const productName = spans[0].textContent;
            const productPrice = spans[1].textContent;
            const productId = spans[2].textContent;

            // 'dropdown-toggle' 버튼의 텍스트를 업데이트합니다
            const dropdownButton = document.querySelectorAll("#dropdownMenuButton1");


              dropdownButton[0].textContent = `${productName} - ${productPrice} - ${productId}`;


        }
*/

//챗지피티가 짜준 코드. 아주 획기적이고 다시 해석하라
   function optionClick(event) {
        // 클릭된 a 태그를 가져옵니다
        const clickedItem = event.currentTarget;
        if (!clickedItem) return; // 클릭된 요소가 .dropdown-item 내부가 아니라면 아무것도 하지 않음

        // 각 span 태그의 텍스트를 가져옵니다
        const spans = clickedItem.querySelectorAll('span');
        const button = clickedItem.querySelectorAll('input');
        const productName = spans[0].textContent;
        const productPrice = spans[1].textContent;
//        const productId = spans[2].textContent;
        const productId = button[0].value;

        // 'dropdown-toggle' 버튼의 텍스트를 업데이트합니다
        const dropdownButton = clickedItem.closest('.modal').querySelector('.dropdown-toggle');
        dropdownButton.textContent = productName + " - " + productPrice + " - " + productId;
    }


//장바구니 등록 클릭시 장바구니로 데이터 넘기기
/*    function.cartPushButton(){

    }*/  //해석하라 해석하라!!!
        function addToCart(button) {
//            const spans = document.querySelectorAll('.productName, .productPrice, .productId');
            console.log(button.parentElement.previousElementSibling.children[0].children[1].children[0].children[0].children[0]);

            const data = {
                productIds : [button.parentElement.previousElementSibling.children[0].children[1].children[0].children[0].children[0].value],
                counts : [1]
            };

            fetch('/api/cart', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to add item to cart');
                }
                // 여기에 필요한 작업 추가 (예: 성공 메시지 표시 등)
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }






    function toList(elements, checkList){
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


  function toList2(elements){
    //  리스트형태로 반환
    //  html에서 each 로 반복한 데이터들
        let listName = [];
       //elements 반복씀
        elements.forEach((element) => {
            //공백아닐때 listName으로 데이터 넣음
            if(element.value != ''){
                listName.push(element.value);
            }
        });

        return listName;
    }
