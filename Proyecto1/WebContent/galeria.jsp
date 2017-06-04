<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style type="text/css">

div.img {
    margin: 5px;
    border: 1px solid #ccc;
    float: left;
    width: 180px;
}

div.img:hover {
    border: 1px solid #777;
}

div.img img {
    width: 100%;
    height: auto;
}

div.desc {
    padding: 15px;
    text-align: center;
}


body {
    margin: 0;
    background-color: lightgreen;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 25%;
    background-color: #f1f1f1;
    position: fixed;
    height: 100%;
    overflow: auto;
}

li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}

</style>

</head>


<body>


<ul>
  <li><a class="active" href="index.jsp">Inicio</a></li>
  <li><a href="galeria.jsp">Galer�a</a></li>
  <li><a href="#page">Etiquetar imagen</a></li>
  <li><a href="cargarImagen.jsp">Cargar imagen</a></li>
</ul>

<div style="margin-left:25%;padding:1px 16px;height:1000px;">
  <h2>Galer�a de im�genes</h2>
  <p></p>
  
  
  <div class="img">
    <a target="_blank" href="testImages/img1.png">
      <img src="testImages/img1.png" alt="" width="300" height="200">
    </a>
    <div class="desc">Descripci�n</div>
  </div>


  <div class="img">
    <a target="_blank" href="testImages/img2.png">
      <img src="testImages/img2.png" alt="" width="600" height="400">
    </a>
    <div class="desc">Descripci�n</div>
  </div>

  <div class="img">
    <a target="_blank" href="testImages/img3.png">
      <img src="testImages/img3.png" alt="" width="600" height="400">
    </a>
    <div class="desc">Descripci�n</div>
  </div>

  
</div>


</body>
</html>