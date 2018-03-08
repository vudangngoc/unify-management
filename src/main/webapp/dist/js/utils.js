get = function(url, callback) {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					if (callback != null)
						callback(xmlhttp.responseText)
				}
			}
			xmlhttp.open("GET", url);
			xmlhttp.send();
		}
		post = function(url, s, callback) {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					if (callback != null)
						callback(xmlhttp.responseText)
				}
			}
			xmlhttp.open("POST", url);
			xmlhttp.setRequestHeader("Content-Type",
					"application/json;charset=UTF-8");
			xmlhttp.send(s);
		}
		put = function(url, s, callback) {
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					if (callback != null)
						callback(xmlhttp.responseText)
				}
			}
			xmlhttp.open("PUT", url);
			xmlhttp.setRequestHeader("Content-Type",
					"application/json;charset=UTF-8");
			xmlhttp.send(s);
		}
		load = function(url) {
			get(url, function(s) {
				var el = document.getElementById("content")
				el.innerHTML = s
				document.getElementById("content_callback").onload()
			})
		}
		
		loadDropDownList = function(url,divId,controlId,callBack){
			get(url,function(res){
				var data = JSON.parse(res);
				var x = document.createElement("SELECT");
				var option = document.createElement("option");
				option.text = "";
				option.value = "";
				x.id = controlId;
				x.addEventListener("change",callBack);
				x.add(option);
				for(var o in data){
					var option = document.createElement("option");
					option.text = data[o];
					option.value = data[o];
					x.add(option);
				}
				document.getElementById(divId).innerHTML = "";
				document.getElementById(divId).appendChild(x)
			});
		}
		var bf;