//재현

moveTopButton = document.getElementById('move-top-btn');

if(moveTopButton){
    moveTopButton.addEventListener('click', () => {
        window.scrollTo({
            top: 0,
            behavior: 'smooth'
        });
    });
}

const itemButtons = document.getElementsByClassName('item-btns');

if(itemButtons){

    let productNameValues = Array.from(document.getElementsByClassName('selectableProductNames'));
    let productIdValues = Array.from(document.getElementsByClassName('selectableIds'));
    let productPriceValues = Array.from(document.getElementsByClassName('selectableProductPrices'));
    let totalPrice = document.getElementById('totalPrice');
    let totalPriceText =  document.getElementById('totalPriceText');

    Array.from(itemButtons).forEach((button, index) => {
        button.addEventListener('click', () => {

            if(document.getElementById('productId' + productIdValues[index].value) == null){
                let space = document.getElementById('productSpace');
                let productInfo = document.createElement('div');
                productInfo.setAttribute('class', 'row');
                productInfo.setAttribute('id', 'productId' + productIdValues[index].value);
                let productName = document.createElement('div');
                productName.setAttribute('class', 'col-8 d-flex align-items-center');

                let selectedInfo = document.createElement('input');
                selectedInfo.setAttribute('type', 'hidden');
                selectedInfo.setAttribute('class', 'selectedProductIds');
                selectedInfo.setAttribute('value', productIdValues[index].value);

                productName.setAttribute('text', productNameValues[index].value);
                productName.textContent = productNameValues[index].value;
                let buttonCol = document.createElement('div');
                buttonCol.setAttribute('class', 'col-3');

                let buttonGroup = document.createElement('div');
                buttonGroup.setAttribute('class', 'btn-group');
                buttonGroup.setAttribute('role', 'group');
                buttonGroup.setAttribute('aria-label', 'basic outlined example');

                let decButton = document.createElement('button');
                decButton.setAttribute('type', 'button');
                decButton.setAttribute('class', 'btn btn-outline-secondary');
                decButton.textContent = '-';

                let count = document.createElement('button');
                count.setAttribute('type', 'button');
                count.setAttribute('class', 'btn btn-outline-secondary disabled orderQuantitys');
                count.setAttribute('value', 1);
                count.setAttribute('id', 'productId' + productIdValues[index].value + 'count');
                count.textContent = "1";

                let incButton = document.createElement('button');
                incButton.setAttribute('type', 'button');
                incButton.setAttribute('class', 'btn btn-outline-secondary');
                incButton.textContent = '+';

                let productPrice = document.createElement('input');
                productPrice.setAttribute('type', 'hidden');
                productPrice.setAttribute('value', productPriceValues[index].value);


                totalPrice.setAttribute('value',  parseInt(productPriceValues[index].value) + parseInt(totalPrice.value));
                totalPriceText.textContent = totalPrice.value + '원';

                decButton.addEventListener('click', function() {
                    if(this.nextElementSibling.value - 1 > 0){
                        this.nextElementSibling.setAttribute('value', this.nextElementSibling.value - 1);
                        this.nextElementSibling.textContent = this.nextElementSibling.value;

                        //최종 가격 변경
                        totalPrice.setAttribute('value',  parseInt(totalPrice.value) - parseInt(productPriceValues[index].value));
                        totalPriceText.textContent = totalPrice.value + '원';

                        //note 변경
                        document.getElementById('product' + productIdValues[index].value + 'Note').children[1].textContent = '(수량 : ' + (parseInt(document.getElementById('product' + productIdValues[index].value + 'Note').children[1].textContent.slice(6, -1)) - 1) + ')';
                    }

                    else{
                        alert('1개 이하 구매 불가');
                    }
                });

                incButton.addEventListener('click', function() {
                    if(parseInt(this.previousElementSibling.value) + 1 < 11){
                        this.previousElementSibling.setAttribute('value', parseInt(this.previousElementSibling.value) + 1);
                        this.previousElementSibling.textContent = this.previousElementSibling.value;
                        totalPrice.setAttribute('value',  parseInt(productPriceValues[index].value) + parseInt(totalPrice.value));
                        totalPriceText.textContent = totalPrice.value + '원';

                        //note 변경
                        document.getElementById('product' + productIdValues[index].value + 'Note').children[1].textContent = '(수량 : ' + (parseInt(document.getElementById('product' + productIdValues[index].value + 'Note').children[1].textContent.slice(6, -1)) + 1) + ')';
                    }
                    else{
                        alert('각 상품의 최대 구매 수량 : 10');
                    }
                });

                let deleteButton = document.createElement('button');
                deleteButton.setAttribute('type', 'button');
                deleteButton.setAttribute('class', 'btn-close col');
                deleteButton.setAttribute('aria-label', 'Close');

                deleteButton.addEventListener('click', function(){

                    totalPrice.setAttribute('value',  parseInt(totalPrice.value) - parseInt(this.previousElementSibling.firstElementChild.firstElementChild.nextElementSibling.value * this.nextElementSibling.value));
                    totalPriceText.textContent = totalPrice.value + '원';
                    this.parentNode.remove();

                    //note 변경
                    document.getElementById('product' + productIdValues[index].value + 'Note').remove();
                });

                space.appendChild(productInfo);
                productInfo.appendChild(selectedInfo);
                productInfo.appendChild(productName);
                productInfo.appendChild(buttonCol);
                buttonCol.appendChild(buttonGroup);
                buttonGroup.appendChild(decButton);
                buttonGroup.appendChild(count);
                buttonGroup.appendChild(incButton);
                productInfo.appendChild(deleteButton);
                productInfo.appendChild(productPrice);

                //cartNote 만들기
                let productNote = document.createElement('div');
                productNote.classList.add('row');
                productNote.id = 'product' + productIdValues[index].value + 'Note';

                let productNameNote = document.createElement('div');
                productNameNote.classList.add('col-auto');
                productNameNote.textContent = productNameValues[index].value;

                let countNote = document.createElement('div');
                countNote.classList.add('col-auto');
                countNote.textContent = '(수량 : 1)';

                document.getElementById('cartNote').appendChild(productNote);
                productNote.appendChild(productNameNote);
                productNote.appendChild(countNote);



            }
            else{
                if(parseInt(document.getElementById('productId' + productIdValues[index].value + 'count').value) + 1 < 11){
                    document.getElementById('productId' + productIdValues[index].value + 'count').setAttribute('value', parseInt(document.getElementById('productId' + productIdValues[index].value + 'count').value) + 1);
                    document.getElementById('productId' + productIdValues[index].value + 'count').textContent = document.getElementById('productId' + productIdValues[index].value + 'count').value;

                    totalPrice.setAttribute('value',  parseInt(totalPrice.value) + parseInt(productPriceValues[index].value));
                    totalPriceText.textContent = totalPrice.value + '원';


                    document.getElementById('product' + productIdValues[index].value + 'Note').children[1].textContent = '(수량 : ' + (parseInt(document.getElementById('product' + productIdValues[index].value + 'Note').children[1].textContent.slice(6, -1)) + 1) + ')';
                }
                else{
                    alert('각 상품의 최대 구매 수량 : 10');
                }
            }


        });
    });
}

