const consoleLogButton = document.getElementById('consoleLog-btn');

if(consoleLogButton){
    consoleLogButton.addEventListener('click', () => {
        console.log('click');
    });
}