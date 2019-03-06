<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>

		<meta charset="UTF-8">
		<link href="css/mappa.css" rel="stylesheet" type="text/css">
		<title>Mappa</title>
		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBEjla2nPAcJpZBb1aA3AkzFd__o7Nha38&callback=func" async defer></script>
		<script src="homePage.js"></script>
			<style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map-container {
        height: 100%;
      }
      #floating-panel {
        position: absolute;
        top: 10px;
        left: 25%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
      }
      #floating-panel {
        position: absolute;
        top: 5px;
        left: 50%;
        margin-left: -180px;
        width: 350px;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
      }
      #latlng {
        width: 225px;
      }
    </style>

	</head>
	
		<body>
		
			<div id="container">
		
				<div id="header">
					
						<h1><a href="homePage.html">LetsMeet</a></h1> 
							
						<a href="profilo.html"><img id="profilo" alt="profilo" src="profilo.png"></a> 
							
						<a href="impostazioni.html"><img id="settings" alt="settings" src="two-cogwheels-configuration-interface-symbol.png"></a>
							
				
				</div>
				
			<div id="map-container">
			
						<!-- <img id="zoom_meno" alt="zoom" src="images/zoom-.svg">
						
						<img id="zoom_piu" alt="zoom" src="images/zoom+.png">
			 -->
			</div>
			
			<footer>
				
					<h3>LetsMeet2019</h3>
				
			</footer>
			
			</div>

		</body>
</html>