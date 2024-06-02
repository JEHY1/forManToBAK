const logoutButton = document.getElementById('logout-btn');

if(logoutButton){
    logoutButton.addEventListener('click', () => {
        fetch('/logout', {
            method: 'POST',
            headers: {
                 "Content-Type": "application/json",
            }
        }).then(response => {
            if(response.ok){
                alert('logout success');
                location.replace(location.href);
            }
        })
    });
}
