document.addEventListener('DOMContentLoaded', function () {
    // y_choose_input.classList.add("green-bg");
    document.getElementById('j_idt10:y-text').value = ''
    console.log("Hi")
    let aButtons = document.getElementsByClassName('a-button')
    for (let i = 0; i < aButtons.length; i++) {
        aButtons[i].addEventListener("click", function (event) {
            event.target.classList.remove('a-button-usual-color');
            event.target.classList.add("a-button-unusual-color")
            // console.log(event.target.classList)
            // console.log(event.target.innerText)
            setXValue(event.target.innerText)
            resetButtons(event.target)
        })
    }


    try {
        let yMessage = document.getElementById('j_idt10:yMessage');
        let xMessage = document.getElementById('j_idt10:xMessage')
        let error_text = ''
        if (xMessage.innerText !== '') error_text = 'Empty x value'
        if (yMessage.innerText !== '') error_text = 'Incorrect y value'
        if (error_text !== '') {
            Toastify({
                text: error_text,
                duration: 2000,
                style: {
                    background: "red"
                },
                offset: {
                    x: 0,
                    y: 0
                }
            }).showToast();
        }
    } catch (e) {

    }


});
//
let y_choose_input = document.getElementById("y_input");

document.getElementById("check_button").addEventListener("click", function () {
    const x_arr = document.getElementsByName("x_button");
    const R_arr = document.getElementsByName("R_button");
    const y = document.getElementById("y_input");
    // const R_index = document.getElementById("r_select").options.selectedIndex;
    // const R_arr = document.getElementById("r_select").options;
    // const R = (R_arr[R_index].value)
    let x;
    let R;
    let flag = false;
    let flagR = false;

    for (let i = 0; i < x_arr.length; i++) {
        if (x_arr[i].checked) {
            x = x_arr[i].value;
            flag = true; // x не пустой
            break;
        }
    }
    for (let i = 0; i < R_arr.length; i++) {
        if (R_arr[i].checked) {
            R = R_arr[i].value;
            flagR = true; // R не пустой
            break;
        }
    }


    if (flag && flagR && !isNaN(x) && !isNaN(R) && !isNaN(y.value) && y.value && y.value > -5 && y.value < 3) {
        let data = new URLSearchParams({
            x: x,
            y: y.value,
            R: R
        })

        let options = {
            method: 'POST',
            body: data,
        }


        y_choose_input.classList.remove("red-bg");
        y_choose_input.classList.add("green-bg");

        // fetch("control", options).then(response => {
        //     if (!response.ok) {
        //         throw new Error('Ошибка HTTP ' + response.status);
        //     }
        //     return response.text();
        // })
        //     .then(data => {
        //         document.querySelector('html').innerHTML = data;
        //         updateJS()
        //
        //     })
        //     .catch(error => {
        //         console.error('Произошла ошибка:', error);
        //     });
    } else {
        let error_text = "";
        if (!flag) {
            error_text = "There is no x value";
        } else if (!y.value) {
            error_text = "There is no y value";
            y_choose_input.classList.remove("green-bg");
            y_choose_input.classList.add("red-bg");
        } else if (y.value <= -5 || y.value >= 3) {
            error_text = "Incorrect y range";
            y_choose_input.classList.remove("green-bg");
            y_choose_input.classList.add("red-bg");
        } else {
            error_text = "The value of y must be a number";
            y_choose_input.classList.remove("green-bg");
            y_choose_input.classList.add("red-bg");
        }
        Toastify({
            text: error_text,
            duration: 2000,
            style: {
                background: "red"
            },
            offset: {
                x: 0,
                y: 0
            }
        }).showToast();
    }


})


function check(input, checkboxes) {
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            checkboxes[i].checked = false;
        }
    }

    if (input.checked) {
        input.checked = false;
    } else {
        input.checked = true;
    }
}

function checkX(input) {
    let checkboxes = document.getElementsByClassName("XRadioCheck");
    check(input, checkboxes)
}


function setXValue(x) {
    document.getElementById('j_idt10:xValue').value = x
}

function checkR(input) {
    let checkboxes = document.getElementsByClassName("RRadioCheck");
    check(input, checkboxes)
}

function resetButtons(button) {
    let buttons = document.getElementsByClassName('a-button')
    for (let i = 0; i < buttons.length; i++) {
        if (buttons[i].innerText !== button.innerText) {
            buttons[i].classList.remove('a-button-unusual-color');
            buttons[i].classList.add("a-button-usual-color")
        }

    }
}