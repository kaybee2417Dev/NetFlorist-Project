var netFloristModule = angular.module("NetFloristApp",[]);

netFloristModule.controller("CustomerController",['$scope','$http',function($scope,$http){
    $http.defaults.headers.post["Content-Type"] = "application/json";  
          
        //######################## Registering Customer with a role of Customer  #####################################
        $scope.create = function (num)
        {
            var  role = "customer";
            
            if(num === '1'){
                role = "Admin";
            }
            
            var user = {
                        "fname" :$scope.fname,
                        "lname" :$scope.lname,
                        "email" : $scope.email,
                        "password" : $scope.password,
                        "mobileNo" : $scope.mobileNo,
                        "role":role
                    };
                    console.log(user);
                     $http.post('http://localhost:9191/customer/register',user        
		        ).success(function(response) {
                            alert("Customer Not Registered...Try Again!Email Already in use...");
                            console.log(response);
                           })
                        .error(function(response) {
                            alert("Customer Registered...");
                           window.location.href = './login.html';
                           console.log(response);
                        });
                };       
}]);

netFloristModule.controller("LoginController",function($scope,$http){
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    //######################## Login with Username and Password #####################################
        $scope.login = function ()
        {
            var username=  $scope.username;
            var password=  $scope.password;
            var userid = 0;
                   $http.get('http://localhost:9191/login/'+ username + '/'+ password + '').then(function(response){
		               if(response.data.role === "customer")
                               {
                                  userid = response.data.cid;
                                  window.location.href = './customerHomePage.html?userID=' + userid;
                               }else if(response.data.role === "Admin")
                               {
                                  userid = response.data.cid;
                                  window.location.href = './adminHomePage.html?userID=' + userid;
                               }else{
                                   alert("Verify your crediantials...Try Again!!!");
                                 }
		         });
              
       };      
});


netFloristModule.controller("ProductController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    //######################## Get UserID And User Details #####################################
    var Data = {};
    window.location.search.replace(/\?/,'').split('&').map(function(o){ Data[o.split('=')[0]]= o.split('=')[1];});
    var userID = Data.userID;
    console.log(userID);
    
    $http.get('http://localhost:9191/findUser/' + userID + '').then(function (response) {
        $scope.users = response.data;
        console.log($scope.users);
        });
    //######################## Retrieve Products  #####################################
    $http.get('http://localhost:9191/viewproduct').then(function (response) {
        $scope.products = response.data;});
         
       //######################## Retrieve Categories  #####################################
        $http.get('http://localhost:9191/showAllCat').then(function (response) {
        $scope.categories = response.data;
        console.log(response.data);
        });
        
       $scope.viewProducts = function ()
        {
              $http.get('http://localhost:9191/viewproduct').then(function(response){
		           
                           $scope.products = response.data;
		         });
       };    
       
        //######################## Admin Remove Product with Product name   #####################################
        $scope.removeProduct = function (name)
        {

            $http.delete('http://localhost:9191/deleteproduct/' + name + '').then(function(response){
                console.log(response);
                if(response.data !== 0)
                {
                   
                    alert("Product has been Deleted");
                }else{
                   
                    alert("Product Not Deleted!..Incorrect Product Name..!!!");
                }
            });
        };
        //######################## Retrieve Product based on a ID  #####################################
        
        $scope.showData = function (pid)
        {
           console.log(pid);
           $http.get('http://localhost:9191/findProduct/' + pid + '').then(function (response) {
            $scope.prod = response.data;
           
            });
       };
       
         //######################## Edit Product with Product ID  #####################################
        $scope.editProduct = function (pid)
        {
            var name = $scope.prod.name;
            var cat = $scope.prod.category;
            var price = $scope.prod.price;
            
           
            $http.put('http://localhost:9191/update/' + pid + '/' + name + '/' + cat + '/'+price+'').then(function(response){
            
                console.log(response);
                if(response.data !== 0)
                {
                    alert("Product has been Updated");
                }else{
                    alert("Product Not Updated!..Product Name exists..!!!");
                }
            });
         
        };

});


