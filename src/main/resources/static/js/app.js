var netFloristModule = angular.module("NetFloristApp",['ngRoute']);
netFloristModule.config(["$routeProvider","$locationProvider",function($routeProvider) {
  $routeProvider
    .when('/login', {
       templateUrl :'/login.html',
       controller : 'LoginController'
   }).when('/register',{
       templateUrl :'/register.html',
       controller : 'RegisterController'
   }).when('/forgotPassword',{
       templateUrl :'/forgotPassword.html',
       controller : 'ForgotPasswordController'
   }).when('/newPassword',{
       templateUrl :'/newPassword.html',
       controller : 'ForgotPasswordController'
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
       controller : 'CustomerController'
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
      
        $scope.registerUsers = function (num)
        {
            var  role = "customer";
           
            if(num === '1'){
                role = "Admin";
            }
           
            var user = {
                        "name" :$scope.fname,
                        "surname" :$scope.lname,
                        "email" : $scope.email,
                        "password" : $scope.password,
                        "mobile" : $scope.mobileNo,
                        "role":role
                    };
                    
            if(user.name !== undefined)
            {
                if(user.surname !== undefined)
                {
                  if(user.mobile !== undefined)
                        {
                            if(user.password !== undefined)
                            {
                               // console.log(user);
                                $http.post('/user/register',user).then(function(response) {
                                        console.log(response.data);
                                        if(response.data.userID !== 0)
                                        {
                                           alert("User Registered...");
                                           window.location = './login.html';
                                        }
                                }).catch(function (error){
                                    alert(error.data.error + ": User Email Already Exists..");
                                });
                           }else
                            {
                                alert("Enter User Password...");
                            }
                        }else
                        {
                            alert("Enter User Mobile...");
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
                    var userId = 0;
                   $http.get('/user/login/'+ username + '/'+ password + '').then(function(response){
                               if(response.data.role === "customer")
                               {
                                  userId = response.data.userID;
                                  window.location = './customerHomePage.html?userId=' + userId;
                               }else if(response.data.role === "Admin")
                               {
                                 userId = response.data.userID;
                                 window.location = './adminHomePage.html?userId=' + userId;
                               }
		        }).catch(function(error){
                            alert(error.data.message);
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
netFloristModule.controller("ForgotPasswordController",function($scope,$http){
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    //######################## Forgot  Password #####################################
    
        
        $scope.forgotPassword = function ()
        {
           var email =  $scope.username;
            if(username !== undefined)
            {
               
                $http.get('/user/forgotPassword/' + email + '').then( function (response){
                    $scope.user = response.data;
                    window.location = './newPassword.html?email=' + email;
                }).catch(function(error){
                    alert(error.data.message);
                });
           
            }else
            {
               alert("Enter Username...");
            }
            
       };   
       
       $scope.newPassword = function (){
           var password = $scope.password;
           var newPassword = $scope.password1;
            var Data = {};
            window.location.search.replace(/\?/,'').split('&').map(function(o){ Data[o.split('=')[0]]= o.split('=')[1];});
            var email = Data.email;
   
           if(password === newPassword)
           {
               $http.put('/user/newPassword/' + password + '/' +email+'').then(function(response){
                    alert("New User Password has been created...");
                    window.location = './login.html';
                    
               }).catch(function (error){
                   alert(error.data.message);
               });
            }else
           {
               alert("Passwords Dont Match....");
           }
        };
});

netFloristModule.controller("ProductController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    //######################## Get UserID And User Details #####################################
    var Data = {};
    window.location.search.replace(/\?/,'').split('&').map(function(o){ Data[o.split('=')[0]]= o.split('=')[1];});
    var userId = Data.userId;
   
    $http.get('/user/findUserByUserId/' + userId + '').then(function (response) {
        $scope.user = response.data;
      });
    //######################## Find All Products  #####################################
    $http.get('/product/findAllProducts').then(function (response) {
       $scope.products = response.data;
    });
        
        
    //######################## Find All Categories  #####################################
        $http.get('/category/findAllCategories').then(function (response) {
        $scope.categories = response.data;
       });
        
    //######################## Admin Delete Product with Product productID   #####################################
        $scope.deleteProduct = function (productId)
        {
           
            $http.delete('/product/deleteProduct/' + productId + '').then(function(response){
               if(response.data !== 0)
                {
                   alert("Product has been Deleted");
                }
            }).catch(function (error){
                alert(error.data.message);
            }); 
        };
        //######################## Find Product based on a Product ID  #####################################
        
        $scope.findProductById = function (productId)
        {
           $http.get('/product/findProductById/' + productId + '').then(function (response) {
            $scope.product = response.data;
           }).catch(function (error){
               alert(error.data.message);
           });
       };
       
         //######################## Update Product based on Product Id Product ID  #####################################
        $scope.editProduct = function (productId)
        {
            var name = $scope.product.name;
            var cat = $scope.product.category;
            var price = $scope.product.price;
                        
            if(name !== undefined)
            {
                if( price !== undefined){
                    
                    $http.put('/product/updateProduct/' + productId + '/' + name + '/' + cat + '/'+price+'').then(function(response){
            
                        if(response.data !== 0)
                        {
                            alert("Product has been Updated");
                        }
                    }).catch(function (error){
                        alert(error.data.message);
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
        $http.get('/category/findAllCategories').then(function (response) {
            $scope.categories = response.data;
         });
     
       
         //######################## Admin Save Product #####################################
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
                       $http.post('/product/saveProduct',product).then(function(response){

                            if(response.data.pID !== 0)
                            {
                                alert("Product Added...");
                            }
                            }).catch(function(error){
                               alert(error.data.message);
                            });

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
               $http.post('/category/saveCategory',cat ).then(function(response){
                     
                if(response.data.catcatID !== 0)
                {
                    alert("Category Added...");
                }
            }).catch(function (error){
                alert(error.data.message);
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
                 $http.delete('/category/deleteCategory/' + name + '').then(function(response){
                
                    if(response.data !== 0)
                    {
                         alert("Category has been Deleted");
                    }
                }).catch(function (error){
                    alert(error.data.message);
                });
            }else
            {
               alert("Enter Category Name to be deleted...");
            }
         };
});

netFloristModule.controller("CustomerController", function ($scope, $http){
   $http.defaults.headers.post["Content-Type"] = "application/json";  
   //######################## Get UserID #####################################
    var Data = {};
    window.location.search.replace(/\?/,'').split('&').map(function(o){ Data[o.split('=')[0]]= o.split('=')[1];});
    var userId = Data.userId;
    
    $http.get('/user/findUserByUserId/' + userId + '').then(function (response) {
        $scope.users = response.data;
        
        });
        
   //######################## Find All Categories #####################################
    $http.get('/category/findAllCategories').then(function (response) {
        $scope.categories = response.data;
        });
        
        //######################## Retrieve Products  #####################################
         $http.get('/product/findAllProducts').then(function(response){
            $scope.products = response.data;
	 });
   
      $scope.searchCategory = function(evnt, catName){
             $http.get('/category/findAllCategories/' + catName + '').then(function(response){
		
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
          var checkProducts = checkProductsInCart(products.productID);
          if(checkProducts === null)
           {
               amount = 1 * products.price;
            
                $scope.cartItems.push({ name : products.name, 
                quantity: 1, 
                productId : products.productID, 
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
           if($scope.cartItems[i].productId === id){
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
       
        //######################## Find All Address Types  #####################################
         $http.get('/addressTypes/findAllAddressTypes').then(function(response){
                $scope.addressTypes = response.data;
	 });
         
        //######################## Find All Provinces  #####################################
         $http.get('/province/findAllProvinces').then(function(response){
              $scope.provinces = response.data;
	 });

         //######################## Find Bank Names  #####################################
         
         $http.get('/bankNames/findAllBankNames').then(function(response){
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
                        $http.get('/bank/findBankAccount/' + cardNo + '/'+ cardHolder + '/' + bankName).then(function(response){
                            
                            $scope.banking = response.data;
                            console.log( $scope.banking);
                            if($scope.banking.bankID !== undefined)
                            {
                                var bankAmount = $scope.banking.balance;
                                var bankBalance = 0.0;
                                var cardAmount = $scope.CartAmount;
                                var bankCardNo = $scope.banking.cardno;
                                var bankId = $scope.banking.bankID;

                                if(bankAmount < cardAmount)
                                {
                                  alert("insufficient Funds in your Bank Account!! Order can not be Processed...");
                                }else{

                                    bankBalance = bankAmount - cardAmount;
                                    $http.put('/bank/updateBankBalance/' +cardNo+ '/' +bankBalance+ '').then(function(response){
                                    
                                    }).catch(function (error){
                                        alert(error.data.message);
                                    });; 

                                    var minNumber = 0; // The minimum number you want
                                    var maxNumber = 500; // The maximum number you want
                                    var randomnumber = Math.floor(Math.random() * (maxNumber + 1) + minNumber);
                                    var orderno = randomnumber + bankCardNo + randomnumber +  bankId;

                                    var address = {
                                        "orderno": orderno,
                                        "name": $scope.name,
                                        "surname": $scope.surname,
                                        "email":$scope.email,
                                        "addresstype": $scope.addressType,
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
                                                if(address.addresstype !== undefined)
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
                                                                    var productId = $scope.cartItems[x].productId;
                                                                    var price =  $scope.cartItems[x].price;
                                                                    var category = $scope.cartItems[x].category;
                                                                    var image= $scope.cartItems[x].image;
                                                                  
                                                                      var orderData = {
                                                                      "orderstatus": "New Order",
                                                                      "orderamount": $scope.CartAmount ,
                                                                      "userID": userId,
                                                                      "orderno": orderno,
                                                                      "delivarydate":$scope.date,
                                                                      "name":name,
                                                                      "quantity":quantity,
                                                                      "productID":productId,
                                                                      "price":price,
                                                                      "category":category,
                                                                      "image":image
                                                                  };

                                                                 
                                                                  $http.post('/orders/saveOrders',orderData).then( function (response){
                                                                      
                                                                  }).catch(function (error){
                                                                      alert(error.data.message);
                                                                  });
                                                                }
                                                                $http.post('/address/saveAddress',address).then(function(response){

                                                                 }).catch(function (error){
                                                                      alert(error.data.message);
                                                                  });
                                                                     alert("Order Processed...");
                                                                     alert("Order Number:..." + orderno);
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
                             }            

                        }).catch(function (error){
                            alert(error.data.message);
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

netFloristModule.controller("OrderController",function($scope,$http){
   
    $http.defaults.headers.post["Content-Type"] = "application/json";  
    
    //########################Find All Orders#####################################
     $http.get('/orders/findAllOrders').then(function(response){
               $scope.orders = response.data;

        });
        
     //########################Find All Order Statues #####################################
         
         $http.get('/orderStatus/findAllOrderStatus').then(function(response){
                $scope.orderStatus = response.data;
               
 	 });
         
     
         //########################Admin Update Status Order Using Order ID #####################################
         
         $scope.updateOrderStatus = function(orderId, orderStatus)
         {
             
             $http.put('/orders/updateOrderStatus/' + orderId + '/' + orderStatus + '').then(function(response){
                
                if(response.data !== 0)
                {
                 alert("Order Status has been Updated");
                }
	 }).catch(function (error){
             alert(error.data.message);
         });
             
         };
         
        //######################## Admin Remove Order Using Order ID #####################################
         
         $scope.removeOrder = function(orderNo)
         {
            console.log(orderNo);   
              $http.delete('/orders/deleteOrders/' + orderNo + '').then(function(response){
                
                if(response.data !== 0)
                {
                 alert("Order has been deleted");
                }
            }).catch(function (error){
                alert(error.data.message);
            });;
         };
         
         //######################## TRACK Order Using Order No Customer Site #####################################
         $scope.trackOrder = function (orderNo)
         {
            if(orderNo !== undefined){ 
                $http.get('/orders/findByOrderNo/' + orderNo + '').then(function(response){
                  
                   $scope.ordersIn = response.data;
                }).catch(function (error){
                    alert(error.data.message);
                });
        
                $http.get('/address/findAddressByOrderNo/' + orderNo + '').then(function(response){
                  
                   $scope.delivary = response.data;
                 }).catch(function (error){
                    alert(error.data.message);
                });
            }else
            {
                alert("Enter Order Number...!!!");
            }
       };
});
   