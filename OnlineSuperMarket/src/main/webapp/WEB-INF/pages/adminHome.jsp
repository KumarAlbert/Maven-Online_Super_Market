<!DOCTYPE html>
<html >
<head>
  <title>Home page</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</head>
<body width="50%">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#" style="color:#0059b3 ">ADMIN PANEL</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="dropdown"><a style="color:#0059b3 " class="dropdown-toggle" data-toggle="dropdown">
      <img src="images/Category.png" width="20px" height="20px"/> Category<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/redirectCategoryInsert" target="iframe">Insert Category</a></li>
          <li><a href="/viewCategories" target="iframe">View Category </a></li>
          <li><a href="/redirectCategoryUpdate" target="iframe">Update Category </a></li>
          <li><a href="/redirectCategoryDelete" target="iframe">Delete Category</a></li>
        </ul>
      </li>
      <li class="dropdown"><a style="color:#0059b3 " class="dropdown-toggle" data-toggle="dropdown">
      <img src="images/subcategory.png" width="20px" height="20px"/>Subcategory<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="redirectSubcategoryInsert.html" target="iframe">Insert Subcategory</a></li>
	       <li><a href="viewSubcategories.html" target="iframe">View Subcategory </a></li>
          <li><a href="redirectSubcategoryUpdate.html" target="iframe">Update Subcategory </a></li>
          <li><a href="redirectSubcategoryDelete.html" target="iframe">Delete Subcategory</a></li>
        </ul>
      </li>
     <li class="dropdown"><a style="color:#0059b3 " class="dropdown-toggle" data-toggle="dropdown">
     <img src="images/product.png" width="20px" height="20px"/> Product<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="redirectProductInsert.html" target="iframe">Insert Product</a></li>
          <li><a href="viewProducts.html" target="iframe">View Product</a></li>
          <li><a href="redirectProductUpdate.html" target="iframe">Update Product </a></li>
          <li><a href="redirectProductDelete.html" target="iframe">Delete Product</a></li>
        </ul>
      </li>
    </ul>
  </div>
</nav>
<div class="container-fluid">
  <div class="row">
		    <div class="col-sm-12">
		   <div  class="embed-responsive embed-responsive-16by9">
          			<iframe height="400px" width="100%" src="viewCategories.html" 
           name="iframe"></iframe>
         </div>
         </div> 
        </div>
      </div>
</body>
</html>