const orderButton = document.getElementById('order-btn');

if (orderButton) {
    orderButton.addEventListener('click', () => {

        if(document.getElementsByClassName('selectedProductIds').length === 0){
            alert('상품을 선택해 주세요');
            return;
        }

        let body = JSON.stringify({
            productIds: toList(Array.from(document.getElementsByClassName('selectedProductIds'))),
            counts: toList(Array.from(document.getElementsByClassName('orderQuantitys'))),
            totalPrice : document.getElementById('totalPrice').value
        });


        httpRequest(`/order`, 'POST', body)
            .then(response => {
                if (response.ok) {
                    // 응답이 성공했을 때
                    alert('success');
                    return response.text(); // HTML 내용을 텍스트로 반환
                } else {
                    // 응답이 실패했을 때
                    alert('fail');
                    location.replace('/order');
                    throw new Error('Failed to fetch HTML');
                }
            })
            .then(html => {
                // 받아온 HTML을 현재 페이지에 적용
                document.documentElement.innerHTML = html;
//                window.history.pushState({productIds : toList(Array.from(document.getElementsByClassName('selectedProductIds'))), counts : toList(Array.from(document.getElementsByClassName('orderQuantitys')))}, '', '/order');
            })
            .catch(error => {
                console.error(error);
            });
    });
}


