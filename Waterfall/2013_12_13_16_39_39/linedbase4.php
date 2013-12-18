<!DOCTYPE html>
<!-- version check
<!-- localhost/linedbase3.php?link=./Data/ScreenCapAt1381654112396/*.jpg  -->

<html lang"en">
  <body>
  	   <meta charset="utf-8">

    <title>...</title>
    	<script type="text/javascript" src="../external/jquery-2.0.2.min.js"></script>

    <script type="text/javascript" src="../external/waterfall.js"></script>
    
	<script type="text/javascript" id="key" src="./Data/key.js"></script>

     <link rel="stylesheet" type="text/css" href="../external/waterfall3.css" /> 

	
	<!-- charts -->

	
	<div id="bigcontainer">

	<!--function to replace the waterfalls at a given scroll position
	var outnames - the names of the files containing the waterfalls
	scrollPercent - Percent of the image container scrolled through
	starttimes - the time each waterfall starts
	endtimes - the time each waterfall ends
	-->
	<script type="text/javascript" >
	var length = outnames.length;
	var index;
	
	var path = outnames[0];
	document.writeln("<script type=\"text/javascript\" src="+path+"\>\</script\>");
	
	
	var i = 0;

	$(document).ready(function(){

                setInterval(function() {



			        var element = document.getElementById('media');
			        style = window.getComputedStyle(element);
			        var scrollPercent = ((element.scrollLeft) / (element.scrollWidth - element.clientWidth + .001));
			        var startPercent = (starttimes[i] / endtimes[length-1]);
        			var endPercent = (endtimes[i] / endtimes[length-1]);
        			var calculatedPercent = (scrollPercent - startPercent)/(endPercent - startPercent);
										
					//what is my current item?
					//if I pass my current item on either side, then switch
					//the problem: the time is not matched on start/end and the graph itself.
					
					//idea: everytime the mark is hit, rewrite the first line of the .js file stating current = ??
					//Then keep everything on one file. 
					
					if (calculatedPercent < 0){
						if (i != 0){
							$('script[src="' + outnames[i] + '"]').remove();
							$('<script>').attr('src', outnames[i-1]).appendTo('head');
						i = i - 1;
						console.log("moved down ", i," ", timeScrolled);
						}
					}
					if (calculatedPercent >= 1){
						if (i != length-1){
							i = i + 1;
							$('script[src="' + outnames[i-1] + '"]').remove();
							$('<script>').attr('src', outnames[i]).appendTo('head');
							console.log("moved up ", i," ", timeScrolled);

						}
					}
					
					
                }, 100);
            });
		
	</script>
	


  <div class="media" id="media">

    <div class="imageContainer">   

	<script type="text/javascript" id="key" src="./Data/images.js"></script>

  </div>
</div>
<script src="../external/js/highcharts.js"></script>
<script src="../external/js/highcharts-more.js"></script>
<script src="../external/js/modules/exporting.js"></script>

<div id="container" style="min-width: 25em; background-color:blue; max-width: 75em; height: 150em; margin: auto"></div>
</div>-->
  </body>
</html>