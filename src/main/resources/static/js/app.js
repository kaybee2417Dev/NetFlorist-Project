var netFloristModule = angular.module("NetFloristApp",['ngRoute']);
netFloristModule.config(["$routeProvider","$locationProvider",function($routeProvider) {

    
    $routeProvider
    .when('/login', {
       templateUrl :'/login.html',
       controller : 'LoginController'
   }).when('/register',{
       templateUrl :'/register.html',
       controller : 'RegisterController'
   }).when('/adminHomePage',{
       templateUrl :'/adminHomePage.html',
       controller : 'ProductController'
   }).when('/addProduct',{
       templateUrl :'/addProduct.html',
       controller : 'AddProductController'
   }).when('/updateProduct',{
       templateUrl :'/updateProduct.html',
       controller : 'ProductController'
   }).when('/addCategory',{
       templateUrl :'/addCategory.html',
       controller : 'CategoryController'
   }).when('/registerAdmin',{
       templateUrl :'/registerAdmin.html',
       controller : 'RegisterController'
   }).when('/viewOrders',{
       templateUrl :'/viewOrders.html',
       controller : 'OrderController'
   }).when('/customerHomePage',{
       templateUrl :'/customerHomePage.html',
       controller : 'CustomerProductController'
   }).when('/customerOrders',{
       templateUrl :'/customerOrders.html',
       controller : 'OrderController'
   }).otherwise({
       redirectTo :'/'
    });
}]);


netFloristModule.controller("RegisterController",['$scope','$http',function($scope,$http){
    $http.defaults.headers.post["Content-Type"] = "application/json";  
          
        //######################## Registering Customer with a role of Customer  #####################################
      
        $scope.registerCustomer = function (num)
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
                    
            if(user.fname !== undefined)
            {
                if(user.lname !== undefined)
                {
                    if(user.email !== undefined)
                    {
                        if(user.mobileNo !== undefined)
                        {
                            if(user.password !== undefined)
                            {
                               // console.log(user);
                                $http.post('/register',user).then(function(response) {
                                        console.log(response.data);
                                        if(response.data.cID !== 0)
                                        {
                                            //console.log("Registerd");
                                            alert("User Registered...");
                                        }else{
                                            console.log("Not Registered");
                                        }
                                }).catch(function (error){
                                    alert("User Not Registered...Email Already Exists...");
                                    console.log(error.value);
                               });
                           }else
                            {
                                alert("Enter User Password...");
                            }
                        }else
                        {
                            alert("Enter User Email...");
                        }
                    }else
                    {
                        alert("Enter User Email...");
                    }
                }else
                {
                    alert("Enter User Last Name...");
                }
            }else
            {
                alert("Enter User First Name...");
            }
                    
       };      
}]);

netFloristModule.controller("LoginController",function($scope,$http){
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    //######################## Login with Username and Password #####################################
    
    
        $scope.login = function ()
        {
            var username=  $scope.username;
            var password=  $scope.password;
            
            if(username !== undefined)
            {
                if(password !== undefined)
                {
                    var userid = 0;
                   $http.get('/login/'+ username + '/'+ password + '').then(function(response){
                        console.log(response.data.role);
		               if(response.data.role === "customer")
                               {
                                  userid = response.data.cid;
                                  window.location = './customerHomePage.html?userID=' + userid;
                               }else if(response.data.role === "Admin")
                               {
                                 userid = response.data.cid;
                                 window.location = './adminHomePage.html?userID=' + userid;
                               }
		        }).catch(function(error){
                               alert("Verify your crediantials...Try Again!!!");
                        });
                }else
                {
                   alert("Enter Password...");
                }
            }else
            {
               alert("Enter Username...");
            }
            
       };      
});


