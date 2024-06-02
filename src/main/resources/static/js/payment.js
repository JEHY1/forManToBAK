function payment(){

    if(document.getElementById('sample6_postcode').value === '' || document.getElementById('sample6_detailAddress').value === ''){
        document.getElementById('invalid-postcode').classList.remove('d-hidden');
    }
    if(document.getElementById('receiver').value === ''){
        document.getElementById('invalid-receiver').classList.remove('d-hidden');
    }
    if(document.getElementById('receiverPhone').value === ''){
        document.getElementById('invalid-receiverPhone').classList.remove('d-hidden');
    }
    if(document.getElementById('paymentType').value === ''){
        document.getElementById('invalid-paymentType').classList.remove('d-hidden');
    }


    if(document.getElementById('sample6_postcode').value !== '' && document.getElementById('receiver').value !== ''
    && document.getElementById('receiverPhone').value !== '' && document.getElementById('paymentType').value !== ''
    && document.getElementById('sample6_detailAddress').value !== ''){
        let body = JSON.stringify({
                paymentPrice : parseInt(document.getElementById('paymentPrice').textContent),
                paymentType : document.getElementById('paymentType').value,
                postalCode: document.getElementById('sample6_postcode').value,
                roadNameAddress: document.getElementById('sample6_address').value,
                detailAddress: document.getElementById('sample6_detailAddress').value,
                address : document.getElementById('sample6_address').value + ' ' + document.getElementById('sample6_detailAddress').value,
                deliveryFee : parseInt(document.getElementById('deliveryFee').textContent),
                productIds : toList(Array.from(document.getElementsByClassName('productIds'))),
                productCounts : toList(Array.from(document.getElementsByClassName('productCounts'))),
                receiver : document.getElementById('receiver').value,
                receiverPhone : document.getElementById('receiverPhone').value,
                cartIds : toList(Array.from(document.getElementsByClassName('cartIds')))
        });

        httpRequest(`/api/payment`, 'POST', body).then(response => {
            if(response.ok){

                return response.json();
            }
            else{
                alert('주문 오류');

                return new Error('error');
            }
        }).then(data => {
            if(data.scarcityProductIds !== null){
                let totalCount = 0; //재고량
                data.scarcityProductCounts.forEach(count => totalCount += count);
                console.log('total count : ' + totalCount);

                if(totalCount === 0){
                    alert('현재 선택하신 상품 재고가 없습니다. (메인으로 이동합니다.)');
                    location.replace('/main');
                }
                let myModal = new bootstrap.Modal(document.getElementById('scarcityNote'));
                let scarcityContent =  document.createElement('div');
                let text = '';

                data.scarcityProductIds.forEach((scarcityProductId, index) => {
                    text += '상품명 : ' + data.scarcityProductNames[index] + '(재고량 : ' + data.scarcityProductCounts[index] + ')\n';
                    document.getElementById('productId' + data.scarcityProductIds[index] + 'changedCount').value = data.scarcityProductCounts[index];
                });
                scarcityContent.textContent = text;
                document.getElementById('scarcity-content').appendChild(scarcityContent);
                myModal.show();
            }
            else{
                alert('주문성공');
                location.replace('/main');
            }
        });
    }
}

function addressCheck(address){
    if(document.getElementById('sample6_postcode').value !== '' && document.getElementById('sample6_detailAddress').value !== ''){
        document.getElementById('invalid-postcode').classList.add('d-hidden');
    }
}

function receiverCheck(receiver){
    if(document.getElementById('receiver').value !== ''){
        document.getElementById('invalid-receiver').classList.add('d-hidden');
    }
}

function receiverPhoneCheck(receiverPhone){
    if(document.getElementById('receiverPhone').value !== ''){
        document.getElementById('invalid-receiverPhone').classList.add('d-hidden');
    }
}

function paymentTypeCheck(paymentType){
    if(document.getElementById('paymentType').value !== ''){
        document.getElementById('invalid-paymentType').classList.add('d-hidden');
    }
}

function checkBox(thisCheckBox){
    const checkboxes = Array.from(document.getElementsByClassName('payType'));
    document.getElementById('paymentType').value = thisCheckBox.parentElement.textContent;
    checkboxes.forEach(checkbox => {
        if (checkbox !== thisCheckBox) {
            checkbox.checked = false;
        }
    });
    if(!thisCheckBox.checked){
        document.getElementById('paymentType').value = '';
    }
    document.getElementById('invalid-paymentType').classList.add('d-hidden');
}

function quantityChange(){

    let totalCount = 0;
    Array.from(document.getElementsByClassName('changedCounts')).forEach(count => {
        totalCount += parseInt(count.value);
        console.log(totalCount);
    });

    if(totalCount === 0){
        alert('상품이 존재하지 않음');
    }

    let body = JSON.stringify({

        productIds: productIdsReset(Array.from(document.getElementsByClassName('productIds')), Array.from(document.getElementsByClassName('changedCounts'))),
        counts: countsReset(Array.from(document.getElementsByClassName('changedCounts'))),
        totalPrice: parseInt(document.getElementById('totalPrice').textContent),
        cartIds: cartIdsReset(Array.from(document.getElementsByClassName('cartIds')), Array.from(document.getElementsByClassName('changedCounts')))
    });

    httpRequest(`/order`, 'POST', body)
        .then(response => {
            if (response.ok) {
                // 응답이 성공했을 때
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

function countsReset(changedCounts){ //재고 확인하고 재고 맞춰서 수량 선택
    let list = [];
    changedCounts.forEach(changedCount => {
        if(parseInt(changedCount.value) !== 0){
            list.push(changedCount.value);
        }
    });

    return list;
}

function productIdsReset(productIds, changedCounts){
    let list = [];
    changedCounts.forEach((changedCount, index) => {
        if(parseInt(changedCount.value) !== 0){
            list.push(productIds[index].value);
        }
    });

    return list;
}

function cartIdsReset(cartIds, changedCounts){
    let list = [];
    if(cartIds.length !== 0){
        changedCounts.forEach((changedCount, index) => {
            if(parseInt(changedCount.value) !== 0){
                list.push(cartIds[index].value);
            }
        });
    }

    return list;
}


