$(function  () {
var shoesData = [{name:"Nike", price:199.00 }, {name:"Loafers", price:59.00 }, {name:"Wing Tip", price:259.00 }];

function updateAllShoes(shoes)  {
var theHTMLListOfShoes = "";

shoesData.forEach (function (eachShoe)  {
// Note the coupling and mixing of HTML and JavaScript; it is tedious to follow
 theHTMLListOfShoes += '<li class="shoes">' + '<a href="/' + eachShoe.name.toLowerCase() + '">' + eachShoe.name + ' -- Price: ' + eachShoe.price + '</a></li>';
    });
    return theHTMLListOfShoes;
}
$(".shoesNav").append (updateAllShoes(shoesData));

});

