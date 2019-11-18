$(document).ready(
    function() {
        $("#basketAdd").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPost();
        });

        function ajaxPost() {
            var formData = {
                id : $("#bookId").val()
            }
            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "saveBook",
                data : JSON.stringify(formData),
                dataType : 'json'
            });
        }
    });


