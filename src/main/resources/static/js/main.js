document.addEventListener('DOMContentLoaded', function () {
    const productContainer = document.querySelector('.product-list');

    // 상품 목록
    const products = [
        { name: '상품1', price: '1원', image: 'product1.jpg' },
        { name: '상품2', price: '2원', image: 'product2.jpg' },
        { name: '상품3', price: '3원', image: 'product3.jpg' },
        { name: '상품4', price: '4원', image: 'product4.jpg' },
        { name: '상품5', price: '5원', image: 'product5.jpg' },
        { name: '상품6', price: '6원', image: 'product6.jpg' },
        { name: '상품7', price: '7원', image: 'product7.jpg' },
        { name: '상품8', price: '8원', image: 'product8.jpg' },
        { name: '상품9', price: '9원', image: 'product9.jpg' },
        { name: '상품10', price: '10원', image: 'product10.jpg' },
        { name: '상품11', price: '10원', image: 'product10.jpg' },
        { name: '상품1', price: '1원', image: 'product1.jpg' },
        { name: '상품2', price: '2원', image: 'product2.jpg' },
        { name: '상품3', price: '3원', image: 'product3.jpg' },
        { name: '상품4', price: '4원', image: 'product4.jpg' },
        { name: '상품5', price: '5원', image: 'product5.jpg' },
        { name: '상품6', price: '6원', image: 'product6.jpg' },
        { name: '상품7', price: '7원', image: 'product7.jpg' },
        { name: '상품8', price: '8원', image: 'product8.jpg' },
        { name: '상품9', price: '9원', image: 'product9.jpg' },
        { name: '상품10', price: '10원', image: 'product10.jpg' },
        { name: '상품11', price: '10원', image: 'product10.jpg' },
        { name: '상품1', price: '1원', image: 'product1.jpg' },
        { name: '상품2', price: '2원', image: 'product2.jpg' },
        { name: '상품3', price: '3원', image: 'product3.jpg' },
        { name: '상품4', price: '4원', image: 'product4.jpg' },
        { name: '상품5', price: '5원', image: 'product5.jpg' },
        { name: '상품6', price: '6원', image: 'product6.jpg' },
        { name: '상품7', price: '7원', image: 'product7.jpg' },
        { name: '상품8', price: '8원', image: 'product8.jpg' },
        { name: '상품9', price: '9원', image: 'product9.jpg' },
        { name: '상품10', price: '10원', image: 'product10.jpg' },
        { name: '상품11', price: '10원', image: 'product10.jpg' },
        { name: '상품1', price: '1원', image: 'product1.jpg' },
        { name: '상품2', price: '2원', image: 'product2.jpg' },
        { name: '상품3', price: '3원', image: 'product3.jpg' },
        { name: '상품4', price: '4원', image: 'product4.jpg' },
        { name: '상품5', price: '5원', image: 'product5.jpg' },
        { name: '상품6', price: '6원', image: 'product6.jpg' },
        { name: '상품7', price: '7원', image: 'product7.jpg' },
        { name: '상품8', price: '8원', image: 'product8.jpg' },
        { name: '상품9', price: '9원', image: 'product9.jpg' },
        { name: '상품10', price: '10원', image: 'product10.jpg' },
        { name: '상품11', price: '10원', image: 'product10.jpg' },
        { name: '상품1', price: '1원', image: 'product1.jpg' },
        { name: '상품2', price: '2원', image: 'product2.jpg' },
        { name: '상품3', price: '3원', image: 'product3.jpg' },
        { name: '상품4', price: '4원', image: 'product4.jpg' },
        { name: '상품5', price: '5원', image: 'product5.jpg' },
        { name: '상품6', price: '6원', image: 'product6.jpg' },
        { name: '상품7', price: '7원', image: 'product7.jpg' },
        { name: '상품8', price: '8원', image: 'product8.jpg' },
        { name: '상품9', price: '9원', image: 'product9.jpg' },
        { name: '상품10', price: '10원', image: 'product10.jpg' },
        { name: '상품11', price: '10원', image: 'product10.jpg' },
        { name: '상품1', price: '1원', image: 'product1.jpg' },
        { name: '상품2', price: '2원', image: 'product2.jpg' },
        { name: '상품3', price: '3원', image: 'product3.jpg' },
        { name: '상품4', price: '4원', image: 'product4.jpg' },
        { name: '상품5', price: '5원', image: 'product5.jpg' },
        { name: '상품6', price: '6원', image: 'product6.jpg' },
        { name: '상품7', price: '7원', image: 'product7.jpg' },
        { name: '상품8', price: '8원', image: 'product8.jpg' },
        { name: '상품9', price: '9원', image: 'product9.jpg' },
        { name: '상품10', price: '10원', image: 'product10.jpg' },
        { name: '상품11', price: '10원', image: 'product10.jpg' },

    ];

    products.forEach(product => {
        const productItem = document.createElement('div');
        productItem.classList.add('product');

        productItem.innerHTML = `
            <img src="images/${product.image}" alt="${product.name}">
            <h3>${product.name}</h3>
            <p>${product.price}</p>
            <button>장바구니 담기</button>
        `;

        productContainer.appendChild(productItem);
    });
});

var myCarousel = document.querySelector('#myCarousel')
var carousel = new bootstrap.Carousel(myCarousel, {
  interval: 2000,
  wrap: false
})