netFloristModule.controller("ProductController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    //######################## Get UserID And User Details #####################################
    var Data = {};
    window.location.search.replace(/\?/,'').split('&').map(function(o){ Data[o.split('=')[0]]= o.split('=')[1];});
    var userID = Data.userID;
    
    $http.get('/findUser/' + userID + '').then(function (response) {
        $scope.users = response.data;
       // console.log(response.data);
        });
    //######################## Retrieve Products  #####################################
    $http.get('/viewproduct').then(function (response) {
      //console.log(response.data);
        $scope.products = response.data;});
        console.log($scope.products);
       //######################## Retrieve Categories  #####################################
        $http.get('/showAllCat').then(function (response) {
        $scope.categories = response.data;
       // console.log(response.data);
        });
        
       $scope.viewProducts = function ()
        {
              $http.get('/viewproduct').then(function(response){
		           
                           $scope.products = response.data;
		         });
       };    
       
        //######################## Admin Remove Product with Product name   #####################################
        $scope.removeProduct = function (name)
        {

            $http.delete('/deleteproduct/' + name + '').then(function(response){
              //  console.log(response);
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
           $http.get('/findProduct/' + pid + '').then(function (response) {
            $scope.prod = response.data;
           });
       };
       
         //######################## Edit Product with Product ID  #####################################
        $scope.editProduct = function (pid)
        {
            var name = $scope.prod.name;
            var cat = $scope.prod.category;
            var price = $scope.prod.price;
            
            if(name !== undefined)
            {
                if( price !== undefined){
                    
                    $http.put('/update/' + pid + '/' + name + '/' + cat + '/'+price+'').then(function(response){
            
                        if(response.data !== 0)
                        {
                            alert("Product has been Updated");
                        }else
                        {
                            alert("Product Not Updated!..Product Name exists..!!!");
                        }
                    }); 
                }else
                {
                    alert("Enter Product Price...");
                }
            }else {
                alert("Enter Product Name...");
            }
        };

});


netFloristModule.controller("AddProductController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
         
           
         //######################## Retrieve Category #####################################
        $http.get('/showAllCat').then(function (response) {
        $scope.categories = response.data;
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
         
         if(product.name !== undefined)
         {
             if(product.price !== undefined)
            {
               if(product.category !== undefined)
                        {
                              $http.post('/saveproduct',product).then(function(response){

                                if(response.data.pID !== 0)
                                {
                                      alert("Product Added...");
                                }else{
                                    alert("Product Not Added");
                                }
                            }).catch(function(error){
                               alert("Product Not Added...Product Name!!!");
                        });;

                        }else{
                            alert("Select Product Category...");
                        }
                }else{
                alert("Enter Product Price...");
            }

         }else{
             alert("Enter Product Name...");
         }

           
        };   

});

netFloristModule.controller("CategoryController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  

          
         //######################## Save Category #####################################
        $scope.saveCategory = function ()
        {
           var cat = {
              "name" :$scope.name
           };
          
          if(cat.name !== undefined){
               $http.post('/saveCat',cat ).then(function(response){
                     
                if(response.data.catcatID !== 0)
                {
                    alert("Category Added...");
                   
                }else{
                    alert("Category Not Added...");
                }
            });
          }else
          {
              alert("Enter Category Name...");
          }
      
        };  
        
        //######################## Admin remove category  #####################################
        $scope.removeCategory = function ()
        {
            var name = $scope.name;
            if( name !== undefined){
                 $http.delete('/deteleCat/' + name + '').then(function(response){
                
                    if(response.data !== 0)
                    {
                         alert("Category has been Deleted");
                    }else{
                         alert("Category Not Deleted!..Incorrect Category Name..!!!");
                    }
                });
            }else
            {
               alert("Enter Category Name to be deleted...");
            }
           
        };
});

