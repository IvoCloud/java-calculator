// console.log("script.js loaded...")
//
// document.addEventListener('DOMContentLoaded', (event) => {
//     document.getElementById("myBtn").addEventListener("click", function() {
//         console.log("Clicked!");
//     });
//
//     let xhttp = new XMLHttpRequest();
// });

$(()=>{
    console.log("loaded");

    $("#form").on("submit",function(e){
        e.preventDefault();
    });

    $("#submitBtn").click(function(e){
        let leftOperand = $(leftOperandBox).val()||0;
        let rightOperand = $(rightOperandBox).val()||0;
        let operation = $('input[name="operation"]:checked').val()||'+';

        $.get({
            url:'http://localhost:8080/',
            data:{
                "leftOperand":leftOperand,
                "rightOperand":rightOperand,
                "operation":operation
            },
            dataType: 'json',
            success: function(data) {
                console.log("Data is: "+ data.result)
                $("#result").append($("<p>",{text:"Expression is "+ data.expression}));
                $("#result").append($("<p>",{text:"Result is "+ data.result}));
            }
        });


    });
});