netFloristModule.controller("AddProductController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
         
           
         //######################## Retrieve Category #####################################
        $http.get('http://localhost:9191/showAllCat').then(function (response) {
        $scope.categories = response.data;
        console.log(response.data);
        });
     
       
         //######################## Admini Save Product #####################################
          $scope.image = null;
            var imageCopy = null;
		    var image = null;
		    var handleImageSelect = function (evt)
		    {
		        var files = evt.target.files;
		        var file = files[0];

		        if (files && file) {
		           
		            var reader = new FileReader();
		            reader.onload = function (readerEvt) {
		                var binaryString = readerEvt.target.result;
		                imageCopy = btoa(binaryString);
		                image = 'data:image/octet-stream;base64,' + imageCopy;
		                $scope.image = image;
		             };

		            reader.readAsBinaryString(file);
		        }
		    };

		    if (window.File && window.FileReader && window.FileList && window.Blob) {
		        document.getElementById('productImage').addEventListener('change', handleImageSelect, false);
		    } else {
		        alert('The File APIs are not fully supported in this browser.');
		    }
      
      
        $scope.create = function ()
        {
            var product = {
                        name :$scope.name,
                        category : $scope.category,
                        price : $scope.price,
                        image : $scope.image
                  };
         
         
           $http.post('http://localhost:9191/saveproduct',product).then(function(response){
                
                console.log(response);
                console.log(product);
                if(response.data.pID !== 0)
                {
                      alert("Product Added...");
                }else{
                    console.log("Product Added");
                  
                }
            });
           
        };   

});

netFloristModule.controller("CategoryController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  

          
         //######################## Save Category #####################################
        $scope.create = function ()
        {
           var cat = {
              "name" :$scope.name
           };
          
           $http.post('http://localhost:9191/saveCat',cat ).then(function(response){
                
                console.log(response);       
                if(response.data.catcatID !== 0)
                {
                    alert("Category Added...");
                   
                }else{
                    alert("Category Not Added...");
                }
            });
            
        };  
        
        //######################## Admin remove category  #####################################
        $scope.removeCategory = function ()
        {
            var name = $scope.name;
            $http.delete('http://localhost:9191/deteleCat/' + name + '').then(function(response){
                console.log(response);
                
                if(response.data !== 0)
                {
                     alert("Category has been Deleted");
                }else{
                     alert("Category Not Deleted!..Incorrect Category Name..!!!");
                }
            });
        };
});

