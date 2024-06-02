//const url = 'https://api.openai.com/v1/engines/gpt-4/completions';

//
//const chatButton = document.getElementById('chat-btn')
//
//if(chatButton){
//    chatButton.addEventListener('click', () => {
//        let userChat;
//        console.log('click');
//        if(chatButton.previousElementSibling.value !== ''){
//            userChat = chatButton.previousElementSibling.value;
//            chatButton.previousElementSibling.value = '';
//        }
//
//        let newChat = document.createElement('div');
//        newChat.setAttribute('tabindex', '-1');
//        newChat.classList.add('pb-3');
//        newChat.textContent = userChat;
//
//        document.getElementById('chat-form').previousElementSibling.appendChild(newChat);
//        newChat.focus();
//
//        let body = JSON.stringify({
//            prompt: userChat,
//            max_token: 50,
//            temperature: 0.7,
//            n: 1,
//            stop: null
//
//        });
//
//        gptApi(url, 'POST', body).then(response => {
//            if(response.ok){
//                console.log('answer : ' + response.json())
//            }
//            else{
//                console.log('error');
//            }
//        });
//    });
//}
//
//function gptApi(url, method, body){
//    return fetch(url, {
//        method: method,
//        headers: {
//            'Authorization': `Bearer ${apiKey}`,
//            'Content-Type': 'application/json'
//        },
//        body: body
//    });
//}


const url = 'https://api.openai.com/v1/engines/gpt-4/completions';


const chatButton = document.getElementById('chat-btn');

if (chatButton) {
    chatButton.addEventListener('click', async () => {
        let userChat;
        if (chatButton.previousElementSibling.value !== '') {
            userChat = chatButton.previousElementSibling.value;
            chatButton.previousElementSibling.value = '';
        }

        let newChat = document.createElement('div');
        newChat.setAttribute('tabindex', '-1');
        newChat.classList.add('pb-3');
        newChat.classList.add('text-end');
        newChat.textContent = userChat;

        document.getElementById('chat-form').previousElementSibling.appendChild(newChat);
        newChat.focus();
        document.getElementById('contentField').focus();

        let body = JSON.stringify({
            prompt: userChat,
            max_tokens: 50,
            temperature: 0.7,
            n: 1,
            stop: null
        });

        try {
            let response = await gptApi(url, 'POST', body);
            if (response.ok) {
                console.log('response ok');
                let responseData = await response.json();
                console.log('answer:', responseData.choices[0].text.trim());

                // Display the response in the chat
                let botResponse = document.createElement('div');
                botResponse.setAttribute('tabindex', '-1');
                botResponse.classList.add('pb-3');
                botResponse.textContent = responseData.choices[0].text.trim();
                document.getElementById('chat-form').previousElementSibling.appendChild(botResponse);
                botResponse.focus();
            }
            else {
                console.log('response not ok');
                let answerChat = document.createElement('div');
                answerChat.setAttribute('tabindex', '-1');
                answerChat.classList.add('pb-3');
                answerChat.textContent = '추천상품을 찾지 못했습니다.';


                document.getElementById('chat-form').previousElementSibling.appendChild(answerChat);
                answerChat.focus();
                document.getElementById('contentField').focus();
            }
        } catch (error) {
        }
    });
}

const contentField = document.getElementById('contentField');

if(contentField){
    contentField.addEventListener('keydown', event => {
        if(event.key === 'Enter'){
            event.preventDefault(); //기존엔터키 입력시 수행할것 먼저 실행?
            chatButton.click();
        }
    })
}

function chatFocus(){
    document.getElementById('contentField').focus();
}

function gptApi(url, method, body) {
    return fetch(url, {
        method: method,
        headers: {
            'Authorization': `Bearer ${apiKey}`,
            'Content-Type': 'application/json'
        },
        body: body
    });
}
