/**
 * 
 */

var xmlhttp;

function init() {
  
  // Überprüfen ob XMLHttpRequest-Klasse vorhanden und erzeugen von Objekte für IE7, Firefox, etc.
  if (typeof XMLHttpRequest != 'undefined') {
	  xmlhttp = new XMLHttpRequest();
  }
	
  // Wenn im oberen Block noch kein Objekt erzeugt, dann versuche XMLHTTP-Objekt zu erzeugen
  // Notwendig für IE6 oder IE5
  if (!xmlhttp) {
	  try {
	        	xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	  } catch(e) {
	     try {
	         	xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	     } catch(e) {
	          	xmlhttp = null;
         }
      }
  }
} //init()
    
    
function getdetails() {
	var orderno = document.getElementById("orderno");

    var url = "http://localhost:8080/EGH_JSF_WEB/rest/egh/getFormByNr/" + orderno.value;
    // eine andere URL als die vom localhost (andere Domaine) schlaegt fehl!
    //var url = "http://vs.zemit.wi.hs-osnabrueck.de/REST_MessageResource/rest/hello/getAuftragByID/" + orderno.value;
   
    //alert(url);
    xmlhttp.open('GET',url, true);
    xmlhttp.send();
    
    var list =  document.getElementById("list");
    
    xmlhttp.onreadystatechange = function() {
    
//        var list =  document.getElementById("list");
    	if (xmlhttp.readyState == 4) {
    		if ( xmlhttp.status == 200) {
    			
    			//aus JASON-String ein Object mit Namen det erzeugen
    			var det = eval( "(" +  xmlhttp.responseText + ")");
			//	alert(det);
    			//alert(det.formNr);
    			if (det.formNr > 0 ) {
    				var data = det.normalAnswers;
					
    				data.forEach(function(value, key, data){
    		//			alert(value.answerNr);
    				});
    			
    				//jason2html-Lib verwenden, um jason-Array in HTML-Liste umzuwandeln
						
   							var transform = {'<>':'li','html':'Frage Nr. ${oneAnswerNormal} Antwort: ${answerNr}'};
    				
    				document.getElementById('list').innerHTML = json2html.transform(data,transform);
						
    			
    			}
    			else {
    				document.getElementById('list').innerHTML = "";
    				alert("Invalid Form No");
    			}
    		}
        else {
        		document.getElementById('list').innerHTML = "";
        		alert("Error ->" + xmlhttp.responseText);
    	    }
    	}
    	xmlhttp.send();

    };
} //getdetails()