netFloristModule.controller("CustomerProductController", function ($scope, $http){
   $http.defaults.headers.post["Content-Type"] = "application/json";  
   //######################## Get UserID #####################################
    var Data = {};
    window.location.search.replace(/\?/,'').split('&').map(function(o){ Data[o.split('=')[0]]= o.split('=')[1];});
    var userID = Data.userID;
  
    $http.get('http://localhost:9191/findUser/' + userID + '').then(function (response) {
        $scope.users = response.data;
        console.log($scope.users);
        });
    //######################## Retrieve Categories #####################################
    $http.get('http://localhost:9191/showAllCat').then(function (response) {
        $scope.categories = response.data;
        
        });
        
        //######################## Retrieve Products  #####################################
         $http.get('http://localhost:9191/viewproduct').then(function(response){
		               
                               console.log(response);
                               $scope.products = response.data;
	 });
   
      $scope.searchCategory = function(evnt, catName){
               
          
            $http.get('http://localhost:9191/viewByCategory/' + catName + '').then(function(response){
		console.log(response);
                $scope.category = response.data;
                 var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace("active", "");
                }
                document.getElementById(catName).style.display = "block";
                evnt.currentTarget.className += " active";
             });
           };
           
           //######################## Add Product To Cart  #####################################
       $scope.cartItems = [];
       $scope.CartAmount = 0.0;
       $scope.addToCart = function(products)
       {
         var checkProducts = checkProductsInCart(products.pid);
          if(checkProducts === null)
           {
               amount = 1 * products.price;
            
                $scope.cartItems.push({ name : products.name, 
                quantity: 1, 
                product_id : products.pid, 
                price : products.price,
                category : products.category,
                image: products.image,
                totalAmount: amount
            });
            
          }else
          {
            checkProducts.quantity++;
            var totalAmount = 0.0;
            for(var x = 0; x < $scope.cartItems.length; x++)
            {
                var amount = parseFloat($scope.cartItems[x].price * $scope.cartItems[x].quantity);
                totalAmount = amount;
             }
            
            checkProducts.totalAmount = totalAmount;
          }
            
            var totalAmount = 0.0;
            for(var x = 0; x < $scope.cartItems.length; x++)
            {
                var amount = parseFloat($scope.cartItems[x].totalAmount + totalAmount);
                totalAmount = amount;
            }
            $scope.CartAmount = totalAmount;
      };
       
       //######################## Check if product exist in the database  #####################################
      function checkProductsInCart(id){
         for(var i=0; i < $scope.cartItems.length; i++){
           if($scope.cartItems[i].product_id === id){
                return $scope.cartItems[i];
            }
        }
        return null;
     }

       $scope.removeCartItem = function()
       {
            var index = $scope.cartItems.indexOf($scope.cartItems.length);
            $scope.cartItems.splice(index, 1); 
            
       };
       
       //######################## Increase Cart Item Quantity  and Cart Amount #####################################
       $scope.increaseItemCount = function (item,quantity)
       {
         
            item.quantity = quantity;
             var totalAmount = 0.0;
            for(var x = 0; x < $scope.cartItems.length; x++)
            {
                var amount = parseFloat($scope.cartItems[x].price * $scope.cartItems[x].quantity);
                totalAmount = amount;
            }
             item.totalAmount = totalAmount;
             
            var totalAmount = 0.0;
            for(var x = 0; x < $scope.cartItems.length; x++)
            {
                var amount = parseFloat($scope.cartItems[x].totalAmount + totalAmount);
                totalAmount = amount;
            }
            $scope.CartAmount = totalAmount;
            
       };
       
       //######################## Decrease Cart Item Quantity and Cart Amount  #####################################
       $scope.decreaseItemCount = function (item,quantity)
       {
            
            item.quantity = quantity;
           
            var totalAmount = 0.0;
            for(var x = 0; x < $scope.cartItems.length; x++)
            {
                var amount = parseFloat($scope.cartItems[x].price * $scope.cartItems[x].quantity);
                totalAmount = amount;
            }
          
            item.totalAmount = totalAmount;
          
            var totalAmount = 0.0;
            for(var x = 0; x < $scope.cartItems.length; x++)
            {
                var amount = parseFloat($scope.cartItems[x].totalAmount + totalAmount);
                totalAmount = amount;
            }
            $scope.CartAmount = totalAmount;
           
       };
       
           $http.get('http://localhost:9191/viewDelivaryType').then(function(response){
                              console.log(response);
                               $scope.delivariestype = response.data;
	 });
         
         $http.get('http://localhost:9191/viewPrivences').then(function(response){
                   console.log(response);
                   $scope.provinces = response.data;
	 });

        //######################## Save Delivary Information  #####################################
         /*$scope.create = function ()
         {
             
             var address = {
                 "name": $scope.name,
                 "surname": $scope.surname,
                 "email":$scope.email,
                 "delivarytype": $scope.delivaryType,
                 "contacts": $scope.contacts,
                 "street": $scope.street,
                 "city":$scope.city,
                 "province":$scope.provinceName  
            };
            
            $http.post('http://localhost:9191/saveDelivary',address).then(function(response){
                 console.log(response);
                if(response.data.getDelivaryID !== 0)
                {
                   
                    alert("Delivary Added...");
                    
                }else{
                    
                    alert("Delivary Not Added...");
                }
             });
             
         };*/
         
         //######################## Retrieve Bank Names  #####################################
         
         $http.get('http://localhost:9191/viewBankNames').then(function(response){
                              console.log(response);
                               $scope.bankNames = response.data;
	 });
         
         //######################## Make an Order(Saving an order and delivary information #####################################
         $scope.payement = function ()
         {
             var cardNo = $scope.cardNo;
             var cardHolder = $scope.cardHolder;
             var bankName = $scope.bankName;
             
             $http.get('http://localhost:9191/searchAccount/' + cardNo + '/'+ cardHolder + '/' + bankName).then(function(response){
                              console.log(response.data);
                               $scope.banking = response.data;
                               
            if($scope.banking.bankID !== undefined)
            {
                var bankAmount = $scope.banking.balance;
                var bankBalance = 0.0;
                var cardAmount = $scope.CartAmount;
                var bankCardNo = $scope.banking.cardno;
                var bankid = $scope.banking.bankID;
                
                 if(bankAmount < cardAmount)
                {
                  alert("insufficient Funds in your Bank Account!! Order can not be Processed...");
                }else{
                    
                    bankBalance = bankAmount - cardAmount;
                    $http.put('http://localhost:9191/updateAccount/' +cardNo+ '/' +bankBalance+ '').then(function(response){
                    console.log(response);
                    }); 
                  
                    var minNumber = 0; // The minimum number you want
                    var maxNumber = 500; // The maximum number you want
                    var randomnumber = Math.floor(Math.random() * (maxNumber + 1) + minNumber);
                    var orderno = randomnumber + bankCardNo + randomnumber + bankid;
                    
                    var address = {
                        "orderno": orderno,
                        "name": $scope.name,
                        "surname": $scope.surname,
                        "email":$scope.email,
                        "delivarytype": $scope.delivaryType,
                        "contacts": $scope.contacts,
                        "street": $scope.street,
                        "city":$scope.city,
                        "province":$scope.provinceName};
                    console.log($scope.date);
                 
                   
                    var orderData = {
                        "orderstatus": "New Order",
                        "orderdetails": $scope.cartItems.toString(),
                        "cID": userID,
                        "orderamount":$scope.CartAmount,
                        "orderno": orderno,
                        "delivarydate":$scope.date
                    };
                    
                   
                   $http.post('http://localhost:9191/saveOrder',orderData).then( function (response){
                        console.log(response);
                         $http.post('http://localhost:9191/saveDelivary',address).then(function(response){
                            console.log(response);
                          });
                    
                        alert("Order Processed...");
                        alert("Order Number: " + orderno);
                    });
                 }
             }else{
                 console.log("Account Not Authorized");
                 alert("Account Not Authorized..Verify your Credict Card Details...");
             }              
                               
	 });
    };
  
});

