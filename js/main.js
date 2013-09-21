$(function  () {
 // var shoesData = [{name:"Nike", price:199.00 }, {name:"Loafers", price:59.00 }, {name:"Wing Tip", price:259.00 }];
 var shoesData = {allShoes:[{name:"Nike", price:199.00, color:"black", size:10 },
                            {name:"Loafers", price:59.00, color:"blue", size:9 },
                            {name:"Wing Tip", price:259.00, color:"brown", size:11 }
                           ]
                 };

   //Get the HTML from the template   in the script tag
    var theTemplateScript = $("#shoe-template").html(); 

   //Compile the template
    var theTemplate = Handlebars.compile (theTemplateScript); 
    Handlebars.registerPartial("description", $("#shoe-description").html());
    $(".shoesNav").append (theTemplate(shoesData)); 

//We pass the shoesData object to the compiled handleBars function
// The function will insert all the values from the objects in their respective places in the HTML and returned HTML as a string. Then we use jQuery to append the resulting HTML string into the page
});