const questionButton = document.getElementById('question-btn');

if(questionButton){
    questionButton.addEventListener('click', () => {
        let body = JSON.stringify({
           question : document.getElementById('question').value,
           productGroupId : document.getElementById('productGroupId').value
        });

        httpRequest(`/api/QnAQuestion`, 'POST', body)
        .then(response => {
            if (response.ok) {
                // 응답이 성공했을 때
                alert('success');
                location.replace('/productDetail/' + document.getElementById('productGroupId').value);
            } else {
                alert('fail');
            }
        });
    });
}

const prevProductGroupButton = document.getElementById('prevProductGroup-btn');

if(prevProductGroupButton){
    prevProductGroupButton.addEventListener('click', () => {

        let param = '?'
        let PGL = toList(Array.from(document.getElementsByClassName('PGL')));
        PGL.forEach(id => param += ('PGL=' + id + '&'));
        let productGroupId = document.getElementById('productGroupId').value;
        if(PGL.indexOf(productGroupId) - 1 >= 0){
            location.replace(PGL[PGL.indexOf(productGroupId) - 1] + param);
        }
        else{
             location.replace(PGL[PGL.length - 1] + param);
        }
    })
}

const nextProductGroupButton = document.getElementById('nextProductGroup-btn');



if(nextProductGroupButton){
    nextProductGroupButton.addEventListener('click', () => {

        let param = '?'
        let PGL = toList(Array.from(document.getElementsByClassName('PGL')));
        PGL.forEach(id => param += ('PGL=' + id + '&'));
        let productGroupId = document.getElementById('productGroupId').value;
        if(PGL.indexOf(productGroupId) + 1 < PGL.length){
            location.replace(PGL[PGL.indexOf(productGroupId) + 1] + param);
        }
        else{
             location.replace(PGL[0] + param);
        }
    });
}

const cartButton = document.getElementById('cart-btn');

if(cartButton){
    cartButton.addEventListener('click', () => {

        if(document.getElementsByClassName('selectedProductIds').length === 0){
            alert('상품을 선택해 주세요');
            return;
        }
        else{
            let myModal = new bootstrap.Modal(document.getElementById('cartBackdrop'));
            myModal.show();
        }

        let body = JSON.stringify({
            productIds: toList(Array.from(document.getElementsByClassName('selectedProductIds'))),
            counts: toList(Array.from(document.getElementsByClassName('orderQuantitys')))
        });

        httpRequest(`/api/cart`, 'POST', body)
        .then(response => {
            if(!response.ok){
                alert('장바구니 추가 실패');
            }
        });
    });
}

const wishButton = document.getElementById('wish-btn');

if(wishButton){
    wishButton.addEventListener('click', () => {

        let body = JSON.stringify({
            productGroupId : document.getElementById('productGroupId').value
        });
        httpRequest(`/api/wish`, 'POST', body);

        wishButton.src = (document.getElementById('wishStatus').value === 'false') ? '/img/wish2.png' : '/img/wish1.png';
        document.getElementById('wishStatus').value = (document.getElementById('wishStatus').value === 'false') ? 'true' : 'false';
    });
}

const presentButton = document.getElementById('present-btn');

if(presentButton){
    presentButton.addEventListener('click', () => {
        alert('준비중입니다.');
    });
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

function toList(elements){
    let list = [];
    elements.forEach(element => {
        if(element.value != ''){
            list.push(element.value);
        }
    });
    return list;
}