netFloristModule.controller("DelivaryController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    //######################## Retrieve Delivary Types From DelivaryTypes DB  #####################################
    $http.get('http://localhost:9191/viewDelivaryType').then(function(response){
            console.log(response);
            $scope.delivariestype = response.data;
	 });
         
         //######################## Retrieve Province From Provience DB #####################################
         $http.get('http://localhost:9191/viewPrivences').then(function(response){
                   console.log(response);
                   $scope.provinces = response.data;
	 });
    
  /*  $scope.create = function ()
         {
                var address = {
                 "name": $scope.name,
                 "surname": $scope.surname,
                 "email":$scope.email,
                 "delivarytype": $scope.delivaryType,
                 "contacts": $scope.contacts,
                 "street": $scope.street,
                 "city":$scope.city,
                 "province":$scope.provinceName  
             };
             
             
             $http.post('http://localhost:9191/saveDelivary',address).then(function(response){
                 console.log(response);
                if(response.data.getDelivaryID !== 0)
                {
                   alert("Delivary Added...");
                    
                }else{
                  
                    alert("Delivary Not Added...");
                }
             });
             
         };*/
});

netFloristModule.controller("OrderController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    
    //########################  Retrieve Orders#####################################
         
    $http.get('http://localhost:9191/viewOrder').then(function(response){
                console.log(response);
                $scope.orders = response.data;
        });
         
       
        //########################  Retrieve Order Statues #####################################
         
         $http.get('http://localhost:9191/viewOrderStatus').then(function(response){
                console.log(response);
                $scope.orderStatus = response.data;
 	 });
         
         //########################  Admin Update Status Order Using Order ID #####################################
         
         $scope.updateOrder = function(orderid, status)
         {
              $http.put('http://localhost:9191/editOrderStatus/' + orderid + '/' + status + '').then(function(response){
                console.log(response);
                if(response.data !== 0)
                {
                 alert("Order Status has been Updated");
                }else{
                    alert("Order Status Not Updated!..Check Order Status..!!!");
                }
	 });
             
         };
         
         //######################## Admin Remove Order Using Order ID #####################################
         
         $scope.removeOrder = function(orderid)
         {
             console.log(orderid);
              $http.delete('http://localhost:9191/removewOrderStatus/' + orderid + '').then(function(response){
                console.log(response);
                if(response.data !== 0)
                {
                 alert("Order has been deleted");
                }else{
                   alert("Order Not Deleted..!!!");
                }
	 });
         };
         
         //######################## TRACK Order Using Order No Customer Site #####################################
         $scope.trackOrder = function (orderNo)
         {
                $http.get('http://localhost:9191/viewbyOrderNo/' + orderNo + '').then(function(response){
                   console.log(response);
                   $scope.ordersIn = response.data;
                 });
        
                 
                $http.get('http://localhost:9191/viewdelivary/' + orderNo + '').then(function(response){
                   console.log(response);
                   $scope.delivary = response.data;
                 });
       };
       
       //######################## Completed #####################################

});
   