netFloristModule.controller("CustomerProductController", function ($scope, $http){
   $http.defaults.headers.post["Content-Type"] = "application/json";  
   //######################## Get UserID #####################################
    var Data = {};
    window.location.search.replace(/\?/,'').split('&').map(function(o){ Data[o.split('=')[0]]= o.split('=')[1];});
    var userID = Data.userID;
  
    $http.get('/findUser/' + userID + '').then(function (response) {
        $scope.users = response.data;
        
        });
        
    //######################## Retrieve Categories #####################################
    $http.get('/showAllCat').then(function (response) {
        $scope.categories = response.data;
        
        });
        
        //######################## Retrieve Products  #####################################
         $http.get('/viewproduct').then(function(response){
		               
                               $scope.products = response.data;
	 });
   
      $scope.searchCategory = function(evnt, catName){
               
          
            $http.get('/viewByCategory/' + catName + '').then(function(response){
		
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
       
           $http.get('/viewDelivaryType').then(function(response){
                $scope.delivariestype = response.data;
	 });
         
         $http.get('/viewPrivences').then(function(response){
                
                 $scope.provinces = response.data;
	 });

         //######################## Retrieve Bank Names  #####################################
         
         $http.get('/viewBankNames').then(function(response){
                $scope.bankNames = response.data;
	 });
         
         //######################## Make an Order(Saving an order and delivary information #####################################
         $scope.payement = function ()
         {
             var cardNo = $scope.cardNo;
             var cardHolder = $scope.cardHolder;
             var bankName = $scope.bankName;
             
             if(cardNo !== undefined)
             {
                if(cardHolder !== undefined)
                {
                    if(bankName !== undefined)
                    {
                        $http.get('/searchAccount/' + cardNo + '/'+ cardHolder + '/' + bankName).then(function(response){
                            
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
                                    $http.put('/updateAccount/' +cardNo+ '/' +bankBalance+ '').then(function(response){
                                    
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
                                   
                                     
                                     if(address.name !== undefined)
                                     {
                                        if(address.surname !== undefined)
                                        {
                                            if(address.email !== undefined)
                                            {
                                                if(address.delivarytype !== undefined)
                                                {
                                                    if(address.contacts !== undefined)
                                                    {
                                                        if(address.street !== undefined)
                                                        {
                                                            if(address.city !== undefined)
                                                            {
                                                                if(address.province !== undefined)
                                                                {
                                                                    for(var x = 0; x < $scope.cartItems.length; x++){

                                                                    var name = $scope.cartItems[x].name;
                                                                    var quantity = $scope.cartItems[x].quantity;
                                                                    var product_id = $scope.cartItems[x].product_id;
                                                                    var price =  $scope.cartItems[x].price;
                                                                    var category = $scope.cartItems[x].category;
                                                                    var image= $scope.cartItems[x].image;
                                                                   
                                                                    var orderData = {
                                                                      "orderstatus": "New Order",
                                                                      "orderamount": $scope.CartAmount ,
                                                                      "cID": userID,
                                                                      "orderno": orderno,
                                                                      "delivarydate":$scope.date,
                                                                      "name":name,
                                                                      "quantity":quantity,
                                                                      "pid":product_id,
                                                                      "price":price,
                                                                      "category":category,
                                                                      "image":image
                                                                  };

                                                                 
                                                                  $http.post('/saveOrder',orderData).then( function (response){
                                                                  });
                                                                }
                                                                $http.post('/saveDelivary',address).then(function(response){

                                                                 });
                                                                      alert("Order Processed...");
                                                                     alert("Order Number: " + orderno);
                                                                }else{
                                                                    alert("Select Recipient Province...");
                                                                }

                                                            }else{
                                                                alert("Enter Recipient City...");
                                                            }

                                                        }else{
                                                            alert("Enter Recipient Street Name...");
                                                        }

                                                    }else{
                                                        alert("Enter Recipient Contacts Numbers...");
                                                    }

                                                }else{
                                                    alert("Select Recipient Type...");
                                                }

                                            }else{
                                                alert("Enter Recipient Email...");
                                            }

                                        }else{
                                            alert("Enter Recipient Surname...");
                                        }

                                     }else{
                                         alert("Enter Recipient Name...");
                                     }
                                }
                             }else{
                                 console.log("Account Not Authorized");
                                 alert("Account Not Authorized..Verify your Credict Card Details...");
                             }              

                        });
                    }else
                    {
                        alert("Select Bank Name!!!");
                    }
                }else
                {
                    alert("Enter Your Card Holder Namde!!!");
                }
             }else
             {
                 alert("Enter Your Card Number!!!");
             }
     };
  
});

netFloristModule.controller("DelivaryController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    //######################## Retrieve Delivary Types From DelivaryTypes DB  #####################################
    $http.get('/viewDelivaryType').then(function(response){
           
            $scope.delivariestype = response.data;
	 });
         
         //######################## Retrieve Province From Provience DB #####################################
         $http.get('/viewPrivences').then(function(response){
                   
                   $scope.provinces = response.data;
	 });

});

netFloristModule.controller("OrderController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    
    //########################  Retrieve Orders#####################################
         
    $http.get('/viewOrder').then(function(response){
                
                $scope.orders = response.data;
        });
         
       
        //########################  Retrieve Order Statues #####################################
         
         $http.get('/viewOrderStatus').then(function(response){
                
                $scope.orderStatus = response.data;
 	 });
         
         //########################  Admin Update Status Order Using Order ID #####################################
         
         $scope.updateOrder = function(orderid, status)
         {
              $http.put('/editOrderStatus/' + orderid + '/' + status + '').then(function(response){
                
                if(response.data !== 0)
                {
                 alert("Order Status has been Updated");
                }else{
                    alert("Order Status Not Updated!..Check Order Status..!!!");
                }
	 });
             
         };
         
         //######################## Admin Remove Order Using Order ID #####################################
         
         $scope.removeOrder = function(orderNo)
         {
            console.log(orderNo);   
              $http.delete('/removewOrderStatus/' + orderNo + '').then(function(response){
                
                if(response.data !== 0)
                {
                 alert("Order has been deleted");
                }else{
                  alert("Order Dont Exists...!!!");
                }
	 });
         };
         
         //######################## TRACK Order Using Order No Customer Site #####################################
         $scope.trackOrder = function (orderNo)
         {
            if(orderNo !== undefined){ 
                $http.get('/viewbyOrderNo/' + orderNo + '').then(function(response){
                  
                   $scope.ordersIn = response.data;
                 
                 });
        
                 
                $http.get('/viewdelivary/' + orderNo + '').then(function(response){
                  
                   $scope.delivary = response.data;
                 });
            }else
            {
                alert("Enter Order Number...!!!");
            }
       };
});
   