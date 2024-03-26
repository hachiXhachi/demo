
 function createAcc() {
    var username = document.getElementById('username').value;
    var name = document.getElementById('name').value;
    var password = document.getElementById('password').value;
    var form = document.getElementById('createAcc');
    if(form.checkValidity()){
        $.ajax({
            url: '/create',
            method: 'POST',
            data: {
                name: name,
                username: username,
                password: password
            },
            success: function(response) {
                var responseData = JSON.parse(response);
                console.log(responseData);
                if (responseData.result === false) {
                errorToast("Error", responseData.message);
                } else {
                    window.open('/login');
                }
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    }else{
        errorToast("Error", "Fill up the required fields");
    }

}

